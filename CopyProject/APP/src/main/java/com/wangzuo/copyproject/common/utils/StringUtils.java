package com.wangzuo.copyproject.common.utils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by hejie on 2016/11/2.
 * <p>
 * 字符串操作的工具类
 */

public class StringUtils {

    /**
     * 改变字符串内容
     *
     * @param originStr  原始的父字符串内容
     * @param replaceStr 要替换的子字符串
     * @param changeStr  被替换的子字符串
     * @return
     */
    public static String changeText(String originStr, String replaceStr, String changeStr) {
        String replace = originStr.replace(changeStr, replaceStr);
        return replace;
    }

    /**
     * 改变字符串的子内容 并且将改变的内容设置成特定的颜色
     *
     * @param originStr  原始的父字符串
     * @param replaceStr 替换的子字符串
     * @param changeStr  被替换的子字符串
     * @param colorId    改变的内容需要设置成的颜色
     * @param textView   需要显示内容的控件
     * @return
     */
    public static void changeTextWithColor(String originStr, String replaceStr, String changeStr, int colorId, TextView textView) {
        String s = changeText(originStr, replaceStr, changeStr);
        int start = s.indexOf(replaceStr);
        int end = replaceStr.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(s);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(colorId), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableStringBuilder);
    }

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (string == null)
            return true;
        if ("".equals(string.trim()))
            return true;
        return false;
    }


    /**
     * 比较两字符串是否相等
     * @param actual
     * @param expected
     * @return
     */
    public static boolean isEquals(String actual, String expected) {
        return actual == expected || (actual == null ? expected == null : actual.equals(expected));
    }
}
