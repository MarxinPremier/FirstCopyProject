package com.wangzuo.copyproject.business.login.presenter;


import com.wangzuo.copyproject.business.login.bean.HistoryOrgBean;
import com.wangzuo.copyproject.business.login.inter.LoginInterface;
import com.wangzuo.copyproject.common.base.presenter.BasePresenter;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by hejie on 2016/11/1.
 * <p>
 * 登录页面的presenter
 */

public class LoginPresenter extends BasePresenter<LoginInterface> {


    private LoginInterface view;

    public LoginPresenter() {

    }

    /**
     * 获得机构列表数据
     */
    public void getOrgList(List<HistoryOrgBean> historyOrgBeanList) {
        try {
            List<HistoryOrgBean> historyOrgBeen = DataSupport.order(" createTime desc ").find(HistoryOrgBean.class);
            historyOrgBeanList.addAll(historyOrgBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据库中机构名数据
     */
    public void upDateOrgName(long id) {
        try {
            DataSupport.delete(HistoryOrgBean.class,id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
