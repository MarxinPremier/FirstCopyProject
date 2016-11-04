package com.wangzuo.copyproject.common.utils;

import android.content.Context;
import android.provider.Telephony;

/**
 * Created by hejie on 2016/11/2.
 *
 * 显示的工具类
 *
 */

public class DisplayUtils {


    /**
     * 获得设备密度
     * @param context
     * @return
     */
    public static float getDesity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获得字体转换系数
     * @param context
     * @return
     */
    public static float getScaleDesity(Context context){
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * px转dp
     * @return
     */
    public static int px2dp(Context context,float pxValue){
        float desity = getDesity(context);
        float v = pxValue / desity + 0.5f;
        return (int) v;
    }

    /**
     * dp转px
     * @return
     */
    public static int dp2px(Context context,float dpValue){
        float desity = getDesity(context);
        float v = dpValue * desity+0.5f;
        return (int) v;
    }

    /**
     * 字体的sp转px
     * @param context
     * @param sp
     * @return
     */
    public static int sp2px(Context context,float sp){
        float scaleDesity = getScaleDesity(context);
        float v = sp * scaleDesity + 0.5f;
        return (int) v;
    }

    /**
     * 字体px转sp
     * @param context
     * @param px
     * @return
     */
    public static int px2sp(Context context,float px){
        float scaleDesity = getScaleDesity(context);
        float v = px / scaleDesity + 0.5f;
        return (int) v;
    }
}
