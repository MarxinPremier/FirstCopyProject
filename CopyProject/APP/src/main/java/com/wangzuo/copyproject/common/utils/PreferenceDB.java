package com.wangzuo.copyproject.common.utils;

/**
 * Created by hejie on 2016/10/31.
 *
 * 用于信息存储与获取
 * 单利模式推荐使用静态内部类的方式
 * 延迟了对象的初始化过程，并且不会出现并发问题
 *
 */

public class PreferenceDB {

    /**
     * 默认构造方法，使用单利模式
     */
    private PreferenceDB(){}

    /**
     * 静态内部类
     */
    private static class SingleTonHolder{
        private static final PreferenceDB singleTon = new PreferenceDB();
    }

    /**
     * 单例获取方法
     * @return
     */
    public static PreferenceDB getInstance(){
        return SingleTonHolder.singleTon;
    }

    /**
     * 是否第一次打开App
     * @return
     */
    public boolean getFirstOpen(){
        return PreferencesUtils.getBoolean("isFirstOppenApp",true);
    }

    /**
     * 第一打开app设置的标志
     * @param org
     * @return
     */
    public boolean setFirstOppen(boolean org) {
        return PreferencesUtils.putBoolean("isFirstOppenApp", org);
    }

    /**
     * 设置默认登录标签
     * @param login
     * @return
     */
    public boolean setAutoLogin(boolean login){
        return  PreferencesUtils.putBoolean("isAutoLogin",login);
    }
}
