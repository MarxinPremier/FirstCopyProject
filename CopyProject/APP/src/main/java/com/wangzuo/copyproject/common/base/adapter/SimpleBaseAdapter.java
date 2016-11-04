package com.wangzuo.copyproject.common.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hejie on 2016/11/1.
 *
 * 简易列表addpter
 *
 *
 */

public abstract class SimpleBaseAdapter extends ListAdapter{

    protected  SimpleViewHolder holder;

    public SimpleBaseAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        holder = SimpleViewHolder.get(mContext,viewGroup,getLayoutId(),i,view);

        initEvent(getItem(i));

        return holder.getConvertView();
    }

    /**
     * 返回布局id
     * @return
     */
    public abstract int  getLayoutId();

    /**
     * 事件处理
     * @param o
     */
    public abstract void initEvent(Object o);

    /**
     * 重写布局返回方法
     * @return
     */
    @Override
    public View inflate() {
        return null;
    }

    /**
     * 返回textview控件
     * @param viewId 控件id
     * @return
     */
    public TextView TextView(int viewId){
        return (TextView) holder.getConvertView().findViewById(viewId);
    }

    /**
     * 返回imageview控件
     * @param viewId
     * @return
     */
    public ImageView ImageView(int viewId){
        return (ImageView) holder.getConvertView().findViewById(viewId);
    }
}
