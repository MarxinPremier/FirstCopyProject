package com.wangzuo.copyproject.business.main.bean;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.common.utils.ResourceUtils;
import com.wangzuo.copyproject.common.utils.StringUtils;

/**
 * Created by hejie on 2016/12/18.
 */

public class BannerBean {
    /** 广告id **/
    private String my_id;
    /** 广告说明 **/
    private String title;
    /** 广告图片 **/
    private String image;
    /** 内部广告内容 **/
    private String app_content;
    /** 外部广告内容**/
    private String url;
    /**类型**/
    private String type;

    /**保存的用户编号**/
    private String save_cache_user_id;
    /**保存机构名**/
    private String save_cache_org;

    public String getMy_id() {
        return my_id;
    }
    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }
    public String getTitle() {
        return StringUtils.isEmpty(title) ? ResourceUtils.getString(R.string.common_pause_no): title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return StringUtils.isEmpty(image) ? "" :image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getApp_content() {
        return StringUtils.isEmpty(app_content) ? "" : app_content ;
    }
    public void setApp_content(String app_content) {
        this.app_content = app_content;
    }
    public String getUrl() {
        return StringUtils.isEmpty(url) ? "" : url ;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getType() {
        return StringUtils.isEmpty(type) ? "" : type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public String getSave_cache_user_id() {
        return save_cache_user_id;
    }
    public void setSave_cache_user_id(String save_cache_user_id) {
        this.save_cache_user_id = save_cache_user_id;
    }
    public String getSave_cache_org() {
        return save_cache_org;
    }
    public void setSave_cache_org(String save_cache_org) {
        this.save_cache_org = save_cache_org;
    }

}
