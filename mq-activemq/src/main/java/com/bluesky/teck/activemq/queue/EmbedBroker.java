package com.bluesky.teck.activemq.queue;

import org.apache.activemq.broker.BrokerService;

public class EmbedBroker {
    public static void main(String[] args)throws Exception {
        //ActiveMQ支持再vm中通信基于嵌入式的broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61618");
        brokerService.start();
    }
}
