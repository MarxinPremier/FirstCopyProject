package com.wangzuo.copyproject.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/**
 * Created by hejie on 2016/11/5.
 * <p>
 * 系统工具类
 */

public class PhoneUtils {

    /**
     * 检测网络状况是否好
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkOk(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo == null ? false : true;
    }

    /**
     * 拨打电话的代码
     * @return
     */
    public static void phoneCall(Context mContext,String phoneNum){

        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:"+phoneNum+""));
        ActivityUtils.startActivity(mContext,intent);

    }

}
