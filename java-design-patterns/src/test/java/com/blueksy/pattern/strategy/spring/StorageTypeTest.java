package com.blueksy.pattern.strategy.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StorageApplication.class)
public class StorageTypeTest {


    @Before
    public void testBefore(){
        System.out.println("测试前");
    }

    @After
    public void testAfter(){
        System.out.println("测试后");
    }

    @Test
    public void storageTest(){
        IStorageType iStorageType = StorageMapSingleton.getInstance().getStorageType("ftp");
        iStorageType.uploadFile("策略模式.txt");
    }
}

