package com.bluesky.pattern.factory.simpleFactory;

import com.bluesky.pattern.strategy.FtpStorageStrategy;
import com.bluesky.pattern.strategy.HdfsStorageStrategy;
import com.bluesky.pattern.strategy.StorageContext;

/**
 * if else 版本的上传文件代码
 */
public class FileClient{

    private final static String LOCAL = "local";
    private final static String FTP = "ftp";
    private final static String FASTDFS = "fastdfs";
    private final static String HDFS = "hdfs";


    /**
     * 上传文件
     *
     * @param storageType 文件存储方式
     * @param file        文件
     */
    public void uploadFile(String storageType, String file) {
        if (storageType.equals(LOCAL)) {
            System.out.println("文件" + file + "已上传到 本地服务器");
        } else if (storageType.equals(FTP)) {
            System.out.println("文件" + file + "已上传到 ftp服务器");
        } else if (storageType.equals(FASTDFS)) {
            System.out.println("文件" + file + "已上传到 fastdfs服务器");
        } else if (storageType.equals(HDFS)) {
            System.out.println("文件" + file + "已上传到 hdfs服务器");
        } else {
            System.out.println("输入的文件存储类型错误");
        }
    }

    /**
     * 卫语句,上传文件
     *
     * @param storageType 文件存储方式
     * @param file        文件
     */
    public void uploadFileWithoutElse(String storageType, String file) {
        if (storageType.equals(LOCAL)) {
            System.out.println("文件" + file + "已上传到 本地服务器");
            return;
        }
        if (storageType.equals(FTP)) {
            System.out.println("文件" + file + "已上传到 ftp服务器");
            return;
        }
        if (storageType.equals(FASTDFS)) {
            System.out.println("文件" + file + "已上传到 fastdfs服务器");
            return;
        }
        if (storageType.equals(HDFS)) {
            System.out.println("文件" + file + "已上传到 hdfs服务器");
            return;
        }
        System.out.println("输入的文件存储类型错误");
    }


    public static void main(String[] args) {
        FileClient fileClient = new FileClient();
        //情况1:if-else
        fileClient.uploadFile("hdfs","ifelse.txt");

        //情况2:卫语句
        fileClient.uploadFileWithoutElse("ftp","withoutElse.txt");

        //情况3:简单工厂模式 版本的上传文件代码
        IStorageType iStorageType = StorageTypeFactory.storageTypeCreate("hdfs");
        iStorageType.uploadFile("simpleFactory.txt");

        //情况4:策略模式
        StorageContext storageContext = null;
        String storageType = "ftp";
        switch (storageType) {
            //客户端需要知道具体有哪些策略，能做什么。但是不需要知道策略具体怎么做
            case FTP:
                storageContext = new StorageContext(new FtpStorageStrategy());
                break;
            case HDFS:
                storageContext = new StorageContext(new HdfsStorageStrategy());
                break;
        }
        storageContext.uploadFileAction("strategy.txt");
    }
}

