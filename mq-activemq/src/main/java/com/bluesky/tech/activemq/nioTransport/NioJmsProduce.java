package com.bluesky.tech.activemq.nioTransport;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class NioJmsProduce {
    public static final String ACTIVEMQ_URL = "nio://localhost:61618";
    public static final String QUEUE_NAME = "nio-transport";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        //通过使用messageProducer生产消息发送至mq队列中
        for (int i = 0; i < 3; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("nio mag i" + i);
            //通过messageProducer发送给mq
            producer.send(textMessage);
        }
        //关闭资源
        producer.close();
        session.close();
        connection.close();
        System.out.println("*****finish******");
    }
}
