package com.wangzuo.copyproject.business.login.view.popup;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;

import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.common.utils.StringUtils;

/**
 * Created by hejie on 2016/11/23.
 * <p>
 * 登錄彈出提示框
 */

public class ShowAlertMsgPop {

    public static Context mContext;

    /**
     * 對提示信息進行判斷
     *
     * @param context
     * @param alert_msg
     */
    public static void judgeAlertMsg(Context context, String alert_msg) {
        mContext = context;
        alert_msg = StringUtils.isEmpty(alert_msg) ? "" : alert_msg;

        if (alert_msg.equals(PreferenceDB.getInstance().getAlertMsg())) {
            if (!StringUtils.isEmpty(alert_msg)) {
                //TODO:為甚們需要一個彈出框的顯示與否判斷？
                if (!PreferenceDB.getInstance().getNotShowAlertMsg()) {
                    showAlertMsg();
                }
            }
        } else {
            if (!StringUtils.isEmpty(alert_msg)) {
                PreferenceDB.getInstance().setNotShowAlertMsg(false);
                PreferenceDB.getInstance().setAlertMsg(alert_msg);
                showAlertMsg();
            }
        }
    }

    /**
     * 延時
     */
    private static void showAlertMsg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showAlertMsgHandler.obtainMessage(0).sendToTarget();
            }
        }).start();
    }

    /**
     * 創建一個popupwindows提示信息
     */
    static Handler showAlertMsgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            try {
                LoginPopupWindows loginPopupWindows = new LoginPopupWindows(mContext, true);
                loginPopupWindows.buid();
                loginPopupWindows.getContentTv().setGravity(Gravity.LEFT);
                loginPopupWindows.getTitleTv().setVisibility(View.VISIBLE);
                loginPopupWindows.showPopup(loginPopupWindows.getContentView());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    });
}
