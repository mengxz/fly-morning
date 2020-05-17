package com.bluesky.tech.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsMapProduce {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("mag i" + i);
            //消息属性
            if(i==2){
                textMessage.setBooleanProperty("isVip",Boolean.TRUE);
            }else {
                textMessage.setBooleanProperty("isVip",Boolean.FALSE);
            }
            producer.send(textMessage);
            //发送map类型消息
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("k1","mapMessage--v"+i);
            mapMessage.setInt("intKey",100+i);
            producer.send(mapMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("*****finish******");
    }
}
