package com.blueksy.pattern.factory.simpleFactory;

public class FastDfsStorageType implements IStorageType {
    @Override
    public void uploadFile(String file) {
        System.out.println("文件" + file + "已上传到 fastdfs服务器");
    }
}

