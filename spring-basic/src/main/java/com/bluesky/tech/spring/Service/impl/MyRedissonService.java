package com.bluesky.tech.spring.Service.impl;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSemaphore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MyRedissonService {

    @Autowired
    private RedissonService redissonService;

//    @Autowired
//    private StringRedisTemplate redisTemplate;

    public String testLock(final String phone){
        // 过期时间
        String ttlKey = "redis:ttl:phone:"+phone;
        String key = "redis:phone:"+phone;
        // 对这个手机号发送消息时会对发送手机号加锁
        if (!redissonService.tryLock(key, 10, TimeUnit.SECONDS)){
            //获取过期时间
            //Long ttl = redisTemplate.getExpire(ttlKey);
            //发送验证码频繁
            return "发送频繁";
        }

        // 发送验证码
        sendCode(phone);
        return "true";
    }

    public String testSemaphore(final String phone){
        log.info("testSemaphore phone:"+phone);
        RSemaphore semaphore = redissonService.getRSemaphore("semaphore");
        // 同时最多允许3个线程获取锁
        semaphore.trySetPermits(3);

        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    log.info(new Date() + "：线程[" + Thread.currentThread().getName() + "]尝试获取Semaphore锁");
                    semaphore.acquire();
                    log.info(new Date() + "：线程[" + Thread.currentThread().getName() + "]成功获取到了Semaphore锁，开始工作");
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    log.info(new Date() + "：线程[" + Thread.currentThread().getName() + "]释放Semaphore锁");
                    semaphore.release();
                }
            },"AA_"+i).start();
        }
        log.info("all ok...");
        return "finish";
    }

    private boolean sendCode(String phone){
        // 写具体发送逻辑
        return true;
    }
}

