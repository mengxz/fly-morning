package com.bluesky.tech.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 持久化后消息发布之后再启动消费者也可以正常消费
 */
public class JmsPersistentTopicConsumer {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws Exception {
        testListenMethod();
    }

    private static void testListenMethod() throws JMSException, IOException {
        System.out.println("topic 01...");
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.setClientID("topic01");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remark...");
        connection.start();
        Message message = durableSubscriber.receive();
        while (null != message){
            TextMessage textMessage = (TextMessage)message;
            System.out.println("持久化topic msg:" + textMessage.getText());
            //设置等待时间1s，获取完成后关闭
            message = durableSubscriber.receive(1000L);
        }

        session.close();
        connection.close();
        System.out.println("*****finish******");
    }
}
