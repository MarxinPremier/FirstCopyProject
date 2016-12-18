package com.wangzuo.copyproject.business.main.bean;

import java.util.List;

/**
 * Created by hejie on 2016/12/18.
 *
 * 首页数据
 *
 */

public class HomeLoadLayoutBean {

    private List<HomeRegionBean> region_list;

    private List<BannerBean> banner_list;

    public List<HomeRegionBean> getRegion_list() {
        return region_list;
    }

    public void setRegion_list(List<HomeRegionBean> region_list) {
        this.region_list = region_list;
    }

    public List<BannerBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerBean> banner_list) {
        this.banner_list = banner_list;
    }

}
