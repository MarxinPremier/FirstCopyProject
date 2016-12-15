package com.wangzuo.copyproject.business.login.view.popup;

import android.content.Context;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.common.base.view.popup.CenterPopupWindows;
import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.common.utils.ResourceUtils;

/**
 * Created by hejie on 2016/11/23.
 * <p>
 * 登錄提示彈出框使用的
 */

public class LoginPopupWindows extends CenterPopupWindows {


    /**
     * 默认构造
     * 根据标签选择使用一个按钮的布局还是两个按钮的布局
     *
     * @param context
     * @param twoBtn
     */
    public LoginPopupWindows(Context context, boolean twoBtn) {
        super(context, twoBtn);

    }

    @Override
    protected String getLeftText() {
        return ResourceUtils.getString(R.string.main_info_txt_every);
    }

    @Override
    protected String getRightText() {
        return ResourceUtils.getString(R.string.main_info_txt_knows);
    }

    @Override
    protected String getContent() {
        return PreferenceDB.getInstance().getAlertMsg();
    }

    @Override
    protected void rightEvent() {
        dismiss();
    }

    @Override
    protected void leftEvent() {
        PreferenceDB.getInstance().setNotShowAlertMsg(true);
        dismiss();
    }

    @Override
    protected String getTitle() {
        return ResourceUtils.getString(R.string.main_info_txt_notety);
    }

}
