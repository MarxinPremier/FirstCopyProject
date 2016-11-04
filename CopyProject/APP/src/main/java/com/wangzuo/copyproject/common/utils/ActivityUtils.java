package com.wangzuo.copyproject.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hejie on 2016/10/28.
 *
 * activity 启动工具类
 *
 */

public class ActivityUtils {

    /**
     * 启动activity的重载方法
     * @param context
     * @param clazz
     */
    public static void startActivity(Context context,Class<?> clazz){
        startActivity(context,new Intent(context,clazz));
    }

    /**
     * 启动activity的方法
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent){
        context.startActivity(intent);
        AnimUtils.inAnim(context);
    }

    /**
     * 退出activity的方法
     * @param context
     */
    public static void finishActivity(Context context){
        ((Activity)context).finish();
        AnimUtils.outAnim(context);
    }
}
