package com.wangzuo.copyproject.common.base.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.common.utils.DisplayUtils;

/**
 * Created by hejie on 2016/11/2.
 *
 * 加载更多自定义框
 *
 */

public class DiyDialog extends Dialog implements LoadDialogInterface{

    private static int default_width = 160;//默认宽度
    private static int default_height = 120;//默认高度
    private TextView hint;

    public DiyDialog(Context context) {
        this(context,default_width,default_height, R.layout.common_dialog_layout,R.style.AppTheme);
    }

    public DiyDialog(Context context,int width,int height,int layoutId,int style){
        super(context,style);
        setContentView(layoutId);
        hint = (TextView) findViewById(R.id.dialog_tv);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = DisplayUtils.dp2px(context, width);
        attributes.height = DisplayUtils.dp2px(context, height);
        attributes.gravity = Gravity.CENTER;
        window.setAttributes(attributes);
    }

    /**
     * 不可点击
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    /**
     * 重写回退键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (isShowing()){
                dismiss();
            }
        }
        return true;
    }

    @Override
    public void loadHint(String hintStr) {
        hint.setText(hintStr);
    }

    @Override
    public void loadDialogDismiss() {
        if (isShowing()){
            dismiss();
        }
    }

    @Override
    public void loadDialogShow() {
        if (!isShowing()){
            show();
        }
    }
}
