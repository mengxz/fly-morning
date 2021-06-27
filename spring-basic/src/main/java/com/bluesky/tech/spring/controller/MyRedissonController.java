package com.bluesky.tech.spring.controller;

import com.bluesky.tech.spring.Service.IShopService;
import com.bluesky.tech.spring.Service.impl.MyRedissonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/redisson")
@Slf4j
public class MyRedissonController {
    @Autowired
    private MyRedissonService myRedissonService;

    @GetMapping(value = "/currentTimestamp")
    public long currentTimestamp() {
        log.info("调用getCurrentTimestamp接口");
        Date curr = new Date();
        return curr.getTime();
    }

    @GetMapping(value = "/lock/{phone}")
    public String testLock(@PathVariable String phone) {
        log.info("testLock phone:{}",phone);
        String result = myRedissonService.testLock(phone);
        log.info("testLock phone:{},result:{}",phone,result);
        return result;
    }

    @GetMapping(value = "/semaphore/{phone}")
    public String testSemaphore(@PathVariable String phone) {
        log.info("testSemaphore phone:{}",phone);
        String result = myRedissonService.testSemaphore(phone);
        log.info("testSemaphore phone:{},result:{}",phone,result);
        return result;
    }
}
