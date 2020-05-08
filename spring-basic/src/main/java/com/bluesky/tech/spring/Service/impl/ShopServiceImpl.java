package com.bluesky.tech.spring.Service.impl;

import com.bluesky.tech.spring.Service.IShopService;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements IShopService {
    @Override
    public String getShopNameById(int shopId) {
        return "shopName_"+shopId;
    }
}
