package com.wangzuo.copyproject.business.login.view.Listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.IllegalFormatCodePointException;

/**
 * Created by hejie on 2016/11/1.
 *
 * 用于控制删除按钮的显示问题
 *
 *
 */

public class ButtnDisplayWatcher implements TextWatcher{


    private final EditText mEditText;
    private final ImageView mBtn;

    public ButtnDisplayWatcher(EditText editText, ImageView btn){
        mEditText = editText;
        mBtn = btn;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().trim().length() != 0){
            mBtn.setVisibility(View.VISIBLE);
        }else {
            mBtn.setVisibility(View.GONE);
        }
    }
}
