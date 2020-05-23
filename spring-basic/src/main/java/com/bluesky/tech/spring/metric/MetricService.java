package com.bluesky.tech.spring.metric;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MetricService {
    //定时提交
    private final int interval = 10;
    private ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    public void run() {
        TimerTask timerTask = new TimerTask();
        // 按 interval 秒的周期执行任务
        timer.scheduleAtFixedRate(timerTask, 0, interval, TimeUnit.SECONDS);
    }

    private class TimerTask implements Runnable {
        @Override
        public void run() {
            log.info("MetricService TimerTask task begin..."+new Date());
        }
    }

    public void stop() {
        timer.shutdownNow();
    }
}
