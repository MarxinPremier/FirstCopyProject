package com.wangzuo.copyproject.business.login.presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.login.view.popup.ShowAlertMsgPop;
import com.wangzuo.copyproject.common.base.view.dialog.BaseLoadDialog;
import com.wangzuo.copyproject.common.utils.GlobalConstants;
import com.wangzuo.copyproject.common.utils.JsonUtils;
import com.wangzuo.copyproject.common.utils.PhoneUtils;
import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.common.utils.ResourceUtils;
import com.wangzuo.copyproject.common.utils.StringUtils;
import com.wangzuo.copyproject.common.utils.ToastUtils;
import com.wangzuo.copyproject.component.requestModel.RequestTask;
import com.wangzuo.copyproject.component.requestModel.SiteBean;
import com.wangzuo.copyproject.component.requestModel.StatusConstants;
import com.wangzuo.copyproject.component.requestModel.UrlConstants;

import java.util.List;

/**
 * Created by hejie on 2016/11/23.
 * <p>
 * 在登錄的時候需要先獲得機構名，需要請求
 * Config配置
 */

public class LoginOrgPresenter {

    private final Activity mActivity;
    private final BaseLoadDialog mBaseLoadDialog;

    public LoginOrgPresenter(Activity activity, BaseLoadDialog baseLoadDialog) {

        mActivity = activity;
        mBaseLoadDialog = baseLoadDialog;
        sendRequest();
    }

    /**
     * 加載配置請求
     */
    private void sendRequest() {
        if (!PhoneUtils.isNetWorkOk(mActivity)) {
            ToastUtils.showToast(mActivity, ResourceUtils.getString(R.string.internet_out));
            return;
        }
        //請求
        RequestTask.sourceTask(UrlConstants.SERVER_ORGS_URL, null, loginOrgHandler);
    }

    /**
     * config請求配置的handler處理
     */
    private Handler loginOrgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case StatusConstants.REQUEST_WHAT_START://开始请求
                    mBaseLoadDialog.loadDialogShow();
                    break;
                case StatusConstants.REQUEST_WHAT_SUCCESS:// 开始请求配置成功
                    mBaseLoadDialog.loadDialogDismiss();
                    setOrg((String) message.obj);
                    break;
                case StatusConstants.REQUEST_WHAT_SERVICE_ERROR:// 服务器错误
                case StatusConstants.REQUEST_WHAT_FAILURE:// 请求失败
                    ToastUtils.showToast(mActivity, ResourceUtils.getString(R.string.request_fai));
                    mBaseLoadDialog.loadDialogDismiss();
                    break;
                case StatusConstants.REQUEST_WHAT_TIMEOUT_ERROR:
                    ToastUtils.showToast(mActivity, ResourceUtils.getString(R.string.time_out));
                    mBaseLoadDialog.loadDialogDismiss();
                    break;
            }
            return true;
        }
    });

    /**
     * config請求配置成功的處理
     *
     * @param json
     */
    private void setOrg(String json) {
        if (StringUtils.isEmpty(json)) {
            return;
        }
        try {
            PreferenceDB.getInstance().setType(JsonUtils.getKeyResult(json, "type"));
            if (PreferenceDB.getInstance().getType().equals("a")) {
                List<SiteBean> sites = JsonUtils.parseToObjectList(JsonUtils.getKeyResult(json, "sites"), SiteBean.class);
                PreferenceDB.getInstance().setAdapterUrl(sites.get(0).getMurl());
            } else {
                List<SiteBean> sites = JsonUtils.parseToObjectList(JsonUtils.getKeyResult(json, "sites"), SiteBean.class);
                PreferenceDB.getInstance().setMlds(sites.get(0).getMurl());
            }
            ShowAlertMsgPop.judgeAlertMsg(mActivity, JsonUtils.getKeyResult(json, "alert_msg"));
            PreferenceDB.getInstance().setServerQrUrl(JsonUtils.getKeyResult(json, "qrurl"));
            PreferenceDB.getInstance().setServerVersion(JsonUtils.getKeyResult(json, "version"));
            PreferenceDB.getInstance().setServerDescription(JsonUtils.getKeyResult(json, "description"));
            PreferenceDB.getInstance().setServerDsite(JsonUtils.getKeyResult(json, "dsite"));
            PreferenceDB.getInstance().setServerDurl(JsonUtils.getKeyResult(json, "durl"));
            PreferenceDB.getInstance().setServerVDesc(JsonUtils.getKeyResult(json, "vdesc"));
            PreferenceDB.getInstance().setIsUpdata(true);

            if ("2".equalsIgnoreCase(JsonUtils.getKeyResult(json, "reminderFlag"))) {
                PreferenceDB.getInstance().setForceUpdate(true);
            } else {
                PreferenceDB.getInstance().setForceUpdate(false);
            }

            mBaseLoadDialog.loadDialogDismiss();
            requestCheckUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 非自动登录检测更新
     **/
    private void requestCheckUpdate() {
        try {
            new Thread(new LoadCheckUpdata(mActivity, upgradeHandler, PreferenceDB.getInstance().getForceUpdate())).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否有新版本处理
     */
    private Handler upgradeHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case GlobalConstants.AFTER_UPDATE:
                    break;
            }
            return true;
        }
    });
}

