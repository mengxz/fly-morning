package com.bluesky.tech.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsTxProduce {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        //3两个参数，第一个参数事务，第二个参数签收；事务设置为true时，session需要手动commit
        //Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        //通过使用messageProducer生产消息发送至mq队列中
        for (int i = 0; i < 3; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("mag i" + i);
            //通过messageProducer发送给mq
            producer.send(textMessage);
        }
        //关闭资源
        producer.close();
        //事务开启需要手动提交
        session.commit();
        session.close();
        connection.close();
        System.out.println("*****finish******");
    }
}
