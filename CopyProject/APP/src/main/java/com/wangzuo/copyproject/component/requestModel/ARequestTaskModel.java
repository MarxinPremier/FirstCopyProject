package com.wangzuo.copyproject.component.requestModel;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.application.ProjectLitePalApplication;
import com.wangzuo.copyproject.business.login.view.activity.LoginActivity;
import com.wangzuo.copyproject.common.base.view.popup.CenterPopupWindows;
import com.wangzuo.copyproject.common.utils.GlobalConstants;
import com.wangzuo.copyproject.common.utils.JsonConstants;
import com.wangzuo.copyproject.common.utils.JsonUtils;
import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.common.utils.ResourceUtils;

import java.util.Map;

/**
 * Created by hejie on 2016/11/3.
 * <p>
 * 网络请求框架，抽象父类
 */

public abstract class ARequestTaskModel {

    /**
     * 用于记录的当前activity,便于广播信息之后
     * 能用popupwindows通知用户
     */
    public static Activity currentActivity;

    /**
     * 向广播发送信息,提示重新登录
     *
     * @param code
     * @param data
     * @param msg
     */
    protected static void sendBroadcastToLogin(String code, String data, String msg) {
        if (JsonConstants.CODE_VALUE_0504.equals(code)) {//需要跳转登录
            String client_type = JsonUtils.getKeyResult(data, "client_type");
            if ("6".equals(client_type)) {
                client_type = "android";
            } else {
                client_type = "ios";
            }
            msg = ResourceUtils.getString(R.string.your_account) +
                    JsonUtils.getKeyResult(data, "time") +
                    ResourceUtils.getString(R.string.another_phone) +
                    client_type +
                    ResourceUtils.getString(R.string.device_verify);
        } else if (JsonConstants.CODE_VALUE_0003.equals(code)) {
            msg = ResourceUtils.getString(R.string.login_time_out);
        } else {
            return;
        }

        //注册广播
        ProjectLitePalApplication.getContext().registerReceiver(noLoginReceiver, new IntentFilter(GlobalConstants.UNLOGIN_ACTION));
        Intent broadCast = new Intent();
        broadCast.setAction(GlobalConstants.UNLOGIN_ACTION);
        broadCast.putExtra("unlogin", "unlogin");
        broadCast.putExtra("message", msg);
        ProjectLitePalApplication.getContext().sendBroadcast(broadCast);
    }

    /**
     * 静态广播,用于处理未登录情况
     */
    static BroadcastReceiver noLoginReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            synchronized (BroadcastReceiver.class) {//防止线程并发
                if ("unlogin".equals(intent.getStringExtra("unlogin"))) {
                    String message = intent.getStringExtra("message");
                    gotoLogin(message);
                }
            }
        }
    };

    /**
     * 去登陆
     *
     * @param message
     */
    private static void gotoLogin(final String message) {
        try {
            final CenterPopupWindows centerPopupWindows = new CenterPopupWindows(currentActivity, false) {
                @Override
                protected String getLeftText() {
                    return null;
                }

                @Override
                protected String getRightText() {
                    return ResourceUtils.getString(R.string.reload_hint);
                }

                @Override
                protected String getContent() {
                    return ResourceUtils.getString(R.string.popup_hint);
                }

                @Override
                protected void rightEvent() {
                    try {
                        PreferenceDB.getInstance().setAutoLogin(false);
                        Intent turnIntent = new Intent(ProjectLitePalApplication.mContext, LoginActivity.class);
                        turnIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        turnIntent.putExtra("isReLogin",true);
                        ProjectLitePalApplication.mContext.startActivity(turnIntent);
                        System.exit(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void leftEvent() {

                }

                @Override
                protected String getTitle() {
                    return message;
                }
            };
            centerPopupWindows.showPopup(centerPopupWindows.getContentView());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get请求 返回原始请求数据
     *
     * @param url     请求url
     * @param params  请求参数
     * @param handler 请求回调处理handler
     */
    protected abstract void sourceTask(String url, Map<String, Object> params, Handler handler);

    /**
     * post请求
     *
     * @param url     请求url
     * @param params  请求参数
     * @param handler 请求回调处理handler
     */
    protected abstract void task(String url, Map<String, Object> params, Handler handler,Integer... intArg);

    /**
     * @param url
     * @param params
     * @param handler
     */
    protected abstract void httpParams(String url, Map<String, Object> params, Handler handler);


}
