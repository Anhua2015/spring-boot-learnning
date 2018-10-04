package com.springdemo.entity;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.HashMap;

public class ChartEntity {
    public static HashMap<String,HashMap<String,Object>> ATTR = new HashMap<String,HashMap<String,Object>>();

    static String THREADING="java.lang:type=Threading";

    static String OPERATING="java.lang:type=OperatingSystem";

    static String MEMORY="java.lang:type=Memory";

    public static final String[][] NAME = {{"Cpu",OPERATING,"ProcessCpuLoad"}
                                            ,{"Thread",THREADING,"ThreadCount"}
                                            ,{"TotalThread",THREADING,"TotalStartedThreadCount"}
    ,{"HeapMemoryUsed",MEMORY,"HeapMemoryUsage::used"}};

    public static void init() throws MalformedObjectNameException {
        for (int i=0;i<NAME.length;i++){
            ATTR.put(NAME[i][0],getJmxMap(NAME[i][1],NAME[i][2]));
        }
    }

    public static HashMap<String,Object> getJmxMap(String objectName, String attr) throws MalformedObjectNameException {
        HashMap<String,Object> on = new HashMap<>();
        on.put("ObjectName",new ObjectName(objectName));
        on.put("Attribute",attr);
        return on;
    }
}
