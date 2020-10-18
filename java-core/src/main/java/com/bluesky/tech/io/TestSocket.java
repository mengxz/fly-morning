package com.bluesky.tech.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSocket {
    public static void main(String[] args)throws Exception {
        ServerSocket serverSocket = new ServerSocket(8090);
        System.out.println("step 1:new ServerSocket(8090) ");
        while (true){
            Socket client = serverSocket.accept();
            System.out.println("step2:serverSocket.accept:"+client.getPort());
            new Thread(() -> {
                try{
                    InputStream inputStream = client.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true){
                        System.out.println(bufferedReader.readLine());
                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }).start();
        }
    }
}
