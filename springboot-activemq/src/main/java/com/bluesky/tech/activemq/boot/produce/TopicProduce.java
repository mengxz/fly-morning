package com.bluesky.tech.activemq.boot.produce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;


@Component
@EnableJms //重点开启注解
@Slf4j
public class TopicProduce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000L)
    public void produceMsgSchedule(){
        jmsMessagingTemplate.convertAndSend(topic,"topic schedule msg:"+ UUID.randomUUID().toString().substring(0,6));
        log.info("topic produceMsgSchedule send msg ok");
    }
}
