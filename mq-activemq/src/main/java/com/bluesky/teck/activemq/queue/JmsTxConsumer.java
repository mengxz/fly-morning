package com.bluesky.teck.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsTxConsumer {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws Exception {
        //事务大于签收
        //在事务性会话中，当一个事务被成功提交则消息被自动签收；如果事务回滚，则消息会被再次传送；
        //在非事务性会话中，消息何时被确认取决于创建会话时的应答模式，是否ack
        //测试事务
        //testTxMethod();
        //测试签收ack
        testAckMethod();
    }

    private static void testAckMethod() throws JMSException, IOException {
        //1创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2通过连接工厂，获得连接connection
        Connection connection = factory.createConnection();
        connection.start();
        //3创建会后，第一个叫事务，第二个叫签收,设置为手动签收时，需要手动ack
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
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
                        //设置手动签收，需要ack
                        message.acknowledge();
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


    private static void testTxMethod() throws JMSException, IOException {
        //1创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2通过连接工厂，获得连接connection
        Connection connection = factory.createConnection();
        connection.start();
        //3创建会后，第一个叫事务，第二个叫签收
        //Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
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
                        //事务开启需要commit
                        session.commit();
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

}
