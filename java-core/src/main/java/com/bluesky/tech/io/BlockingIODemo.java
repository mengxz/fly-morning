package com.bluesky.tech.io;


import com.ctrip.framework.apollo.core.ConfigConsts;
import com.google.common.base.Joiner;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BlockingIODemo {


    //参考:https://developer.aliyun.com/article/782086?utm_content=g_1000243150
    private static void blockingIO(){
        System.out.println("begin=="+new Date());
        String url ="http://nets.api.autohome.com.cn/api/nets/promotion/health";
        //String url ="http://localhost:30300/api/nets/promotion/health";
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("end=="+new Date());
    }

    //https://github.com/AsyncHttpClient/async-http-client
    private static void futureBlockingGet() {
        //String url ="https://nets.api.autohome.com.cn/api/nets/promotion/health";
        String url = "http://localhost:30300/api/nets/promotion/health";
        //AsyncHttpClient httpClient = initAsyncHttpClient();
    }

    public static void main(String[] args)throws Exception {
        //test01();
        blockingIO();
        //futureBlockingGet();
    }
}
