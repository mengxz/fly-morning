package com.bluesky.tech.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * https://www.bilibili.com/video/BV11K4y1C7rm?p=1
 * IO多路复用原理分析
 * nio,epoll,多路复用
 * Linux抓取程序对内核发生的系统调用，输出到指定文件；
 * strace -ff -o ./xxxx java TestSocket
 *
 * jps查询进程号 2878
 * cd /proc/2878
 * cd /proc/2878/task
 * cd /proc/2878/fd   linux一切皆文件【文件描述符】0-标准输入；1-标准输出；2-错误输出；
 * 查询网络端口
 * netstat -natp
 *
 *
 *linux程序nc连接socket
 *nc localhost 8090
 *查询网络端口
 *netstat -natp
 *cd /proc/2878/
 *ll
 *文件描述符增加一个新的文
 *
 *
 * less
 * /socket.*5$
 *
 * 查看linux命令帮助
 * man accept
 * man 2 bind
 */
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
