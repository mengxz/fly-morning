package com.bluesky.tech.juc.future;

import org.asynchttpclient.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.asynchttpclient.Dsl.asyncHttpClient;

//参考:https://github.com/AsyncHttpClient/async-http-client/blob/master/example/src/main/java/org/asynchttpclient/example/completable/CompletableFutures.java
//https://github.com/AsyncHttpClient/async-http-client
public class ListenableFuturesDemo {
    public static void main(String[] args) throws Exception {
        //test01();
        //test02();
//        test03();
        //test04();
        test05();
    }

    private static void test05()throws Exception{
        String url ="https://nets.api.autohome.com.cn/api/nets/promotion/health";
        System.out.println("begin=="+new Date());
        try{
            AsyncHttpClient httpClient = asyncHttpClient();
            Request request = httpClient.prepareGet(url).build();
            AsyncHandler<Response> handler = new AsyncCompletionHandler<Response>() {
                @Override
                public Response onCompleted(Response response) throws Exception {
                    String responseBody = response.getResponseBody();
                    System.out.println("handle:"+responseBody);
                    return response;
                }
            };
            //AsyncHandler<Response> handler1 = (response) ->{return response;};
            httpClient.executeRequest(request,handler);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //new CountDownLatch(1).await();
        System.out.println("end=="+new Date());
    }


    private static void test04()throws IOException{
        String url ="https://nets.api.autohome.com.cn/api/nets/promotion/health";
        System.out.println("begin=="+new Date());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try{
            AsyncHttpClient httpClient = asyncHttpClient();
            Request request = httpClient.prepareGet(url).build();
            ListenableFuture<Response> future = httpClient.executeRequest(request).addListener(() -> {
                System.out.println("finish..."+Thread.currentThread().getName());
            }, executorService);
            //线程睡眠指定时间
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
            Response response = future.get();
            System.out.println(response.getResponseBody());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("end=="+new Date());
    }


    private static void test03()throws IOException{
        String url ="https://nets.api.autohome.com.cn/api/nets/promotion/health";
        System.out.println("begin=="+new Date());
        try{
            AsyncHttpClient httpClient = asyncHttpClient();
            Request request = httpClient.prepareGet(url).build();
            ListenableFuture<Response> future = httpClient.executeRequest(request);
            Response response = future.get();
            System.out.println(response.getResponseBody());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("end=="+new Date());
    }

    private static void test02()throws IOException{
        String url ="https://nets.api.autohome.com.cn/api/nets/promotion/health";
        System.out.println("begin=="+new Date());
        try{
            AsyncHttpClient httpClient = asyncHttpClient();
            Request request = httpClient.prepareGet(url).build();
            CompletableFuture<Response> completableFuture = httpClient.executeRequest(request).toCompletableFuture();
            Response response = completableFuture.get();
            System.out.println(response.getResponseBody());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("end=="+new Date());
    }

    private static void test01()throws IOException{
        String url ="https://nets.api.autohome.com.cn/api/nets/promotion/health";
        try (AsyncHttpClient asyncHttpClient = asyncHttpClient()) {
            asyncHttpClient
                    .prepareGet(url)
                    .execute()
                    .toCompletableFuture()
                    .thenApply(Response::getResponseBody)
                    .thenAccept(System.out::println)
                    .join();
        }
    }
}
