package com.bluesky.tech.mdc;

import org.slf4j.MDC;

public class MDCThreadTest extends Thread {
    private int i ;

    public MDCThreadTest(int i){
        this.i = i;
    }

    @Override
    public void run(){
        System.out.println(++i);
        MDC.put("username", "name_"+i);

        for (int j = 0; j < 100; j++) {
            System.out.println("aaa====" + i);
            if(j==10){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("run: " + i + "     "  + MDC.get("username"));
    }

    public static void main(String args[]) throws InterruptedException{
        MDCThreadTest t1 = new MDCThreadTest(1);
        t1.start();
        MDCThreadTest t2 = new MDCThreadTest(2);
        t2.start();
    }
}
