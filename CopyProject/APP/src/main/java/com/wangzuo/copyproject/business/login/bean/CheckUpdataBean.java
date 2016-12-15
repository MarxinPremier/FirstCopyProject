package com.wangzuo.copyproject.business.login.bean;

/**
 * Created by hejie on 2016/11/23.
 *
 * 版本實體類
 *
 */

public class CheckUpdataBean {

    /**版本号**/
    private String version;

    /**版本修复描述**/
    private String description;

    /**版本地址**/
    private String url;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
