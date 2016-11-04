package com.wangzuo.copyproject.business.guide.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.guide.adapter.GuideAdapter;
import com.wangzuo.copyproject.business.guide.inter.GuideInterface;
import com.wangzuo.copyproject.business.guide.presenter.GuidePresenter;
import com.wangzuo.copyproject.business.login.view.LoginActivity;
import com.wangzuo.copyproject.common.base.activity.MVPBaseActivity;
import com.wangzuo.copyproject.common.utils.ActivityUtils;
import com.wangzuo.copyproject.common.utils.InflaterUtils;
import com.wangzuo.copyproject.common.utils.PreferenceDB;

import java.util.ArrayList;

/**
 * Created by hejie on 2016/10/31.
 *
 * 引导页面
 */

public class GuideActivity extends MVPBaseActivity<GuideInterface,GuidePresenter> implements GuideInterface{

    private ViewPager viewPager;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void initView() {
        findView();

        initViewPager();
    }

    /**
     * 初始化Viewpager
     */
    private void initViewPager() {
        ArrayList<View> views = new ArrayList<>();
        views.add(InflaterUtils.inflater(this,R.layout.guide_view_one,null));
        views.add(InflaterUtils.inflater(this,R.layout.guide_view_two,null));
        views.add(InflaterUtils.inflater(this,R.layout.guide_view_three,null));
        views.add(InflaterUtils.inflater(this,R.layout.guide_view_four,null));

        viewPager.setAdapter(new GuideAdapter(views));
    }

    private void findView() {
        viewPager = (ViewPager) baseView.findViewById(R.id.guide_viewpager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.guide_layout;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected GuidePresenter createPresenter() {
        return new GuidePresenter();
    }


    /**
     * 第四页按钮监听
     * @param view
     */
    public void toOtherActivity(View view) {
//        ToastUtils.showToast(this,"开始了");
        PreferenceDB.getInstance().setFirstOppen(true);
        ActivityUtils.startActivity(this, LoginActivity.class);
        ActivityUtils.finishActivity(this);
    }
}
