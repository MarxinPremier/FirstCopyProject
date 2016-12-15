package com.wangzuo.copyproject.application;

import android.content.Context;
import android.content.pm.PackageInfo;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

/**
 * Created by hejie on 2016/10/28.
 * <p>
 * applicaton 类
 * <p>
 * 该类使用了litepal作为父类，用于sqlite缓存使用
 */

public class ProjectLitePalApplication extends LitePalApplication {
    //全局上下文
    public static Context mContext;
    //全局机构名
    public static String org;
    //全局userid
    public static String user_id;
    //apk下载请求引用
    private static String referer = "";
    private static String referer2 = "";
    //當前版本號
    public String localVersionName;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //LitePal數據庫初始化
        Connector.getDatabase();
        //apk版本
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            localVersionName = packageInfo.versionName;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获得全局请求头文件引用
     * @return
     */
    public static  String getReferer(){
        return referer;
    }

    /**
     * 获得全局请求头文件引用2
     * @return
     */
    public static  String getReferer2(){
        return referer2;
    }
}
