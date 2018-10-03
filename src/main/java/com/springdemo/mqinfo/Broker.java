package com.springdemo.mqinfo;

import com.springdemo.entity.ChartEntity;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cadmean
 */
public class Broker {
    private String url;
    private JMXConnector connector;
    private MBeanServerConnection mbsc;
    private BrokerInfo brokerInfo;
    private String addr;

    public Broker(String addr) throws IOException {
        this.addr=addr;
        this.url="service:jmx:rmi:///jndi/rmi://"+addr+":1099/jmxrmi";
        this.brokerInfo=new BrokerInfo(this.addr);
        createConnector();
    }

    public String getAddr() {
        return addr;
    }

    private void createConnector() throws IOException {
        JMXServiceURL url = new JMXServiceURL(this.url);
        connector = JMXConnectorFactory.connect(url, null);
        connector.connect();
        mbsc = connector.getMBeanServerConnection();
    }

    public void closeConnection() throws IOException {
        this.connector.close();
    }
    public BrokerInfo getBrokerInfo() throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, IOException {
        String objectName="org.apache.activemq:brokerName="+this.addr+",type=Broker";
        ObjectName name = new ObjectName(objectName);
        this.brokerInfo.setBrokerName((String) mbsc.getAttribute(name,"BrokerName"));
        this.brokerInfo.setBrokerId((String) mbsc.getAttribute(name,"BrokerId"));
        this.brokerInfo.setConnection((Integer) mbsc.getAttribute(name,"CurrentConnectionsCount"));
        this.brokerInfo.setMemoryLimit((Long) mbsc.getAttribute(name,"MemoryLimit"));
        this.brokerInfo.setStoreLimit((Long) mbsc.getAttribute(name,"StoreLimit"));
        this.brokerInfo.setUptime((String) mbsc.getAttribute(name,"Uptime"));
        this.brokerInfo.setMemoryPercentUsage((Integer)mbsc.getAttribute(name,"MemoryPercentUsage"));
        this.brokerInfo.setStorePercentUsage((Integer)mbsc.getAttribute(name,"StorePercentUsage"));
        return brokerInfo;
    }

    public List<QueueInfo> getQueues() throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, IOException {
        List<QueueInfo> list = new ArrayList<>();
        ObjectName objectName=new ObjectName("org.apache.activemq:brokerName="+this.addr+",type=Broker");
        for (ObjectName name : (ObjectName[]) mbsc.getAttribute(objectName,"Queues")) {
            QueueInfo qi = new QueueInfo();
            qi.setName((String) mbsc.getAttribute(name,"Name"));
            qi.setQueueSize((long) mbsc.getAttribute(name,"QueueSize"));
            qi.setProducerCount((long) mbsc.getAttribute(name,"ProducerCount"));
            qi.setConsumerCount((long) mbsc.getAttribute(name,"ConsumerCount"));
            qi.setDequeueCount((long) mbsc.getAttribute(name,"DequeueCount"));
            qi.setEnqueueCount((long) mbsc.getAttribute(name,"EnqueueCount"));
            qi.setForwardCount((long) mbsc.getAttribute(name,"ForwardCount"));
            list.add(qi);
        }
        return list;
    }
    public static String THREAD="thread";
    public static String CPU="cpu";
    public static String HEAPUSED="heapMemory";

    public Object getCharts(String type) throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, IOException {
        if(HEAPUSED.equalsIgnoreCase(type)) {
            MemoryMXBean memBean= ManagementFactory.newPlatformMXBeanProxy(mbsc,ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);
            MemoryUsage heap = memBean.getHeapMemoryUsage();
            long heapSizeUsed = heap.getUsed();
            double used = heapSizeUsed / 1024 / 1024 ;
            return used;
        } else if (ChartEntity.ATTR.containsKey(type)) {
            ObjectName on = (ObjectName) ChartEntity.ATTR.get(type).get("ObjectName");
            String attr = (String) ChartEntity.ATTR.get(type).get("Attribute");
            return mbsc.getAttribute(on,attr);
        } else {
            return 0;
        }
    }
}
