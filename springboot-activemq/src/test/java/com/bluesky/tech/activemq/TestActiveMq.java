package com.bluesky.tech.activemq;

import com.bluesky.tech.activemq.boot.Application;
import com.bluesky.tech.activemq.boot.produce.QueueProduce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMq {
    @Resource
    private QueueProduce queueProduce;

    @Test
    public void testSend() throws Exception{
        queueProduce.produceMsg();
    }
}
