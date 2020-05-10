package com.bluesky.tech.spring.handle.vo;

public class FilterResultVo<T> {

	private boolean success = false;
	/**
	 * 响应消息。code非0时，message非空
	 */
	private String returnMsg = "sysError";
	/**
	 * 响应结果
	 */
	private T result;

	public static <T> FilterResultVo<T> Success(T result){
		FilterResultVo<T> rsres = new FilterResultVo<>();
		rsres.setResult(result);
		rsres.setReturnMsg("OK");
		rsres.setSuccess(true);
		return rsres;
	}

	public static <T> FilterResultVo<T> Fail(String failMsg){
		FilterResultVo<T> rsres = new FilterResultVo<>();
		rsres.setReturnMsg(failMsg);
		return rsres;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(final String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(final T result) {
		this.result = result;
	}
}
