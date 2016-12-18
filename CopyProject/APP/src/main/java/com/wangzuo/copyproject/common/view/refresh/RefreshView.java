package com.wangzuo.copyproject.common.view.refresh;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.liaoinstan.springview.widget.SpringView;
import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.common.utils.InflaterUtils;
import com.wangzuo.copyproject.common.view.refresh.inter.IBaseDataAdapter;

/**
 * Created by hejie on 2016/12/15.
 * <p>
 * 下拉刷新控件，包含如下组件
 * 1.下拉头部显示控件{@link RefreshHeader}
 * 2.上拉尾部显示控件{@link RefreshFooter}
 * 3.加载中显示控件
 * 4.空布局控件
 * 5.刷新控件{@link SpringView}
 * 6.加载数据presenter
 * 7.数据显示部分
 * --- 使用RecyclerView来加载数据显示
 */

public class RefreshView {

    private final RecyclerView recyclerView;
    private final LinearLayout mbaseView;
    private final View refreshView;
    private final SpringView springView;
    private final RecyclerViewCommonAdapter adpter;

    /***
     * 构造
     *
     * @param context
     */
    public RefreshView(Context context, LinearLayout baseView, IBaseDataAdapter dataAdapter) {
        //记录下基本布局
        mbaseView = baseView;
        //加载列表刷新布局，并添加到父布局
        refreshView = InflaterUtils.inflater(context, R.layout.commom_refresh_layout);
        mbaseView.addView(refreshView);
        /**刷新控件部分**/
        //找到刷新控件
        springView = (SpringView) refreshView.findViewById(R.id.common_refresh_layout_spring_view);
        //给刷新控件头部
        springView.setHeader(new RefreshHeader(context));
        //尾部
        springView.setFooter(new RefreshFooter(context));
        //设定下拉刷新逻辑
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                refreshMotion();
            }

            @Override
            public void onLoadmore() {
                loadMoreMotion();
            }
        });
        //设定上拉加载逻辑
        /**数据显示部分**/
        recyclerView = (RecyclerView) refreshView.findViewById(R.id.common_refresh_layout_recycler_view);
        int spanCount = dataAdapter.getSpanCount();
        //布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(context, spanCount, LinearLayoutManager.VERTICAL, false));
        //适配器
        recyclerView.setAdapter(adpter = new RecyclerViewCommonAdapter(context, dataAdapter));
    }

    /**
     * 上拉加载更多
     */
    private void loadMoreMotion() {
        //网络请求

    }

    /**
     * 下拉刷新
     */
    private void refreshMotion() {
        //网络请求

    }
}
