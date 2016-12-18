package com.wangzuo.copyproject.common.view.refresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wangzuo.copyproject.common.utils.InflaterUtils;
import com.wangzuo.copyproject.common.view.refresh.inter.IBaseDataAdapter;

import java.util.List;

/**
 * Created by hejie on 2016/12/16.
 * <p>
 * RecyclerView的普通列表通用adapter
 */

public class RecyclerViewCommonAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final Context mContext;
    private final IBaseDataAdapter mIBaseDataAdapter;
    private final List mData;

    public RecyclerViewCommonAdapter(Context context, IBaseDataAdapter iBaseDataAdapter) {
        mContext = context;
        mIBaseDataAdapter = iBaseDataAdapter;
        mData = mIBaseDataAdapter.getData();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(InflaterUtils.inflater(mContext, mIBaseDataAdapter.getItemLayout()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        //绑定和显示
        mIBaseDataAdapter.displayData(holder, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
