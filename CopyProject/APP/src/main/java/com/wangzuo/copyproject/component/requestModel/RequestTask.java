package com.wangzuo.copyproject.component.requestModel;

import android.os.Handler;

import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.component.request.AsyncHttpClientTask;
import java.util.Map;

/**
 * Created by hejie on 2016/11/2.
 *
 * 网络请求
 * {@link request}这里可以有多重选择
 *  1.可以是{@link AsyncHttpClientTask}
 */

public class RequestTask {

    /**
     * 获得一个请求
     */
    public static final NetRequestTaskModel request = AsyncHttpClientTask.getInstance();


    /**
     * post请求
     * @param url
     * @param map
     * @param handler
     * @param integers
     */
    public static void task(String url, Map<String,Object> map, Handler handler,Integer... integers){
        request.task(url,map,handler,integers);
    }

    /**
     * 普通get请求
     * @param url
     * @param map
     * @param handler
     */
    public static void sourceTask(String url,Map<String,Object> map,Handler handler){
        request.sourceTask(url,map,handler);
    }

    /**
     *
     * @param url
     * @param params
     * @param handler
     */
    public static void httpParams(String url, Map<String, Object> params, Handler handler){
        request.httpParams(url,params,handler);
    }

    /**
     * 普通请求URL
     * @param url
     */
    public static String getUrl(String url){
        return new StringBuffer(PreferenceDB.getInstance().getMlds()).append("/").append(url).toString();
    }

    /**
     * adapter请求
     * @param url
     */
    public static String getAurl(String url){
        return  new StringBuffer(PreferenceDB.getInstance().getAdapterUrl()).append("/").append(url).toString();
    }
}
