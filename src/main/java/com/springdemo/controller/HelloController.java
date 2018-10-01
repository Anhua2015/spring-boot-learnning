package com.springdemo.controller;
import com.springdemo.entity.RespCode;
import com.springdemo.entity.RespEntity;
import com.springdemo.mqinfo.Broker;
import com.springdemo.mqinfo.BrokerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author Cadmean
 */
@Controller
public class HelloController {
    Broker broker=null;

    @RequestMapping("/")
    public String hello(){
        return "index";
    }

    @GetMapping("/connectBroker.action")
    public String connectBroker(@RequestParam(value = "addr", required = false) String addr, Model model){
        try {
            this.broker = new Broker(addr);
            System.out.println(broker.getBrokerInfo().toString());
            model.addAttribute("broker",broker.getBrokerInfo());
        } catch (IOException | MalformedObjectNameException | AttributeNotFoundException | MBeanException | ReflectionException | InstanceNotFoundException e) {
            e.printStackTrace();
        }
        return "broker";
    }

    @RequestMapping(value = "/time",method=RequestMethod.GET)
    @ResponseBody
    public RespEntity<BrokerInfo> getTime() throws MalformedObjectNameException, InstanceNotFoundException, IOException, ReflectionException, AttributeNotFoundException, MBeanException {
        return new RespEntity<BrokerInfo>(RespCode.SUCCESS,this.broker.getBrokerInfo());
    }

}