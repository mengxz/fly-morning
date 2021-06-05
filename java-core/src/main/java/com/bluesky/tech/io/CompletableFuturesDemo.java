package com.bluesky.tech.io;

import org.asynchttpclient.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import static org.asynchttpclient.Dsl.asyncHttpClient;

//参考:https://github.com/AsyncHttpClient/async-http-client/blob/master/example/src/main/java/org/asynchttpclient/example/completable/CompletableFutures.java
public class CompletableFuturesDemo {
    public static void main(String[] args) throws Exception {
        //test01();
        //test02();
        CompletableFuturesDemo demo = new CompletableFuturesDemo();
        demo.futureTest();
    }

    private void futureTest() throws Exception{
        AsyncHttpClient httpClient = asyncHttpClient();
        CompletableFuture<String> adcodeFuture = reverseCodeFuture(httpClient);
        CompletableFuture<String> weatherFuture = adcodeFuture.thenCompose(adcode -> weatherFuture(httpClient,adcode));
        weatherFuture.whenComplete((result,throwable)->{
           if(throwable != null){
               throwable.printStackTrace();
               return;
           }
            System.out.println("futureTest=="+result);
        });
    }

    private CompletableFuture<String> weatherFuture(AsyncHttpClient httpClient,String adcode){
        String url ="http://localhost:30200/api/nets/promotion/manage/prize/test/test?id="+adcode;
        Request request = httpClient.prepareGet(url).build();
        CompletableFuture<String> future = new CompletableFuture<>();
        AsyncHandler<Response> handler = new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                String responseBody = response.getResponseBody();
                System.out.println("weatherFuture handle:"+responseBody);
                future.complete(responseBody);
                return response;
            }
        };
        httpClient.executeRequest(request,handler);
        return future;
    }

    private CompletableFuture<String> reverseCodeFuture(AsyncHttpClient httpClient){
        String url ="http://localhost:30200/api/nets/promotion/manage/health";
        Request request = httpClient.prepareGet(url).build();
        CompletableFuture<String> future = new CompletableFuture<>();
        AsyncHandler<Response> handler = new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                String responseBody = response.getResponseBody();
                System.out.println("reverseCodeFuture handle:"+responseBody);
                future.complete(responseBody);
                return response;
            }
        };
        httpClient.executeRequest(request,handler);
        return future;
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
