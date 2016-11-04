package com.wangzuo.copyproject.common.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.wangzuo.copyproject.common.base.presenter.BasePresenter;
import com.wangzuo.copyproject.common.utils.InflaterUtils;
import com.wangzuo.copyproject.common.utils.NormalConstants;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by hejie on 2016/10/31.
 *
 * mvp架构的activity的基类
 * 用于与presenter的耦合和解耦
 *
 */

public abstract class MVPBaseActivity<V,T extends BasePresenter<V>> extends AutoLayoutActivity{

    protected T mPresenter; //presenter对象
    protected View baseView;
    /**
     * 在这里与presenter建立连接
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPresenter();

        initIntent();

        initBaseView();

        initImmersion();

        initView();

        initEvent();
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化父布局
     */
    protected void initBaseView(){
        baseView = InflaterUtils.inflater(this,getLayoutId());
        setContentView(baseView);
    }

    /**
     * 获得初始化布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化intent
     */
    protected abstract void initIntent();

    /**
     * 初始化presenter
     */
    private void initPresenter() {
        //创建presenter
        mPresenter = createPresenter();
        //建立连接
        mPresenter.attachView((V) this);
    }


    /**
     * 初始化事件
     */
    protected abstract void initEvent();


    /**
     * 销毁生命周期,同时与presenter断开连接
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //断开连接
        mPresenter.detachView();
    }

    protected abstract T createPresenter();


    /**
     * 初始化沉浸式
     */
    private void initImmersion() {
        if (NormalConstants.IMMERSION == NormalConstants.IMMERSION_YES){//使用
            //状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }else if (NormalConstants.IMMERSION == NormalConstants.IMMERSION_NO){//不使用

        }
    }
}
