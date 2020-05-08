package com.bluesky.tech.spring.controller;


import com.bluesky.tech.spring.Service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ShopControllerTest {

    @LocalServerPort
    private int port;

    @Resource
    private ShopController shopController;
    @Autowired
    private IShopService shopService;

    @Test
    public void getCurrentTimestamp() {
        log.info("port:"+port);
        long currentTimestamp = shopController.currentTimestamp();
        log.info("currentTimestamp:"+currentTimestamp);
    }

    @Test
    public void getShopNameById() throws Exception {
        String shopName = shopService.getShopNameById(10);
        log.info("shopName：" + shopName);
    }
}

