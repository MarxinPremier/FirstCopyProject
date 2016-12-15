package com.wangzuo.copyproject.business.login.view.Listener;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by hejie on 2016/11/1.
 *
 * 根据edittext的焦点问题显示删除按钮的问题
 */

public class EditTextFocusListener implements View.OnFocusChangeListener{

    private final EditText mEditText;
    private final ImageView mBtn;

    public EditTextFocusListener(EditText editText, ImageView btn){
        mEditText = editText;
        mBtn = btn;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b&&!TextUtils.isEmpty(mEditText.getText().toString())){
            mBtn.setVisibility(View.VISIBLE);
        }else {
            mBtn.setVisibility(View.GONE);
        }
    }
}
