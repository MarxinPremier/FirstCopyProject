package com.wangzuo.copyproject.common.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wangzuo.copyproject.common.utils.ListUtils;

import java.util.List;

/**
 * Created by hejie on 2016/11/1.
 * <p>
 */

public abstract  class ListAdapter extends BaseAdapter {


    protected final Context mContext;
    protected final List<?> mList;

    public ListAdapter(Context context, List<?> list) {
        super();
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return ListUtils.isEmpty(mList) ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return ListUtils.isEmpty(mList) ? 0 : mList.get(i);

    }

    @Override
    public long getItemId(int i) {
        return ListUtils.isEmpty(mList) ? 0 : i;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View baseView = inflate();
        return baseView;
    }

    public abstract View inflate();
}
