package com.wangzuo.copyproject.business.start.presenter;

import com.wangzuo.copyproject.business.start.inter.StartActivityInterface;
import com.wangzuo.copyproject.common.base.presenter.BasePresenter;

/**
 * Created by hejie on 2016/10/28.
 *
 * 启动页的业务逻辑
 *
 */

public class StartDisplayPresenter extends BasePresenter<StartActivityInterface>{

    private TimeHandler mTimeHandler;

    /**
     * 开启计时器
     */
    public void startCounter(){
        mTimeHandler.startCounter();
    }

    @Override
    public void attachView(StartActivityInterface view) {
        super.attachView(view);
        mTimeHandler = new TimeHandler(getView());
    }
}
