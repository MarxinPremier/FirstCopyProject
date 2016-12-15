package com.wangzuo.copyproject.common.view.refresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.liaoinstan.springview.widget.SpringView;

import java.io.PipedReader;

/**
 * Created by hejie on 2016/12/15.
 * <p>
 * 下拉刷新控件，包含如下组件
 * 1.下拉头部显示控件{@link RefreshHeader}
 * 2.上拉尾部显示控件{@link RefreshFooter}
 * 3.加载中显示控件
 * 4.空布局控件
 * 5.数据显示部分
 * --- 5.1 使用RecyclerView来加载数据显示
 * --- 5.2
 * 6.加载数据presenter
 */

public class RefreshView extends SpringView {


    public static int LINEAR_ADAPTER = 0x0011;
    public static int GRID_ADAPTER = 0x0012;

    private final RecyclerView recyclerView;

    /***
     * 构造
     *
     * @param context
     * @param attrs
     */
    public RefreshView(Context context, AttributeSet attrs) {
        super(context,attrs);
        //头部
        setHeader(new RefreshHeader(context));
        //尾部
        setHeader(new RefreshFooter(context));
        //添加recyclerview
        recyclerView = new RecyclerView(context);
        addView(recyclerView);
        //初始化RecyclerView
    }


}
