package com.bluesky.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private KafkaTemplate<Object, Object> template;

    //http://localhost:8090/send/k1
    @GetMapping("/send/{input}")
    public void sendFoo(@PathVariable String input) {
        //this.template.send("topic_input", input);
        this.template.send("kafka-test-topic", input);
    }
    //@KafkaListener(id = "webGroup", topics = "topic_input")
    @KafkaListener(id = "webGroup", topics = "kafka-test-topic")
    public void listen(String input) {
        logger.info("input value: {}" , input);
    }

}
