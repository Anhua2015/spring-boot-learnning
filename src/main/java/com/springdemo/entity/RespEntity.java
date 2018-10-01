package com.springdemo.entity;

/**
 * @author Cadmean
 */
public class RespEntity<R> {
    private int code;
    private String msg;
    private R data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    public RespEntity(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, R data) {
        this(respCode);
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
