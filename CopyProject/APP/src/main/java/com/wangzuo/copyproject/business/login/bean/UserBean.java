package com.wangzuo.copyproject.business.login.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by hejie on 2016/11/5.
 *
 * 用户信息
 *
 */

public class UserBean extends DataSupport implements Serializable{
    private static final long serialVersionUID = 1L;

    private long id;
    private String my_id;//用户ID
    private String org_name;//部门名称
    private String sid;//登录token及Sid
    private String company_name;//公司名称
    private String company_id; //公司id
    private String job_name;//职位名称
    private String name;//用户名
    private String head_photo;//用户头像地址
    private String msn;//
    private String home_tel;//
    private String office_tel;//
    private String email;// email
    private String qq;//
    private String mobile;//
    private String two_code_url;//设置二维码下载地址
    private Integer unread_count;//保存用户未读消息数
    private String description;//简介
    private String skin_url;
    private String skin_name;
    private String login_org; //登录机构名
    private String login_name;	//登录名
    private String password; //密码
    private String progress_length; //进度条长度（百分比）
    private String total_playtime; //播放总时长（百分比)
    private String referer;
    private String login_id;//录入时候的账号


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_photo() {
        return head_photo;
    }

    public void setHead_photo(String head_photo) {
        this.head_photo = head_photo;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getHome_tel() {
        return home_tel;
    }

    public void setHome_tel(String home_tel) {
        this.home_tel = home_tel;
    }

    public String getOffice_tel() {
        return office_tel;
    }

    public void setOffice_tel(String office_tel) {
        this.office_tel = office_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTwo_code_url() {
        return two_code_url;
    }

    public void setTwo_code_url(String two_code_url) {
        this.two_code_url = two_code_url;
    }

    public Integer getUnread_count() {
        return unread_count;
    }

    public void setUnread_count(Integer unread_count) {
        this.unread_count = unread_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkin_url() {
        return skin_url;
    }

    public void setSkin_url(String skin_url) {
        this.skin_url = skin_url;
    }

    public String getSkin_name() {
        return skin_name;
    }

    public void setSkin_name(String skin_name) {
        this.skin_name = skin_name;
    }

    public String getLogin_org() {
        return login_org;
    }

    public void setLogin_org(String login_org) {
        this.login_org = login_org;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProgress_length() {
        return progress_length;
    }

    public void setProgress_length(String progress_length) {
        this.progress_length = progress_length;
    }

    public String getTotal_playtime() {
        return total_playtime;
    }

    public void setTotal_playtime(String total_playtime) {
        this.total_playtime = total_playtime;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }
}
