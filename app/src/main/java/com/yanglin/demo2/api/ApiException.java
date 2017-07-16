package com.yanglin.demo2.api;

/**
 * 作者 Administrator
 * 时间   2017/7/14 0014 12:09.
 * 描述   处理公共数据的异常,当返回的请求结果为失败的情况下,直接抛出异常
 */

public class ApiException extends RuntimeException {
    private String mMsg;

    public String getMsg() {
        return mMsg;
    }

    public ApiException(String msg) {
        super();
        mMsg = msg;
    }
}
