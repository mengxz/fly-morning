package com.bluesky.tech.spring.handle.enums;

public enum HandleEnum {

    TEST(10, "测试", "testHandler"),
    COMMON(20,"通用","commonHandler");

    private int value;
    private String desc;
    //处理类的: spring bean id
    private String receiveHandler;

    HandleEnum(int value, String desc, String receiveHandler) {
        this.value = value;
        this.desc = desc;
        this.receiveHandler = receiveHandler;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getReceiveHandler(){
        return receiveHandler;
    }

    public static HandleEnum fromCode(Integer code) {
        if(code == null) return null;
        for (HandleEnum a : HandleEnum.values())
            if (a.value == code)
                return a;
        return null;
    }
}
