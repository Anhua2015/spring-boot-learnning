package com.springdemo.mqinfo;

public class TopicInfo {
    private String name;
    private long consumerCount;
    private long forwardCount;
    private long enqueueCount;
    private long dequeueCount;

    @Override
    public String toString() {
        return "TopicInfo{" +
                "name='" + name + '\'' +
                ", consumerCount=" + consumerCount +
                ", forwardCount=" + forwardCount +
                ", enqueueCount=" + enqueueCount +
                ", dequeueCount=" + dequeueCount +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getConsumerCount() {
        return consumerCount;
    }

    public void setConsumerCount(long consumerCount) {
        this.consumerCount = consumerCount;
    }

    public long getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(long forwardCount) {
        this.forwardCount = forwardCount;
    }

    public long getEnqueueCount() {
        return enqueueCount;
    }

    public void setEnqueueCount(long enqueueCount) {
        this.enqueueCount = enqueueCount;
    }

    public long getDequeueCount() {
        return dequeueCount;
    }

    public void setDequeueCount(long dequeueCount) {
        this.dequeueCount = dequeueCount;
    }
}
