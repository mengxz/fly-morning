package com.bluesky.tech.activemq.boot.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class TopicConfigBean {
    @Value("${mytopic}")
    private String myTopic;

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(myTopic);
    }
}
