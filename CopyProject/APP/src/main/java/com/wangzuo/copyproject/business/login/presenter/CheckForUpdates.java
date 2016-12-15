package com.wangzuo.copyproject.business.login.presenter;

/**
 * Created by hejie on 2016/11/23.
 * <p>
 * 檢測更新
 */

public class CheckForUpdates {

    /**
     * 比較服務器版本和本地版本
     * @param serverVersion
     * @param localVersion
     * @return
     */
    public static  boolean checkApkVersion(String serverVersion, String localVersion) {
        int sVersion = getVersion(serverVersion);
        if (sVersion == -1) {
            return false;
        }
        int lVersion = getVersion(localVersion);
        if (lVersion == -1) {
            return false;
        }

        if (sVersion <= lVersion) {
            return false;
        }
        return true;
    }


    /**
     * 將字符串轉換成數字
     *
     * @param version
     * @return
     */
    private static int getVersion(String version) {
        int sum = 0;
        int length = version.length();
        for (int i = 0; i < length; i++) {
            int temp = version.charAt(i) - 0x30;
            if (temp <= 9 && temp >= 0) {
                sum = sum * 10 + temp;
            }
        }
        if (sum == 0) {
            sum = -1;
        }
        return sum;
    }

}
