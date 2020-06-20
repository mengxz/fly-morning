package com.bluesky.tech.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ForkJoinMDC extends Thread {
    private int i ;

    public ForkJoinMDC(int i){
        this.i = i;
    }

    @Override
    public void run(){
        MDC.put("username", "name_"+i);
//        test01();
        test02(3);
    }

    public void test01(){
        for (int j = 0; j < 3; j++) {
            log.info("aaa====" + j);
            new Thread(() -> {
                log.info("currentThreadName:"+Thread.currentThread().getName()+"====="+MDC.get("username"));
            },"AA_"+j).start();
        }
        log.info("run: " + i + "======="  + MDC.get("username"));
    }


    public List<String> test02(int num){
        log.info("===test02===="  + MDC.get("username"));
        List<String> list = new ArrayList<String>();
        for(int i=0;i<num;i++){
            list.add("this num_"+i);
        }
        List<String> relist = list.stream().parallel().map((x) -> {
            log.info(x + "=========" + Thread.currentThread().getName()+"--userName---"+MDC.get("username"));
            return x + "_ok";
        }).collect(Collectors.toList());
        return relist;
    }

    public static void main(String args[]) throws InterruptedException{
        ForkJoinMDC t1 = new ForkJoinMDC(1);
        t1.start();
//        ThreadTest1 t2 = new ThreadTest1(2);
//        t2.start();
    }
}
