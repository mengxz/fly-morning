package com.bluesky.tech.activemq.boot.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
@Slf4j
public class TopicConsumer {
    @JmsListener(destination = "${mytopic}")
    public void receive(TextMessage msg)throws JMSException {
        log.info("TopicConsumer receive:{}",msg.getText());
    }
}
