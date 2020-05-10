package com.bluesky.tech.spring.handle.vo;

/**
 * 服务端处理结果实体
 */
public class Rsres<T> {
    public Rsres() {
    }

    public Rsres(int returnCode, String returnMsg, T result) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.result = result;
    }

    /**
     * 响应编码。0-正常，小于0-系统级错误，大于0-业务级错误
     */
    private int returnCode = -1;
    /**
     * 响应消息。code非0时，message非空
     */
    private String returnMsg = "sysError";
    /**
     * 响应结果
     */
    private T result;

    public static <T> Rsres<T> Fail(String failMsg) {
        Rsres<T> rsres = new Rsres<>();
        rsres.setReturnMsg(failMsg);
        rsres.setReturnCode(-1);
        return rsres;
    }

    public static <T> Rsres<T> Fail(String failMsg, int returnCode) {
        Rsres<T> rsres = new Rsres<>();
        rsres.setReturnMsg(failMsg);
        rsres.setReturnCode(returnCode);
        return rsres;
    }

    public static <T> Rsres<T> Success(T result) {
        Rsres<T> rsres = new Rsres<>();
        rsres.setResult(result);
        rsres.setReturnCode(0);
        rsres.setReturnMsg("OK");
        return rsres;
    }

    public static <T> Rsres<T> Success() {
        Rsres<T> rsres = new Rsres<>();
        rsres.setReturnCode(0);
        rsres.setReturnMsg("OK");
        return rsres;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public boolean success() {
        return returnCode == 0;
    }

    public Rsres setReturnCode(int returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}

