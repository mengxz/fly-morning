package com.bluesky.tech.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQProduce {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQProduce springMQProduce = (SpringMQProduce)ctx.getBean("springMQProduce");
        springMQProduce.jmsTemplate.send((session) -> {
            TextMessage textMessage = session.createTextMessage("spring msg3333");
            return textMessage;
        });
        System.out.println("send task over...");
    }
}
