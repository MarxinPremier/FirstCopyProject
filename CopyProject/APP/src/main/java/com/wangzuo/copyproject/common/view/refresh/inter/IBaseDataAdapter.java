package com.wangzuo.copyproject.common.view.refresh.inter;

import com.wangzuo.copyproject.common.view.refresh.RecyclerViewHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by hejie on 2016/12/16.
 * <p>
 * recyclerView的适配器接口，用于封装适配器所需要的配置信息
 * 在使用recyclerview时候由开发者实现
 */

public interface IBaseDataAdapter<T> {

    /**
     * 获取item的布局id
     *
     * @return 布局id
     */
    int getItemLayout();

    /**
     * 获取数据源
     *
     * @return
     */
    List<T> getData();

    /**
     * item显示方法
     *
     * @param viewHolder 布局持有者
     * @param dataBean   需要呈现的数据
     */
    void displayData(RecyclerViewHolder viewHolder, T dataBean);

    /**
     * 布局显示类型,决定recyclerview的布局管理器
     *
     * @return
     */
    int getSpanCount();

    /**
     * 列表页面请求的url
     *
     * @return 请求的url
     */
    String requestUrl();

    /**
     * 请求参数
     *
     * @return
     */
    Map<String, String> getRequestParams();
}

