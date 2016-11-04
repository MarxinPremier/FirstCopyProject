package com.wangzuo.copyproject.business.start.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.start.inter.StartActivityInterface;
import com.wangzuo.copyproject.business.start.presenter.StartDisplayPresenter;
import com.wangzuo.copyproject.common.base.activity.MVPBaseActivity;

/**
 * Created by hejie on 2016/10/28.
 * <p>
 * 启动页
 */

public class StartActivity extends MVPBaseActivity<StartActivityInterface,StartDisplayPresenter>
        implements StartActivityInterface{

    private ImageView startImg;
    private TextView startSecondsTv;

    @Override
    protected void initEvent() {
        mPresenter.startCounter();
    }

    @Override
    protected void initView() {
        findView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.start_layout;
    }

    @Override
    protected void initIntent() {

    }

    /**
     * 找到控件
     */
    private void findView() {
        startImg = (ImageView) baseView.findViewById(R.id.start_img);
        startSecondsTv = (TextView) baseView.findViewById(R.id.start_seconds_tv);
    }

    @Override
    protected StartDisplayPresenter createPresenter() {
        return new StartDisplayPresenter();
    }

    /**
     * 用于回调更新textview数据
     * @param string
     */
    @Override
    public void setText(String string) {
        if (startSecondsTv != null){
            startSecondsTv.setText(string);
        }
    }


    /**
     * 获取activity的Context
     * @return
     */
    @Override
    public Context getContext() {
        return this;
    }
}
