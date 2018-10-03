package com.springdemo.controller;
import com.springdemo.entity.ChartEntity;
import com.springdemo.entity.RespCode;
import com.springdemo.entity.RespEntity;
import com.springdemo.mqinfo.Broker;
import com.springdemo.mqinfo.BrokerInfo;
import com.springdemo.mqinfo.QueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Cadmean
 */
@Controller
public class HelloController {
    Broker broker=null;

    @Autowired
    public  HelloController() throws MalformedObjectNameException {
        ChartEntity.init();
    }

    @RequestMapping("/")
    public String indexMain(){
        return "index";
    }

    @GetMapping("/connectBroker.action")
    public String connectBroker(@RequestParam(value = "addr", required = false) String addr,Model model) throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException {
        try {
            if (this.broker!=null) {
                if (!this.broker.getAddr().equalsIgnoreCase(addr)) {
                    this.broker.closeConnection();
                    this.broker = new Broker(addr);
                }
            } else {
                this.broker=new Broker(addr);
            }
            model.addAttribute("broker",broker.getBrokerInfo());
            model.addAttribute("threadCount",broker.getCharts(Broker.THREAD));
            model.addAttribute("cpu",broker.getCharts(Broker.CPU));
            model.addAttribute("heapUsed",broker.getCharts(Broker.HEAPUSED));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "broker";
    }

    @RequestMapping(value = "/brokerInfo",method=RequestMethod.GET)
    @ResponseBody
    public RespEntity<BrokerInfo> getBrokerInfo() throws MalformedObjectNameException, InstanceNotFoundException, IOException, ReflectionException, AttributeNotFoundException, MBeanException {
        return new RespEntity<BrokerInfo>(RespCode.SUCCESS,this.broker.getBrokerInfo());
    }

    @RequestMapping(value = "/dataqueue",method=RequestMethod.GET)
    public String getQueuePage() throws MalformedObjectNameException, InstanceNotFoundException, IOException, ReflectionException, AttributeNotFoundException, MBeanException {
        return "comp/queue";
    }

    @RequestMapping(value = "/queue",method=RequestMethod.GET)
    @ResponseBody
    public RespEntity<List<QueueInfo>> getQueueInfo() throws MalformedObjectNameException, InstanceNotFoundException, IOException, ReflectionException, AttributeNotFoundException, MBeanException {
        return new RespEntity<List<QueueInfo>>(RespCode.SUCCESS,this.broker.getQueues());
    }

    @RequestMapping(value="/charts")
    @ResponseBody
    public Object getCharts(@RequestParam(value = "type", required = false) String type) throws MalformedObjectNameException, InstanceNotFoundException, IOException, ReflectionException, AttributeNotFoundException, MBeanException {
        if (this.broker!=null) {
            return this.broker.getCharts(type);
        }else {
            return 0;
        }
    }

}