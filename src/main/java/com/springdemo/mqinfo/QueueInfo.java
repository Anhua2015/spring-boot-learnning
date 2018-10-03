package com.springdemo.mqinfo;

public class QueueInfo {
    private String name;
    private long queueSize;
    private long consumerCount;
    private long producerCount;
    private long forwardCount;
    private long enqueueCount;
    private long dequeueCount;

    @Override
    public String toString() {
        return "QueueInfo{" +
                "name='" + name + '\'' +
                ", queueSize=" + queueSize +
                ", consumerCount=" + consumerCount +
                ", producerCount=" + producerCount +
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

    public long getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(long queueSize) {
        this.queueSize = queueSize;
    }

    public long getConsumerCount() {
        return consumerCount;
    }

    public void setConsumerCount(long consumerCount) {
        this.consumerCount = consumerCount;
    }

    public long getProducerCount() {
        return producerCount;
    }

    public void setProducerCount(long producerCount) {
        this.producerCount = producerCount;
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
