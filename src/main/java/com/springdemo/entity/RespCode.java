package com.springdemo.entity;

/**
 * @author Cadmean
 */

public enum RespCode {

    SUCCESS(0, "ok"),
    WARN(-1, "fail");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}