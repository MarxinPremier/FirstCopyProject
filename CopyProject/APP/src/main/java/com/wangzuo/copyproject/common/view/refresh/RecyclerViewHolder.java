package com.wangzuo.copyproject.common.view.refresh;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by hejie on 2016/12/16.
 * <p>
 * recyclerView的通用ViewHolder
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    //view池
    private SparseArray<View> mViews;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    /**
     * 获得item中的子布局
     *
     * @param viewId 子布局的id
     * @return
     */
    public View getView(int viewId) {
        //先从view池里寻找
        View viewById = mViews.get(viewId);
        if (viewById != null) {//找到直接返回
            return viewById;
        } else {//没找到
            //从item中寻找
            viewById = itemView.findViewById(viewId);
            //做缓存
            mViews.put(viewId, viewById);
            return viewById;
        }
    }
}
