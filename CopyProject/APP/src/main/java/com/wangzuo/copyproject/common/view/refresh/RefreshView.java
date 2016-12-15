package com.wangzuo.copyproject.common.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import com.liaoinstan.springview.widget.SpringView;
import com.wangzuo.copyproject.common.utils.InflaterUtils;

/**
 * Created by hejie on 2016/12/15.
 *
 * 下拉刷新控件，包含如下组件
 *  1.下拉头部显示控件
 *  2.上拉尾部显示控件
 *  3.加载中显示控件
 *  4.空布局控件
 *  5.加载数据presenter
 */

public class RefreshView extends SpringView{

    /***
     * 构造
     * @param context
     * @param attrs
     */
    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //加载recycleview
    }

    

}
