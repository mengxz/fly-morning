package com.bluesky.pattern.strategy;

public class HdfsStorageStrategy extends StorageStrategy {
    @Override
    public void uploadFile(String file) {
        System.out.println("文件" + file + "已上传到 hdfs服务器");
    }
}

