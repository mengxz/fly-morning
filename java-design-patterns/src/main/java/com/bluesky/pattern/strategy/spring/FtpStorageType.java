package com.bluesky.pattern.strategy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FtpStorageType implements IStorageType {
    @Autowired
    private FtpStorageType ftpStorageType;

    private final static String FTP = "ftp";

    @Override
    public void uploadFile(String file) {
        System.out.println("文件" + file + "已上传到 ftp服务器");
    }

    /**
     * 当前 bean 被实例化后，会执行下面方法把字符串和策略的对应关系传进去
     */
    @Override
    public void afterPropertiesSet() {
        StorageMapSingleton.getInstance().putStorageType(FTP, ftpStorageType);
    }
}

