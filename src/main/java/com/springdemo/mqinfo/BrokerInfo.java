package com.springdemo.mqinfo;

/**
 * @author Cadmean
 */
public class BrokerInfo {
    private String brokerName;
    private String addr;
    private String brokerId;
    private String uptime;
    private int connection;
    private long memoryLimit;
    private long storeLimit;
    private int storePercentUsage;
    private int memoryPercentUsage;

    public int getStorePercentUsage() {
        return storePercentUsage;
    }

    public void setStorePercentUsage(int storePercentUsage) {
        this.storePercentUsage = storePercentUsage;
    }

    public int getMemoryPercentUsage() {
        return memoryPercentUsage;
    }

    public void setMemoryPercentUsage(int memoryPercentUsage) {
        this.memoryPercentUsage = memoryPercentUsage;
    }

    @Override
    public String toString() {
        return "BrokerInfo{" +
                "brokerName='" + brokerName + '\'' +
                ", addr='" + addr + '\'' +
                ", brokerId='" + brokerId + '\'' +
                ", uptime='" + uptime + '\'' +
                ", connection=" + connection +
                ", memoryLimit=" + memoryLimit +
                ", storeLimit=" + storeLimit +
                ", storePercentUsage=" + storePercentUsage +
                ", memoryPercentUsage=" + memoryPercentUsage +
                '}';
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public int getConnection() {
        return connection;
    }

    public void setConnection(int connection) {
        this.connection = connection;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public BrokerInfo(String addr) {
        this.addr=addr;
    }

    public long getStoreLimit() {
        return storeLimit;
    }

    public void setStoreLimit(long storeLimit) {
        this.storeLimit = storeLimit;
    }
}
