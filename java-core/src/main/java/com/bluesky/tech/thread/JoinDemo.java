package com.bluesky.tech.thread;

import java.util.concurrent.TimeUnit;

public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        //获取当前线程信息
        Thread previousThread= Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread thread=new Thread(new Domino(previousThread));
            thread.start();
            previousThread=thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Thread.currentThread().getName()+\" terminate.\" = " + Thread.currentThread().getName()+" terminate.");

    }

    static class Domino implements Runnable{
        private Thread thread;
        public Domino(Thread thread){
            this.thread=thread;
        }
        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
