package com.wangzuo.copyproject.business.login.view.Listener;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.login.bean.LoginParams;
import com.wangzuo.copyproject.business.login.presenter.LoginPresenter;
import com.wangzuo.copyproject.common.utils.PhoneUtils;
import com.wangzuo.copyproject.common.utils.ResourceUtils;
import com.wangzuo.copyproject.common.utils.ToastUtils;

/**
 * Created by hejie on 2016/11/1.
 * <p>
 * 登录监听
 */

public class LoginListener implements View.OnClickListener {

    private final EditText mOrgEdit;
    private final EditText mAccountEdit;
    private final EditText mPwdEdit;
    private final Context mContext;
    private final TextView moPhoneTextView;
    private final LoginPresenter mLoginPresenter;

    public LoginListener(Context context,
                         EditText orgEdit,
                         EditText accountEdit,
                         EditText pwdEdit,
                         TextView textView,
                         LoginPresenter loginPresenter) {
        mOrgEdit = orgEdit;
        mAccountEdit = accountEdit;
        mPwdEdit = pwdEdit;
        mContext = context;
        moPhoneTextView = textView;
        mLoginPresenter = loginPresenter;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login_btn://登录按钮
                verifyLogin();
                break;
            case R.id.login_hotline_tv://热线按钮
                call();
                break;
            case R.id.login_forget_tv://找回密码
                forgetPwd();
                break;
        }

    }

    /**
     * 忘记密码
     */
    private void forgetPwd() {

    }

    /**
     * 拨打电话
     */
    private void call() {
        PhoneUtils.phoneCall(mContext,moPhoneTextView.getText().toString());
    }

    /**
     * 登录验证
     */
    private void verifyLogin() {
        String orgTrim = mOrgEdit.getText().toString().trim();
        String accountTrim = mAccountEdit.getText().toString().trim();
        String pwdTrim = mPwdEdit.getText().toString().trim();
        if (TextUtils.isEmpty(orgTrim)) {
            ToastUtils.showToast(mContext, ResourceUtils.getString(R.string.login_no_org));
            return;
        }
        if (TextUtils.isEmpty(accountTrim)) {
            ToastUtils.showToast(mContext, ResourceUtils.getString(R.string.login_no_accout));
            return;
        }
        if (TextUtils.isEmpty(pwdTrim)) {
            ToastUtils.showToast(mContext, ResourceUtils.getString(R.string.login_no_pwd));
            return;
        }
        //登录代码
        LoginParams loginParams = new LoginParams();
        loginParams.setOrg(orgTrim);
        loginParams.setAccount(accountTrim);
        loginParams.setPwd(pwdTrim);
        loginParams.setModel(android.os.Build.MODEL);
        loginParams.setRelease(android.os.Build.VERSION.RELEASE);
        mLoginPresenter.startLogin(loginParams);
    }
}
