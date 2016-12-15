package com.wangzuo.copyproject.business.login.bean;

/**
 * Created by hejie on 2016/11/5.
 *
 * 登录参数集合
 *
 */

public class LoginParams {
    private String org;
    private String account;
    private String pwd;
    private String model;
    private String release;

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
