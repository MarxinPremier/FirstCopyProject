package com.wangzuo.copyproject.business.login.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.common.utils.EmojiFilter;
import com.wangzuo.copyproject.common.utils.InflaterUtils;

/**
 * Created by hejie on 2016/11/1.
 * <p>
 * 登录编辑栏控件
 * 1.输入栏有表情过滤
 * 2.按钮的焦点显示
 * 3.按钮的内容显示
 * 4.删除按钮的清空监听
 *
 */

public class EditTextLayout extends RelativeLayout {

    private final Context mContext;
    private View baseView;
    private TextView nameTv;
    private EditText editText;
    private ImageView delBtn;
    private ImageView downMoreBtn;
    private String hintString;
    private TypedArray typedArray;
    private String nameStr;

    public EditTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    /**
     * 初始化
     */
    private void init(AttributeSet attributeSet) {

        initDeclareStyle(attributeSet);

        findView();

        initName();

        initEditText();

        initDelBtn();

        initMoreBtn();
    }

    /**
     * 初始化更多按钮
     */
    private void initMoreBtn() {
        downMoreBtn.setVisibility(GONE);
    }

    /**
     *  初始化删除按钮
    */
    private void initDelBtn() {
        delBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //清空编辑框
                editText.setText("");
            }
        });
    }

    /**
     * 初始化edittext
     */
    private void initEditText() {
        //设置提示语
        if (!TextUtils.isEmpty(hintString)) {
            editText.setHint(hintString);
        }
        //设置焦点按钮问题
        editText.setOnFocusChangeListener(new EditTextFocusListener(editText,delBtn));
        editText.addTextChangedListener(new ButtnDisplayWatcher(editText,delBtn));
        //表情过滤
        EmojiFilter.filtEmojiEditText(mContext,editText);
    }

    /**
     * 初始化name
     */
    private void initName() {
        if (!TextUtils.isEmpty(nameStr)) {
            nameTv.setText(nameStr);
        }
    }

    /**
     * 初始化属性
     */
    private void initDeclareStyle(AttributeSet attributeSet) {
        typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.EditTextLayout);
        hintString = typedArray.getString(R.styleable.EditTextLayout_editHint);
        nameStr = typedArray.getString(R.styleable.EditTextLayout_editName);
    }

    /**
     * 找到控件
     */
    private void findView() {
        baseView = InflaterUtils.inflater(mContext, R.layout.login_edit_layou, this);
        nameTv = (TextView) baseView.findViewById(R.id.login_edit_name);
        editText = (EditText) baseView.findViewById(R.id.login_edit_editText);
        delBtn = (ImageView) baseView.findViewById(R.id.login_edit_del_btn);
        downMoreBtn = (ImageView) baseView.findViewById(R.id.login_edit_down_btn);
    }

    /**
     * 将editText暴露
     * @return
     */
    public EditText getEditText() {
        return editText;
    }

    /**
     * 将列表按钮暴露
     * @return
     */
    public ImageView getDownMoreBtn() {
        return downMoreBtn;
    }
}
