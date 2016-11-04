package com.wangzuo.copyproject.common.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.common.utils.InflaterUtils;

import java.io.PipedReader;

/**
 * Created by hejie on 2016/11/1.
 *
 * 简易ViewHoler
 * 快速封装Adapter里面的ViewHolder的基本操作和处理
 *
 */

public class SimpleViewHolder {

    private int position;
    private SparseArray<View> array ;
    private View convertView;

    public SimpleViewHolder(Context context, ViewGroup viewGroup,int layoutId,int position){
        this.position = position;
        array = new SparseArray<>();
        this.convertView = InflaterUtils.inflater(context,layoutId,null);
        this.convertView.setTag(this);
    }

    /**
     * 返回一个ViewHolder
     * @param context 上下文
     * @param viewGroup viewGroup
     * @param layoutId 控件id
     * @param position 位置
     * @param convertView 父布局
     * @return
     */
    public static SimpleViewHolder get(Context context,ViewGroup viewGroup,int layoutId,int position,View convertView){
        if (convertView == null){
            return new SimpleViewHolder(context,viewGroup,layoutId,position);
        }else {
            SimpleViewHolder simpleViewHolder = (SimpleViewHolder) convertView.getTag();
            simpleViewHolder.position = position;
            return simpleViewHolder;
        }
    }

    public int getPosition() {
        return position;
    }

    public View getConvertView() {
        return convertView;
    }

    /**
     * 通过view的id来获取相应的控件
     * @param viewId id
     * @param <T> view泛型
     * @return 返回控件
     */
    public <T extends View> T getView(int viewId){
        View view = array.get(viewId);
        if (view == null){
            view = convertView.findViewById(viewId);
            array.put(viewId,view);
        }
        return (T) view;
    }
}
