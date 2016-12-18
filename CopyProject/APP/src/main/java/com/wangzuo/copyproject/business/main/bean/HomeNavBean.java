package com.wangzuo.copyproject.business.main.bean;

import com.wangzuo.copyproject.common.utils.StringUtils;

/**
 * Created by hejie on 2016/12/18.
 */

public class HomeNavBean {

    /**
     * my_id : 菜单id
     * name : 菜单名称
     * code : 菜单编码
     */

    private String my_id;
    private String name;
    private String code;

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }


    public String getName() {
        return StringUtils.isEmpty(name)?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
