package com.wangzuo.copyproject.business.login.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.login.bean.HistoryOrgBean;
import com.wangzuo.copyproject.business.login.presenter.LoginPresenter;
import com.wangzuo.copyproject.business.login.view.LoginActivity;
import com.wangzuo.copyproject.common.base.adapter.SimpleBaseAdapter;

import java.util.List;

/**
 * Created by hejie on 2016/11/1.
 *
 * 机构列表的适配器
 *
 */

public class OrgAdapter extends SimpleBaseAdapter{

    private LoginPresenter mLoginPresenter;

    public OrgAdapter(Context context, List<?> list,LoginPresenter loginPresenter) {
        super(context, list);
        mLoginPresenter = loginPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.login_org_list_item;
    }

    @Override
    public void initEvent(Object object) {
        HistoryOrgBean orgBean  = (HistoryOrgBean) object;
        String name = orgBean.getName();
        TextView(R.id.login_org_list_tv).setText(name);
        //给每一个删除按钮设置监听
        ImageView(R.id.login_org_list_img).setTag(holder.getPosition());
        ImageView(R.id.login_org_list_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cruPos = (int) ((ImageView)view).getTag();
                //移除数据
                mList.remove(cruPos);
                //更新数据
                notifyDataSetChanged();
                //调用一下litepal进行存储
                mLoginPresenter.upDateOrgName(((List<HistoryOrgBean>)mList).get(cruPos).getId());
            }
        });
    }
}
