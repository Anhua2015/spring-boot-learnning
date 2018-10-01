package com.springdemo.mqinfo;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;

public class Broker {
    private String url;
    private JMXConnector connector;
    private MBeanServerConnection connection;
    private BrokerInfo brokerInfo;
    private String addr;
    public Broker(String addr) throws IOException {
        this.addr=addr;
        this.url="service:jmx:rmi:///jndi/rmi://"+addr+":1099/jmxrmi";
        this.brokerInfo=new BrokerInfo(this.addr);
        createConnector();
    }
    private void createConnector() throws IOException {
        JMXServiceURL url = new JMXServiceURL(this.url);
        connector = JMXConnectorFactory.connect(url, null);
        connector.connect();
        connection = connector.getMBeanServerConnection();
    }

    public BrokerInfo getBrokerInfo() throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, IOException {
        String objectName="org.apache.activemq:brokerName="+this.addr+",type=Broker";
        ObjectName name = new ObjectName(objectName);
        this.brokerInfo.setBrokerName((String) connection.getAttribute(name,"BrokerName"));
        this.brokerInfo.setBrokerId((String) connection.getAttribute(name,"BrokerId"));
        this.brokerInfo.setConnection((Integer) connection.getAttribute(name,"CurrentConnectionsCount"));
        this.brokerInfo.setMemoryLimit((Long) connection.getAttribute(name,"MemoryLimit"));
        this.brokerInfo.setStoreLimit((Long) connection.getAttribute(name,"StoreLimit"));
        this.brokerInfo.setUptime((String) connection.getAttribute(name,"Uptime"));
        return brokerInfo;
    }
}
