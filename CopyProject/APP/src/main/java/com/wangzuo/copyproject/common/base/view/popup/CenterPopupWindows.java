package com.wangzuo.copyproject.common.base.view.popup;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.common.utils.InflaterUtils;

/**
 * Created by hejie on 2016/11/3.
 * <p>
 * 系统提示弹出框
 * 1.两种样式
 * --- 一种是两个按钮的
 * --- 一种是一个按钮的
 * 2.需要实现抽象方法,来设定title
 * content
 * 按钮的监听事件
 */

public abstract class CenterPopupWindows extends PopupWindow {

    private View mContentView;
    private TextView title, content;
    private TextView leftBtn, rightBtn;

    /**
     * 默认构造
     * 根据标签选择使用一个按钮的布局还是两个按钮的布局
     *
     * @param context
     * @param twoBtn
     */
    public CenterPopupWindows(Context context, boolean twoBtn) {
        this(twoBtn ?
                        InflaterUtils.inflater(context, R.layout.common_center_popup_view) :
                        InflaterUtils.inflater(context, R.layout.common_center_popup_single_view),
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                twoBtn);

    }

    /**
     * 初始化构造方法
     *
     * @param contentView
     * @param width
     * @param height
     * @param twoBtn
     */
    private CenterPopupWindows(View contentView, int width, int height, boolean twoBtn) {
        super(contentView, width, height, true);
        mContentView = contentView;
        setAnimationStyle(android.R.style.Animation_Dialog);
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setBackgroundDrawable(contentView.getResources().getDrawable(R.drawable.common_pubic_bg_half));

        //初始化title
        title = (TextView) contentView.findViewById(R.id.title);
        title.setText(getTitle());
        //初始化content
        content = (TextView) contentView.findViewById(R.id.content);
        content.setText(getContent());
        //设定按键监听
        rightBtn = (TextView) contentView.findViewById(R.id.rightBtn);
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CenterPopupWindows.this.dismiss();
                rightEvent();
            }
        });
        if (twoBtn) {
            //双按钮时，需要继续设定左边按键监听
            leftBtn = (TextView) contentView.findViewById(R.id.leftBtn);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CenterPopupWindows.this.dismiss();
                    leftEvent();
                }
            });
        } else {//单按钮时需要给右边按键设置文本
            rightBtn.setText(getRightText());
        }
    }

    /**
     * 给右边按键设置文本
     *
     * @return
     */
    protected abstract String getRightText();


    /**
     * 显示popupWindows
     *
     * @param parent
     */
    public void showPopup(View parent) {
        super.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    /**
     * 获得内容抽象方法
     *
     * @return
     */
    protected abstract String getContent();

    /**
     * 右边监听的事件处理
     * 1.当按钮只有一个时候就给这个方法实现即可
     * 2.当按钮有两个的时候，这个代表右边按钮
     */
    protected abstract void rightEvent();

    /**
     * 左边监听的事件处理
     */
    protected abstract void leftEvent();

    /**
     * 获得标题
     *
     * @return
     */
    protected abstract String getTitle();


}
