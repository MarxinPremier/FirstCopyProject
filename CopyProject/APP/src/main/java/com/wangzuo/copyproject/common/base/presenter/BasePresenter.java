package com.wangzuo.copyproject.common.base.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by hejie on 2016/10/31.
 * <p>
 * Presenter的基类
 */

public abstract class BasePresenter<T> {


    protected Reference<T> mViewRef; //View类型的接口类型

    /**
     * 与view建立连接
     *
     * @param view view模块
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);//弱引用，防止activity的内存泄漏
    }

    /**
     * 获得view引用
     *
     * @return
     */
    protected T getView() {
        return mViewRef.get();
    }

    /**
     * 判断view是否连接
     *
     * @return 连接与否
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 与view断开连接，防止内存泄漏
     */
    public void detachView(){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
