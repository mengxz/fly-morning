package com.bluesky.tech.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQConsumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQConsumer consumer = (SpringMQConsumer)ctx.getBean("springMQConsumer");
        String re = (String)consumer.jmsTemplate.receiveAndConvert();
        System.out.println("consumer receive..."+re);
    }
}
