package com.wangzuo.copyproject.common.utils;

/**
 * Created by hejie on 2016/11/3.
 *
 * 全局变量设置
 */
public class GlobalConstants {

    public static final String PWD_DES_KEY = "12312123";

    public static final String UNLOGIN_ACTION = "com.wangzuo.copyproject.business.unlogin";// 未登录消息广播action

    /**有版本更新**/
    public static final int UPDATE = 0x01001;
    /**没有版本更新**/
    public static final int NO_UPDATE = 0x01002;
    /**版本更新后其他操作**/
    public static final int AFTER_UPDATE = 0x01003;
    /**下载apk的超时时间设定**/
    public static final int TIME_OUT = 10*1000;
    /**apk更新下载的通知id***/
    public static final int APK_UPDATE_NOTIFACATION_ID = 0;
    /**apk更新下载成功***/
    public static final int DOWN_OK = 0xff;
    /**apk更新下载失败***/
    public static final int DOWN_FAILD = 0x00;

}
