package com.bluesky.tech.spring.controller;

import com.bluesky.tech.spring.Service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/test")
@Slf4j
public class ShopController {
    @Autowired
    private IShopService shopService;

    @GetMapping(value = "/currentTimestamp")
    public long currentTimestamp() {
        log.info("调用getCurrentTimestamp接口");
        Date curr = new Date();
        return curr.getTime();
    }

    @GetMapping(value = "/shop/{shopId}")
    public String getShopNameById(@PathVariable int shopId) {
        log.info("getShopNameById shopId:{}",shopId);
        String shopName = shopService.getShopNameById(shopId);
        log.info("getShopNameById shopId:{},shopName:{}",shopId,shopName);
        return shopName;
    }
}
