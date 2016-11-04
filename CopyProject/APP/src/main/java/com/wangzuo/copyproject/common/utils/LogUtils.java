package com.wangzuo.copyproject.common.utils;


import android.util.Log;


/**
 * Created by hejie on 2016/11/2.
 * <p>
 * log工具类,使用单例
 */

public class LogUtils {

    public static final String TAG_EXCEPTION = "exception";
    public static final String TAG = "logutils";

    public static LogUtils getInstance() {
        return LogHolder.log;
    }

    private LogUtils() {
    }

    private static class LogHolder {
        private static final LogUtils log = new LogUtils();
    }

    /**
     * 反馈出当前的log信息的具体位置
     *
     * @return
     */
    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement s : sts) {
            if (s.isNativeMethod()) {
                continue;
            }
            if (s.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (s.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            return "[" + Thread.currentThread().getName() + ":" + s.getFileName() + ":" + s.getLineNumber() + "]";
        }
        return null;
    }

    /**
     * 打印info数据
     *
     * @param tag
     * @param info
     */
    public void i(String tag, String info) {
        if (NormalConstants.DEBUG) {

        } else {
            if (NormalConstants.BEDUG_LEVEL <= Log.INFO) {

                String functionName = getFunctionName();
                if (functionName != null) {
                    Log.i(tag, info + "----" + functionName);
                } else {
                    Log.i(tag, "null" + "----" + functionName);
                }
            }
        }
    }

    /**
     * 打印debug数据
     *
     * @param tag
     * @param info
     */
    public void d(String tag, String info) {
        if (NormalConstants.DEBUG) {

        } else {
            if (NormalConstants.BEDUG_LEVEL <= Log.DEBUG) {

                String functionName = getFunctionName();
                if (functionName != null) {
                    Log.d(tag, info + "----" + functionName);
                } else {
                    Log.d(tag, "null" + "----" + functionName);
                }
            }
        }
    }

    /**
     * 打印error数据
     *
     * @param tag
     * @param
     */
    public void e(String tag, Exception e) {
        if (NormalConstants.DEBUG) {

        } else {
            if (NormalConstants.BEDUG_LEVEL <= Log.ERROR) {
                String functionName = getFunctionName();
                if (functionName != null) {
                    Log.e(tag, "error", e);
                } else {
                    Log.e(tag, "null" + "----" + functionName);
                }
            }
        }
    }
}
