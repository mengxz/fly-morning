package com.bluesky.tech.activemq.nioTransport;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class NioJmsConsumer {
    public static final String ACTIVEMQ_URL = "nio://localhost:61618";
    public static final String QUEUE_NAME = "nio-transport";

    public static void main(String[] args) throws Exception {
        //testReceiveMethod();
        testListenMethod();
    }

    private static void testListenMethod() throws JMSException, IOException {
        //1创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2通过连接工厂，获得连接connection
        Connection connection = factory.createConnection();
        connection.start();
        //3创建会后，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4创建目的地
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //通过监听方式获取消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message != null && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println("receive msg:" + textMessage.getText());
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
