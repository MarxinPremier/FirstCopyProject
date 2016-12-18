package com.wangzuo.copyproject.business.main.bean;

import com.wangzuo.copyproject.common.utils.StringUtils;

import java.util.List;

/**
 * Created by hejie on 2016/12/18.
 */

public class HomeRegionBean {
    /**
     * my_id : 区域id
     * region_name : 区域名称
     * content_code : 区域编码
     * seq : 排序
     * display_mode_code : 展示类型
     * url : 区域数据列表信息加载URL
     * region_type : 区域类型
     * nav_list:导航类型
     */

    /**机构名称**/
    private String orgName;
    /**用户id**/
    private String user_id;
    private String my_id;
    private String region_name;
    private String content_code;
    private String seq;
    private String display_mode_code;
    private String url;
    private String region_type;
    private List<HomeNavBean> nav_list;

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getContent_code() {
        return content_code;
    }

    public void setContent_code(String content_code) {
        this.content_code = content_code;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getDisplay_mode_code() {
        return StringUtils.isEmpty(display_mode_code) ?"1":display_mode_code;
    }

    public void setDisplay_mode_code(String display_mode_code) {
        this.display_mode_code = display_mode_code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegion_type() {
        return region_type;
    }

    public void setRegion_type(String region_type) {
        this.region_type = region_type;
    }

    public List<HomeNavBean> getNav_list() {
        return nav_list;
    }

    public void setNav_list(List<HomeNavBean> nav_list) {
        this.nav_list = nav_list;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
