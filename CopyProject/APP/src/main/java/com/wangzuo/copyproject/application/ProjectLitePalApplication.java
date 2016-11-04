package com.wangzuo.copyproject.application;

import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by hejie on 2016/10/28.
 *
 * applicaton 类
 *
 * 该类使用了litepal作为父类，用于sqlite缓存使用
 *
 */

public class ProjectLitePalApplication extends LitePalApplication{
    public static Context mContext ;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
