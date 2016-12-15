package com.wangzuo.copyproject.business.login.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.wangzuo.copyproject.application.ProjectLitePalApplication;
import com.wangzuo.copyproject.business.login.bean.CheckUpdataBean;
import com.wangzuo.copyproject.business.login.view.popup.CheckUpDatePopupWindows;
import com.wangzuo.copyproject.common.utils.GlobalConstants;
import com.wangzuo.copyproject.common.utils.PreferenceDB;

/**
 * Created by hejie on 2016/11/23.
 *
 * 检测更新处理
 *
 */
public class LoadCheckUpdata implements Runnable {

    private Context mContext;
    private Handler mHandler;
    private boolean mForceUpdate;

    public LoadCheckUpdata(Context context, Handler afterHandler, boolean forceUpdate) {
        mContext = context;
        mHandler = afterHandler;
        mForceUpdate = forceUpdate;
    }

    @Override
    public void run() {
        boolean bUpdataApk = false;
        String serverVersion = PreferenceDB.getInstance().getServerVersion();
        bUpdataApk = CheckForUpdates.checkApkVersion(serverVersion, ((ProjectLitePalApplication) mContext.getApplicationContext()).localVersionName);
        if (bUpdataApk) {
            CheckUpdataBean bean = new CheckUpdataBean();
            bean.setVersion(PreferenceDB.getInstance().getServerVersion());
            bean.setUrl(PreferenceDB.getInstance().getServerDurl());
            bean.setDescription(PreferenceDB.getInstance().getServerDescription());
            PreferenceDB.getInstance().setAPKUrl(PreferenceDB.getInstance().getServerDurl());
            Message message = upgradeHandler.obtainMessage();
            message.what = GlobalConstants.UPDATE;
            message.obj = bean;
            upgradeHandler.sendMessage(message);
        } else {
            upgradeHandler.obtainMessage(GlobalConstants.NO_UPDATE).sendToTarget();
        }

        if (mHandler !=null){
            mHandler.obtainMessage(GlobalConstants.AFTER_UPDATE,bUpdataApk).sendToTarget();
        }
    }

    /**
     * 是否有新版本處理
     */
    private Handler upgradeHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case GlobalConstants.UPDATE:
                    if (mHandler != null) {
                        mHandler.obtainMessage(GlobalConstants.UPDATE).sendToTarget();
                    }
                    showUpdataDialog((CheckUpdataBean) message.obj);
                    break;
                case GlobalConstants.NO_UPDATE:
                    if (mHandler != null) {
                        mHandler.obtainMessage(GlobalConstants.NO_UPDATE).sendToTarget();
                    }
                    break;
            }
            return true;
        }
    });

    /**
     * 檢測更新對話框
     *
     * @param obj
     */
    private void showUpdataDialog(CheckUpdataBean obj) {
        CheckUpdataBean checkUpdataBean = obj;
        CheckUpDatePopupWindows checkUpDatePopupWindows;
        if (mForceUpdate) {//
            checkUpDatePopupWindows = new CheckUpDatePopupWindows(mContext, false, checkUpdataBean);
        } else {
            checkUpDatePopupWindows = new CheckUpDatePopupWindows(mContext, true, checkUpdataBean);
        }
        checkUpDatePopupWindows.buid().showPopup(checkUpDatePopupWindows.getContentView());
    }
}
