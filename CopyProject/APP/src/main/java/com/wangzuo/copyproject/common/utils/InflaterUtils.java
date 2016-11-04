package com.wangzuo.copyproject.common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hejie on 2016/10/28.
 *
 * 简化的布局加载器
 */

public class InflaterUtils {

    public static View inflater(Context context, int layoutId, ViewGroup viewContainer){
        return LayoutInflater.from(context).inflate(layoutId,viewContainer);
    }

    public static View inflater(Context context, int layoutId){
        return LayoutInflater.from(context).inflate(layoutId,null);
    }
}
