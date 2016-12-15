package com.wangzuo.copyproject.common.utils;

import android.os.ParcelUuid;

/**
 * Created by hejie on 2016/10/31.
 * <p>
 * 用于信息存储与获取
 * 单利模式推荐使用静态内部类的方式
 * 延迟了对象的初始化过程，并且不会出现并发问题
 */

public class PreferenceDB {

    /**
     * 默认构造方法，使用单利模式
     */
    private PreferenceDB() {
    }

    /**
     * 服務器配置文件二維碼地址
     *
     * @param qrurl
     */
    public void setServerQrUrl(String qrurl) {
        PreferencesUtils.putString("qrUrl", qrurl);
    }

    /**
     * 服務器配置文件最新版本
     *
     * @param version
     */
    public void setServerVersion(String version) {
        PreferencesUtils.putString("version", version);
    }

    /**
     * 服務器配置文件最新版本描述
     *
     * @param description
     */
    public void setServerDescription(String description) {
        PreferencesUtils.putString("description", description);
    }

    /**
     * 服务器配置文件默认机构名
     *
     * @param dsite
     */
    public void setServerDsite(String dsite) {
        PreferencesUtils.putString("dsite", dsite);
    }

    /**
     * 服务器配置文件最新版本下载地址
     *
     * @param durl
     */
    public void setServerDurl(String durl) {
        PreferencesUtils.putString("durl", durl);
    }

    /**
     * 服务器配置版本描述
     *
     * @param vdesc
     */
    public void setServerVDesc(String vdesc) {
        PreferencesUtils.putString("vdesc", vdesc);
    }

    /**
     * 服务器配置文件是否更新
     *
     * @param b
     */
    public void setIsUpdata(boolean b) {
        PreferencesUtils.putBoolean("isUpdata", b);
    }

    /**
     * 设置强制更新
     *
     * @param b
     */
    public void setForceUpdate(boolean b) {
        PreferencesUtils.putBoolean("forceUpdate", b);
    }

    /**
     * 獲取強制更新
     * @return
     */
    public boolean getForceUpdate() {
        return PreferencesUtils.getBoolean("forceUpdate",false);
    }

    /**
     * 服务器配置文件最新版本下载地址
     * @return
     */
    public String getServerDurl() {
        return PreferencesUtils.getString("durl", "");
    }

    /**
     * 服务器配置文件最新版本描述
     * @return
     */
    public String getServerDescription() {
        return PreferencesUtils.getString("description", "");
    }

    /**
     * 设置升級地址
     * @param apkUrl
     */
    public void setAPKUrl(String apkUrl) {
        PreferencesUtils.putString("apkUrl", apkUrl);
    }

    /**
     * 获得升级地址
     * @return
     */
    public String getAPKUrl() {
        return PreferencesUtils.getString("apkUrl", "");
    }

    /**
     * 静态内部类
     */
    private static class SingleTonHolder {
        private static final PreferenceDB singleTon = new PreferenceDB();
    }

    /**
     * 单例获取方法
     *
     * @return
     */
    public static PreferenceDB getInstance() {
        return SingleTonHolder.singleTon;
    }

    /**
     * 是否第一次打开App
     *
     * @return
     */
    public boolean getFirstOpen() {
        return PreferencesUtils.getBoolean("isFirstOppenApp", true);
    }

    /**
     * 第一打开app设置的标志
     *
     * @param org
     * @return
     */
    public boolean setFirstOppen(boolean org) {
        return PreferencesUtils.putBoolean("isFirstOppenApp", org);
    }

    /**
     * 设置默认登录标签
     *
     * @param login
     * @return
     */
    public boolean setAutoLogin(boolean login) {
        return PreferencesUtils.putBoolean("isAutoLogin", login);
    }


    /**
     * 获得mlds的返回的主地址
     */
    public String getMlds() {
        return PreferencesUtils.getString("mlds", "");
    }

    /**
     * 存储mlds主地址
     *
     * @return
     */
    public void setMlds(String url) {
        PreferencesUtils.putString("mlds", url);
    }

    /**
     * 返回adapter返回的主地址
     *
     * @return
     */
    public String getAdapterUrl() {
        return PreferencesUtils.getString("adapterUrl", "");
    }

    /**
     * 存储adapter的主地址
     *
     * @return
     */
    public void setAdapterUrl(String url) {
        PreferencesUtils.putString("adapterUrl", url);
    }

    /**
     * 获得地址类型来决定使用哪种请求路径
     *
     * @return
     */
    public String getType() {
        return PreferencesUtils.getString("type", "m");
    }

    /**
     * 存儲類型
     *
     * @param type
     */
    public void setType(String type) {
        PreferencesUtils.putString("type", type);
    }

    /**
     * 新信息提示
     *
     * @param alert_msg
     */
    public void setAlertMsg(String alert_msg) {
        PreferencesUtils.putString("alert_msg", alert_msg);
    }

    /**
     * 獲得新信息提示
     *
     * @return
     */
    public String getAlertMsg() {
        return PreferencesUtils.getString("alert_msg", "");
    }

    /**
     * 獲得是否需要提示信息判斷
     *
     * @return
     */
    public boolean getNotShowAlertMsg() {
        return PreferencesUtils.getBoolean("noshow_alert_msg", false);
    }

    /**
     * 是否需要繼續提示信息
     *
     * @param noshow_alert_msg
     */
    public void setNotShowAlertMsg(boolean noshow_alert_msg) {
        PreferencesUtils.putBoolean("noshow_alert_msg", noshow_alert_msg);
    }

    /**
     * 獲得服務器apk版本
     * @return
     */
    public String getServerVersion() {
        return PreferencesUtils.getString("version", "");
    }
}
