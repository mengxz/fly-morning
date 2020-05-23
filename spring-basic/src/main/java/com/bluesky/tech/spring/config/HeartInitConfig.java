package com.bluesky.tech.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@AutoConfigureOrder(200)
@Slf4j
public class HeartInitConfig {
    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext cxt;

    @PostConstruct
    public void init() {
        //MetricService.getInstance().setCollector(getMetricsCollector());
        log.info("HeartInitConfig init begin...");
    }
}
