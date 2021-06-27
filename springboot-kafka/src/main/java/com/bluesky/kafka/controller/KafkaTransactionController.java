package com.bluesky.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
public class KafkaTransactionController {
    private final Logger logger = LoggerFactory.getLogger(KafkaTransactionController.class);
    @Autowired
    private KafkaTemplate<Object, Object> template;

    //http://localhost:8090/transaction/k1
    @GetMapping("/transaction/{input}")
    public void sendTransactionFoo(@PathVariable String input) {
        template.executeInTransaction(t ->{
            t.send("kafka-test-topic","kl");
            if("error".equals(input)){
                throw new RuntimeException("failed");
            }
            t.send("kafka-test-topic","ckl");
            return true;
        });
    }

    @GetMapping("/transaction2/{input}")
    @Transactional(rollbackFor = RuntimeException.class)
    public void sendFoo(@PathVariable String input) {
        template.send("kafka-test-topic", "hello:"+input);
        if ("error".equals(input)) {
            logger.info("transaction2 error");
            throw new RuntimeException("failed");
        }
        template.send("kafka-test-topic", "bye:"+input);
    }

}
