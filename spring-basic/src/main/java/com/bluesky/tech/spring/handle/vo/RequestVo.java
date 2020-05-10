package com.bluesky.tech.spring.handle.vo;

public class RequestVo {
    Integer userId;
    String userName;
    Integer shopId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public RequestVo(Integer userId, String userName, Integer shopId) {
        this.userId = userId;
        this.userName = userName;
        this.shopId = shopId;
    }
}
