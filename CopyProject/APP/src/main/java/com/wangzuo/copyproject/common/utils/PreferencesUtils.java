package com.wangzuo.copyproject.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.wangzuo.copyproject.application.ProjectLitePalApplication;

/**
 * Created by hejie on 2016/10/31.
 *
 * sharePreference 的工具类
 *
 */

public class PreferencesUtils {

    /**
     * 提取关键字
     */
    public static String PREFERENCE_NAME = "PREFERENCE_NAME";

    /**
     * 放入一个字符串Preference
     * @param key 关键字
     * @param value 值
     * @return 如果放入成功就返回true
     */
    public static boolean putString(String key,String value){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,value);
        return edit.commit();
    }

    /**
     * 提取一个String字段
     * @param key 提取关键字
     * @param defaultValue 提取默认值
     * @return 提出的内容
     */
    public static String getStrig(String key,String defaultValue){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key,defaultValue);
    }

    /**
     * 默认提取字符串的重载方法
     * @param key 关键字
     * @return 提取的字符串
     */
    public static String getString(String key){
        return  getStrig(key,null);
    }

    /**
     * 放入int数据的方法
     * @param key 关键字
     * @param value 值
     * @return 是否放入成功
     */
    public static boolean putInt(String key,int value){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key,value);
        return edit.commit();
    }

    /**
     * 提取int数据的方法
     * @param key 关键字
     * @param defaultInt 默认值
     * @return 提取的数据
     */
    public static int getInt(String key,int defaultInt){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(key, defaultInt);
    }

    /**
     * 提取int数据的重载方法
     * @param key 关键字
     * @return 提取的数据
     */
    public static int getInt(String key){
        return getInt(key,-1);
    }

    /**
     *  放入float数据的方法
     * @param key 关键字
     * @param value 值
     * @return 放入成功与否
     */
    public static boolean putFloat(String key,int value){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putFloat(key,value);
        return edit.commit();
    }

    /**
     * 提取float数据的方法
     * @param key 关键字
     * @param defaultFloat 默认数据
     * @return 提取的数据
     */
    public static float getFloat(String key,int defaultFloat){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getFloat(key, defaultFloat);
    }

    /**
     * 提取float数据的默认方法
     * @param key 关键字
     * @return 提取的数据
     */
    public static float getFloat(String key){
        return getFloat(key,-1);
    }


    /**
     * 放入long数据的方法
     * @param key 关键字
     * @param value 值
     * @return 放入成功与否
     */
    public static boolean putLong(String key,long value){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(key,value);
        return edit.commit();
    }

    /**
     * 提取long数据的方法
     * @param key 关键字
     * @param defaultLong 默认值
     * @return 提取的数据
     */
    public static long getLong(String key,long defaultLong){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultLong);
    }


    /**
     * 提取long数据的默认方法
     * @param key 关键字
     * @return 提取的数据
     */
    public static long getLong(String key){
        return getLong(key,-1);
    }


    /**
     * 放入boolean数据的方法
     * @param key 关键字
     * @param value 值
     * @return 翻入成功与否
     */
    public static boolean putBoolean(String key,boolean value){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key,value);
        return edit.commit();
    }

    /**
     * 获得boolean数据的方法
     * @param key 关键字
     * @param defaultBoolean 默认值
     * @return 返回的数据
     */
    public  static boolean getBoolean(String key,boolean defaultBoolean){
        SharedPreferences sharedPreferences = ProjectLitePalApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,defaultBoolean);
    }

    /**
     * 获得boolean数据的方法
     * @param key 关键字
     * @return 返回的数据
     */
    public  static boolean getBoolean(String key){
        return getBoolean(key,false);
    }
}
