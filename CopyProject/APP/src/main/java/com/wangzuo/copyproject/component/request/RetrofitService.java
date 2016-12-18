package com.wangzuo.copyproject.component.request;

import com.wangzuo.copyproject.business.main.bean.HomeLoadLayoutBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hejie on 2016/12/18.
 *
 * retrofit请求服务
 *
 */

public interface RetrofitService {

    /**
     * 首页数据请求服务
     * @param homeLayoutBeanRequest 请求参数
     * @return
     */
    @FormUrlEncoded
    @POST("sys/loadIndexLayout")
    Call<HomeLoadLayoutBean> sys_loadIndexLayout(@Body RetrofitRequestBody.HomeLayoutBeanRequest homeLayoutBeanRequest);


}
