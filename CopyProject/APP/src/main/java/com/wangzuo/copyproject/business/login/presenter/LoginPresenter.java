package com.wangzuo.copyproject.business.login.presenter;


import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.application.ProjectLitePalApplication;
import com.wangzuo.copyproject.business.login.bean.HistoryOrgBean;
import com.wangzuo.copyproject.business.login.bean.LoginParams;
import com.wangzuo.copyproject.business.login.bean.UserBean;
import com.wangzuo.copyproject.business.login.inter.LoginInterface;
import com.wangzuo.copyproject.business.login.view.activity.LoginActivity;
import com.wangzuo.copyproject.business.main.view.MainActivity;
import com.wangzuo.copyproject.common.base.presenter.BasePresenter;
import com.wangzuo.copyproject.common.base.view.dialog.BaseLoadDialog;
import com.wangzuo.copyproject.common.utils.ActivityUtils;
import com.wangzuo.copyproject.common.utils.JsonUtils;
import com.wangzuo.copyproject.common.utils.ListUtils;
import com.wangzuo.copyproject.common.utils.PhoneUtils;
import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.common.utils.ResourceUtils;
import com.wangzuo.copyproject.common.utils.StringUtils;
import com.wangzuo.copyproject.common.utils.ToastUtils;
import com.wangzuo.copyproject.component.requestModel.RequestParams;
import com.wangzuo.copyproject.component.requestModel.RequestTask;
import com.wangzuo.copyproject.component.requestModel.StatusConstants;
import com.wangzuo.copyproject.component.requestModel.UrlConstants;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by hejie on 2016/11/1.
 * <p>
 * 登录页面的presenter
 */

public class LoginPresenter extends BasePresenter<LoginInterface> {

    private LoginInterface view;
    private final Context mContext;
    private final BaseLoadDialog mLoadDialog;
    private LoginParams mloginParams;


    public LoginPresenter(Context context, BaseLoadDialog loadDialog) {
        mContext = context;
        mLoadDialog = loadDialog;
    }

    /**
     * 获得机构列表数据
     */
    public void getOrgList(List<HistoryOrgBean> historyOrgBeanList) {
        try {
            List<HistoryOrgBean> historyOrgBeen = DataSupport.order(" createTime desc ").find(HistoryOrgBean.class);
            historyOrgBeanList.addAll(historyOrgBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据库中机构名数据
     */
    public void upDateOrgName(long id) {
        try {
            DataSupport.delete(HistoryOrgBean.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存历史登录机构
     */
    public void savaOrgData() {
        List<HistoryOrgBean> historyOrgBeen = DataSupport.where("name = ? ", mloginParams.getOrg()).find(HistoryOrgBean.class);
        if (ListUtils.isEmpty(historyOrgBeen)) {//原来同名机构没有
            String org = mloginParams.getOrg();
            if (!StringUtils.isEmpty(org)) {
                HistoryOrgBean bean = new HistoryOrgBean(org);
                bean.setMurl(PreferenceDB.getInstance().getMlds());
                bean.setCreateTime(System.currentTimeMillis());
                bean.save();
            }
        } else {//同名机构存在
            HistoryOrgBean orgBean = historyOrgBeen.get(0);
            orgBean.setCreateTime(System.currentTimeMillis());
            orgBean.update(orgBean.getId());
        }
    }

    /**
     * 开始登陆
     *
     * @param loginParams
     */
    public void startLogin(LoginParams loginParams) {

        if (!PhoneUtils.isNetWorkOk(mContext)){
            ToastUtils.showToast(mContext,ResourceUtils.getString(R.string.internet_out));
            return;
        }

        mloginParams = loginParams;
        if ("a".equals(PreferenceDB.getInstance().getType())) {//a类型,只需要机构名
            if (PhoneUtils.isNetWorkOk(mContext)) {//网络状况好不好
                if (StringUtils.isEmpty(loginParams.getOrg())) {
                    ToastUtils.showToast(mContext, ResourceUtils.getString(R.string.login_no_org));
                } else {
                    RequestTask.task(RequestTask.getAurl(UrlConstants.METHOD_LOGIN_MLDS), RequestParams.getMldsLoginParams(loginParams.getOrg()), loginHandler);
                }
            }
        } else if ("m".equals(PreferenceDB.getInstance().getType())) {//m类型，需要全套信息
            Map<String, Object> login = RequestParams.get_LOGIN(
                    loginParams.getOrg(),
                    loginParams.getAccount(),
                    loginParams.getPwd(),
                    loginParams.getModel(),
                    loginParams.getRelease()
            );
            if (mContext instanceof LoginActivity) {
                RequestTask.task(RequestTask.getUrl(UrlConstants.METHOD_LOGIN), login, loginHandler);
            } else {
                RequestTask.task(RequestTask.getUrl(UrlConstants.METHOD_LOGIN), login, loginHandler);

            }
        }
    }


    /**
     * 登录请求的handler
     */
    private Handler loginHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case StatusConstants.REQUEST_WHAT_START://开始登录
                    mLoadDialog.loadDialogShow();
                    break;
                case StatusConstants.REQUEST_WHAT_SUCCESS://登录成功
                    mLoadDialog.loadDialogDismiss();
                    loginSuccess((String) message.obj);
                    break;
                case StatusConstants.REQUEST_WHAT_SERVICE_ERROR://服务错误
                    mLoadDialog.loadDialogDismiss();
                    break;
                case StatusConstants.REQUEST_WHAT_FAILURE://登录失败
                    mLoadDialog.loadDialogDismiss();
                    break;
                case StatusConstants.REQUEST_WHAT_TIMEOUT_ERROR://请求超时
                    mLoadDialog.loadDialogDismiss();
                    ToastUtils.showToast(mContext, ResourceUtils.getString(R.string.time_out));
                    break;
            }
            return true;
        }
    });

    /**
     * 请求成功的处理
     */
    private void loginSuccess(String json) {
        if (StringUtils.isEmpty(json)) {
            return;
        }

        UserBean userBean = JsonUtils.parseToObjectBean(json, UserBean.class);
        userBean.setOrg_name(mloginParams.getOrg());
        userBean.setLogin_name(mloginParams.getAccount());
        userBean.setPassword(mloginParams.getPwd());
        //从新存储
        DataSupport.deleteAll(UserBean.class);
        userBean.save();
        //记录
        ProjectLitePalApplication.org = userBean.getLogin_org();
        ProjectLitePalApplication.user_id = userBean.getMy_id();
        //保存
        savaOrgData();
        //跳转
        ActivityUtils.startActivity(mContext, MainActivity.class);
        ActivityUtils.finishActivity(mContext);

    }
}
