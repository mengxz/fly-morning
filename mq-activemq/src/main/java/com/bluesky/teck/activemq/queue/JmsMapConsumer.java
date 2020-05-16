package com.bluesky.teck.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsMapConsumer {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws Exception {
        //testReceiveMethod();
        testListenMethod();
    }

    private static void testListenMethod() throws JMSException, IOException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message != null && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println("receive msg:" + textMessage.getText());
                        System.out.println("receive 增强属性:" + textMessage.getStringProperty("isVip"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
                if(message != null && message instanceof MapMessage){
                    MapMessage mapMessage = (MapMessage)message;
                    try {
                        System.out.println("receive msg:" + mapMessage.getString("k1")+"---"+mapMessage.getInt("intKey"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
        System.out.println("*****finish******");
    }

    /**
     * receive
     * @throws JMSException
     */
    private static void testReceiveMethod() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        while(true){
            //TextMessage textMessage = (TextMessage)consumer.receive();
            TextMessage textMessage = (TextMessage)consumer.receive(3000L);
            if(null != textMessage){
                System.out.println("receive msg:" + textMessage.getText());
            }else {
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();
        System.out.println("*****finish******");
    }
}
