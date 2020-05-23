package com.bluesky.tech.spring.config;

import com.bluesky.tech.spring.metric.MetricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@AutoConfigureOrder(600)
@Slf4j
public class MetricInitConfig {
    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext cxt;

    @PostConstruct
    public void init() {
        //MetricService.getInstance().setCollector(getMetricsCollector());
        log.info("MetricInitConfig init begin...");
        getMetricService();
        log.info("MetricInitConfig init end...");
    }

    public MetricService getMetricService() {
        MetricService collector = new MetricService();
        //注册metric, 这里只能调用一次
        try {
            collector.run();
        } catch (Exception e) {
            log.error("metricService init or run exception: ", e);
            collector.stop();
        }
        return collector;
    }
}
