package com.wangzuo.copyproject.common.utils;

import com.wangzuo.copyproject.application.ProjectLitePalApplication;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hejie on 2016/11/28.
 *
 * http工具类
 *
 */

public class HttpURLConnectionUtils {

    /**
     * 给网络请求增加头部
     * @param connection
     */
    public static void addHeader(HttpURLConnection connection){
        if (!StringUtils.isEmpty(ProjectLitePalApplication.getReferer2())){
            connection.addRequestProperty("Referer",ProjectLitePalApplication.getReferer2());
        }
    }

    /**
     * MediaPlay增加头部
     * @return
     */
    public static Map<String,String> addHeadMediaPlay(){
        if (!StringUtils.isEmpty(ProjectLitePalApplication.getReferer())){
            Map<String,String> map = new HashMap<>();
            map.put("headers",ProjectLitePalApplication.getReferer());
            return map;
        }
        return null;
    }
}

