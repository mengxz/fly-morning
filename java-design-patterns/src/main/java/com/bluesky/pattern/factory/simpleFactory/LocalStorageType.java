package com.bluesky.pattern.factory.simpleFactory;

public class LocalStorageType implements IStorageType {
    @Override
    public void uploadFile(String file) {
        System.out.println("文件" + file + "已上传到 本地服务器");
    }
}

