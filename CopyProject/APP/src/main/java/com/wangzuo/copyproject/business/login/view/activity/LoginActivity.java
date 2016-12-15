package com.wangzuo.copyproject.business.login.view.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.login.adapter.OrgAdapter;
import com.wangzuo.copyproject.business.login.bean.HistoryOrgBean;
import com.wangzuo.copyproject.business.login.inter.LoginInterface;
import com.wangzuo.copyproject.business.login.presenter.LoginOrgPresenter;
import com.wangzuo.copyproject.business.login.presenter.LoginPresenter;
import com.wangzuo.copyproject.business.login.view.EditTextLayout;
import com.wangzuo.copyproject.business.login.view.Listener.LoginButtonDisplayWatcher;
import com.wangzuo.copyproject.business.login.view.Listener.LoginListener;
import com.wangzuo.copyproject.common.base.activity.MVPBaseActivity;
import com.wangzuo.copyproject.common.base.view.dialog.BaseLoadDialog;
import com.wangzuo.copyproject.common.utils.ActivityUtils;
import com.wangzuo.copyproject.common.utils.AnimUtils;
import com.wangzuo.copyproject.common.utils.ResourceUtils;
import com.wangzuo.copyproject.common.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by hejie on 2016/11/1.
 * <p>
 * 登录页面
 * 1.密码输入框变成密文
 * 2.机构输入框的列表
 * 3.机构输入框更多按钮的隐藏情况
 * --- 箭头点击状态反转
 * --- 列表的数据清空时会让布局消失
 * 4.输入框与登录按钮的联动
 * 5.logo动画
 * 6.找回密码跳转
 * 7.拨打电话跳转
 * 8.机构名的数据库存储时间问题
 * --- 删除一条机构数据就进行一次数据更新
 * --- 登录成功也要进行一次机构数据更新(注意查重)
 * 9.键盘监听
 * --- 检测是否退出
 * 10.自动填写问题
 */

public class LoginActivity extends MVPBaseActivity<LoginInterface, LoginPresenter> implements LoginInterface, AdapterView.OnItemClickListener {

    private ImageView logoImg;
    private EditTextLayout orgEditLayou;
    private EditTextLayout accountEditLayout;
    private EditTextLayout pwdEditLayout;
    private Button loginbtn;
    private TextView forgetTv;
    private TextView hotLine;
    private ListView orgList;
    private EditText orgEdit;
    private EditText accountEdit;
    private EditText pwdEdit;
    private ImageView orgDownMoreBtn;
    private LoginButtonDisplayWatcher watcher;
    private LoginListener loginListener;
    private ArrayList<HistoryOrgBean> arrayList = new ArrayList<>();
    private long lastTime;//记录上次按下回退键的时间
    private boolean isReLogin;
    private String id;
    private String type;
    private BaseLoadDialog loadDailog ;

    @Override
    protected void initView() {

        findView();

        initEdit();

        initLogoAnim();

        initLoginBtn();

        initOrgList();

        initLoginUrl();
    }

    /**
     * 初始化url的config配置请求
     */
    private void initLoginUrl() {

        new LoginOrgPresenter(this,loadDailog);

    }


    /**
     * 初始化机构列表
     */
    private void initOrgList() {
        arrayList.clear();
        mPresenter.getOrgList(arrayList);
        orgList.setAdapter(new OrgAdapter(this, arrayList, mPresenter));
        //模拟数据
        arrayList.add(new HistoryOrgBean("diyitiao"));
        ((OrgAdapter) orgList.getAdapter()).notifyDataSetChanged();
        orgDownMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMoreOrgList();
            }
        });
        orgList.setOnItemClickListener(this);
    }

    /**
     * 根据情况显示结构列表与否
     */
    private void showMoreOrgList() {
        if (orgList.getVisibility() == View.GONE) {
            orgList.setVisibility(View.VISIBLE);
            orgDownMoreBtn.setImageResource(R.drawable.main_login_txt_up);
        } else if (orgList.getVisibility() == View.VISIBLE) {
            orgList.setVisibility(View.GONE);
            orgDownMoreBtn.setImageResource(R.drawable.main_login_txt_down);
        }
    }

    /**
     * 初始化btn
     */
    private void initLoginBtn() {
        //绑定监听器
        watcher = new LoginButtonDisplayWatcher(orgEdit, accountEdit, pwdEdit, loginbtn);
        orgEdit.addTextChangedListener(watcher);
        accountEdit.addTextChangedListener(watcher);
        pwdEdit.addTextChangedListener(watcher);
        //登录监听
        loginListener = new LoginListener(this, orgEdit, accountEdit, pwdEdit, hotLine, mPresenter);
        loginbtn.setOnClickListener(loginListener);
        //电话热线
        hotLine.setOnClickListener(loginListener);
        //忘记密码
        forgetTv.setOnClickListener(loginListener);
    }

    /**
     * 初始化logo的动画
     */
    private void initLogoAnim() {
        AnimUtils.enLarge(this, logoImg);
    }

    /**
     * 初始化编辑框的一些属性
     */
    private void initEdit() {
        //机构输入框
        orgEdit = orgEditLayou.getEditText();
        orgDownMoreBtn = orgEditLayou.getDownMoreBtn();
        orgDownMoreBtn.setVisibility(View.VISIBLE);
        //账号输入框
        accountEdit = accountEditLayout.getEditText();
        //密码输入框 --- 密文形式
        pwdEdit = pwdEditLayout.getEditText();
        pwdEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        pwdEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    /**
     * 找到控件
     */
    private void findView() {
        logoImg = (ImageView) findViewById(R.id.login_logo_img);
        orgEditLayou = (EditTextLayout) findViewById(R.id.login_org_edit_text);
        accountEditLayout = (EditTextLayout) findViewById(R.id.login_name_edit_text);
        pwdEditLayout = (EditTextLayout) findViewById(R.id.login_pwd_edit_text);
        loginbtn = (Button) findViewById(R.id.login_btn);
        forgetTv = (TextView) findViewById(R.id.login_forget_tv);
        hotLine = (TextView) findViewById(R.id.login_hotline_tv);
        orgList = (ListView) findViewById(R.id.login_org_list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_layout;
    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            isReLogin = intent.getBooleanExtra("isReLogin", false);
            id = intent.getStringExtra("id");
            type = intent.getStringExtra("type");
        }
    }

    @Override
    protected void initEvent() {}

    @Override
    protected LoginPresenter createPresenter() {
        loadDailog = new BaseLoadDialog(this);
        return new LoginPresenter(this, loadDailog);
    }

    @Override
    public Context getContext() {
        return this;
    }


    /**
     * 机构列表点击事件用于重新选择机构名
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HistoryOrgBean orgBean = arrayList.get(i);
        String name = orgBean.getName();
        orgEdit.setText(name);
        showMoreOrgList();
    }


    /**
     * 重写回退键的方法
     */
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - lastTime) > 3000) {//大于3秒就重新按下
            lastTime = System.currentTimeMillis();
            ToastUtils.showToast(this,ResourceUtils.getString(R.string.common_app_exit_hint));
        } else {
            ActivityUtils.finishActivity(this);
            System.exit(0);
        }
    }
}
