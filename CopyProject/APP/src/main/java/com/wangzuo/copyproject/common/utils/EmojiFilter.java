package com.wangzuo.copyproject.common.utils;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.wangzuo.copyproject.R;


/**
 * Emoji表情过滤
 *
 */
public class EmojiFilter {

    /**
     * 检测是否有emoji字符
     * @param source
     * @return FALSE，包含图片
     */
    public static boolean containsEmoji(String source) {
        if (source.equals("")) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }

    }


    private static String tmp = "";
    // 是否重置了EditText的内容
    private  static boolean resetText ;

    public static void filtEmojiEditText(final Context context,final EditText editText){

        tmp = "";
        resetText = false;

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int arg2, int arg3) {
                if (!resetText) {
                    if ((arg3 == 2) && (!EmojiFilter.containsEmoji(s.toString().substring(start, start + 2)))) {
                        resetText = true;
                        editText.setText(tmp);
                        editText.invalidate();
                        if (editText.getText().length() > 1)
                            Selection.setSelection(editText.getText(), editText.getText().length());
                        Toast.makeText(context, ResourceUtils.getString(R.string.cannot_emoji_input), Toast.LENGTH_SHORT).show();
                        if (editText.getText() instanceof Spannable) {
                            Spannable spanText = (Spannable) editText.getText();
                            Selection.setSelection(spanText, editText.getText().length());
                        }
                    }
                } else {
                    resetText = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!resetText)
                    tmp = s.toString();
            }});
    }
}
