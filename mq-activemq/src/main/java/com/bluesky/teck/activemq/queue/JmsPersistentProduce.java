package com.bluesky.teck.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息可靠性
 * 1、持久性
 * 2、事务
 * 3、签收
 */
public class JmsPersistentProduce {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        //1创建连接工厂，按照给定的url地址，采用默认用户名和密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2通过连接工厂，获得连接并启动访问
        Connection connection = factory.createConnection();
        connection.start();
        //3两个参数，第一个参数事务，第二个参数签收；
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4创建目的地，队列或者主题
        Queue queue = session.createQueue(QUEUE_NAME);
        //5创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        //设置是否持久化
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//非持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);//持久化
        //6通过使用messageProducer生产消息发送至mq队列中
        for (int i = 0; i < 3; i++) {
            //7创建消息
            TextMessage textMessage = session.createTextMessage("mag i" + i);
            //8通过messageProducer发送给mq
            producer.send(textMessage);
        }
        //关闭资源
        producer.close();
        session.close();
        connection.close();
        System.out.println("*****finish******");
    }
}
