package com.wangzuo.copyproject.business.login.view.popup;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.login.bean.CheckUpdataBean;
import com.wangzuo.copyproject.common.base.view.popup.CenterPopupWindows;
import com.wangzuo.copyproject.common.utils.DisplayUtils;
import com.wangzuo.copyproject.common.utils.ResourceUtils;
import com.wangzuo.copyproject.common.utils.StringUtils;
import com.wangzuo.copyproject.component.service.UpdataService;

/**
 * Created by hejie on 2016/11/23.
 * <p>
 * 檢測更新的彈出框
 */

public class CheckUpDatePopupWindows extends CenterPopupWindows {

    private CheckUpdataBean mCheckUpdataBean;
    private final Context mContext;


    /**
     * 默认构造
     * 根据标签选择使用一个按钮的布局还是两个按钮的布局
     *
     * @param context
     * @param twoBtn  為真就是兩個，為假就是一個按鈕
     */
    public CheckUpDatePopupWindows(Context context, boolean twoBtn, CheckUpdataBean checkUpdataBean) {
        super(context, twoBtn);
        mContext = context;
        mCheckUpdataBean = checkUpdataBean;
    }

    @Override
    protected String getLeftText() {
        return ResourceUtils.getString(R.string.common_app_new_version_ignore);
    }

    @Override
    protected String getRightText() {
        return ResourceUtils.getString(R.string.common_app_new_version_update);
    }

    @Override
    protected String getContent() {
        getContentTv().setGravity(Gravity.LEFT);
        if (StringUtils.isEmpty(mCheckUpdataBean.getDescription())) {
            getContentTv().setVisibility(View.GONE);
            return "";
        } else {
            getContentTv().setVisibility(View.VISIBLE);
            getContentTv().setPadding(
                    DisplayUtils.dp2px(mContext, 10),
                    DisplayUtils.dp2px(mContext, 5),
                    DisplayUtils.dp2px(mContext, 10),
                    DisplayUtils.dp2px(mContext, 5)
            );
            String s = mCheckUpdataBean.getDescription().replaceAll("；", "\n");
            return s;
        }
    }

    @Override
    protected void rightEvent() {
        Intent intent = new Intent();
        intent.setClass(mContext, UpdataService.class);
        mContext.startService(intent);
        dismiss();
    }

    @Override
    protected void leftEvent() {
        dismiss();
    }

    @Override
    protected String getTitle() {
        return ResourceUtils.getString(R.string.common_app_new_version) + mCheckUpdataBean.getVersion();
    }
}
