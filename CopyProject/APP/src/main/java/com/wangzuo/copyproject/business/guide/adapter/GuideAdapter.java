package com.wangzuo.copyproject.business.guide.adapter;

import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by hejie on 2016/10/31.
 *
 * 引导页的viewpager的适配器
 *
 */

public class GuideAdapter extends PagerAdapter{

    private ArrayList<View> mDatas ;

    public GuideAdapter(ArrayList<View> datas){
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mDatas.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mDatas.get(position));
    }
}
