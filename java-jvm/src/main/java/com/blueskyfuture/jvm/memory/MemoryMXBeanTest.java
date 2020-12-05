package com.blueskyfuture.jvm.memory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MemoryMXBeanTest {
    private static final int BUFFER_LENGTH_1 = 8192;
    //10M
    private static final int BUFFER_LENGTH = 10*1024*1024;
    private static ArrayList cache = new ArrayList();

    static {
        Timer timer = new Timer("Monitor", true);
        timer.schedule(new MonitorTask(), 0, 2000);
        cache.add(new byte[10 * BUFFER_LENGTH]);
    }

    private static class MonitorTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("monitor task..."+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            cache.add(new byte[BUFFER_LENGTH]);
            System.out.println("=======begin============================"+i);
            test01();
            System.out.println("=======end============================"+i);
        }
        //线程睡眠指定时间
        try {
            TimeUnit.SECONDS.sleep(100);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    private static void  test01(){
        MemoryMXBean memorymBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memorymBean.getHeapMemoryUsage();
        System.out.println("JVM_Mem_Heap_MB_Max===="+heapMemoryUsage.getMax()/(1024*1024L));
        System.out.println("JVM_Mem_Heap_MB_Used===="+ heapMemoryUsage.getUsed()/(1024*1024L));
        System.out.println("JVM_Mem_Heap_MB_Init===="+ heapMemoryUsage.getInit()/(1024*1024L));
        System.out.println("JVM_Mem_Heap_MB_Committed===="+ heapMemoryUsage.getCommitted()/(1024*1024L));
    }
}
