package com.bluesky.tech.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.jianshu.com/p/43c31c28e766
 */
public class LoggerDemo {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private void test(){
        logger.trace("TRACE TEST 这个地方输出TRACE级别的日志");
        logger.debug("DEBUG TEST 这个地方输出DEBUG级别的日志");
        logger.info("INFO test 这个地方输出INFO级别的日志");
        logger.error("ERROR test 这个地方输出ERROR级别的日志");
    }
    public static void main(String[] args) {
        LoggerDemo demo = new LoggerDemo();
        demo.test();
    }
}
