package com.wangzuo.copyproject.business.login.view;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hejie on 2016/11/1.
 * <p>
 * 输入框与按钮显示的观察者
 */

public class LoginButtonDisplayWatcher implements TextWatcher {

    private final EditText mEditText1;
    private final EditText mEditText2;
    private final EditText mEditText3;
    private final Button mBtn;

    public LoginButtonDisplayWatcher(EditText editText1, EditText editText2, EditText editText3, Button button) {
        mEditText1 = editText1;
        mEditText2 = editText2;
        mEditText3 = editText3;
        mBtn = button;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String trim1 = mEditText1.getText().toString().trim();
        String trim2 = mEditText2.getText().toString().trim();
        String trim3 = mEditText3.getText().toString().trim();
        if (!TextUtils.isEmpty(trim1) &&
                !TextUtils.isEmpty(trim2) &&
                !TextUtils.isEmpty(trim3)) {
            mBtn.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            mBtn.setTextColor(Color.parseColor("#5FFFFFFF"));
        }
    }
}
