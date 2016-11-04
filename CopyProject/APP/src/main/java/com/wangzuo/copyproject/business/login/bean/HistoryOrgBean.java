package com.wangzuo.copyproject.business.login.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by hejie on 2016/11/1.
 *
 * 历史机构bean
 *
 */

public class HistoryOrgBean extends DataSupport{

    private long id;
    private String name;
    private String murl;
    private long createTime;

    public  HistoryOrgBean(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
