package com.wangzuo.copyproject.component.requestModel;

/**
 * Created by hejie on 2016/11/3.
 *
 * 请求返回的基本信息
 *
 */

public class BaseJson {

    private String code;
    private String msg;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
