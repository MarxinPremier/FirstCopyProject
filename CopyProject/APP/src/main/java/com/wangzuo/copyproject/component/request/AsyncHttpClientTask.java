package com.wangzuo.copyproject.component.request;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wangzuo.copyproject.common.utils.JsonConstants;
import com.wangzuo.copyproject.common.utils.JsonUtils;
import com.wangzuo.copyproject.common.utils.LogUtils;
import com.wangzuo.copyproject.common.utils.MapUtils;
import com.wangzuo.copyproject.component.requestModel.NetRequestTaskModel;
import com.wangzuo.copyproject.component.requestModel.BaseJson;
import com.wangzuo.copyproject.component.requestModel.StatusConstants;
import org.apache.http.Header;
import java.net.SocketTimeoutException;
import java.util.Map;

/**
 * Created by hejie on 2016/11/3.
 * <p>
 * 使用异步请求框架
 */

public class AsyncHttpClientTask extends NetRequestTaskModel {

    private final AsyncHttpClient client = new AsyncHttpClient();//实例化对象

    /**
     * 私有构造
     */
    private AsyncHttpClientTask() {
        client.setTimeout(3000);//设置连接超时3秒
        client.addHeader("referer", "http://tc.zhixueyun.com/zxy-student-web/");//请求头
    }

    /**
     * 持有者
     */
    public static class TaskHolder {
        private static final AsyncHttpClientTask CLIENT_TASK = new AsyncHttpClientTask();
    }

    /**
     * 单例获得者
     *
     * @return
     */
    public static AsyncHttpClientTask getInstance() {
        return TaskHolder.CLIENT_TASK;
    }


    @Override
    public void sourceTask(String url, Map<String, Object> params, final Handler handler) {
        client.get(url, getRequestParams(url, params), new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onStart()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_START)
                        .sendToTarget();

            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onFinish()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_FINISH)
                        .sendToTarget();

            }

            @Override
            public void onCancel() {
                super.onCancel();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onCancel()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_CANCEL)
                        .sendToTarget();

            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                super.onProgress(bytesWritten, totalSize);
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onProgress()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_PROGRESS)
                        .sendToTarget();

            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onSuccess()");
                try {
                    handler.obtainMessage(StatusConstants.REQUEST_WHAT_SUCCESS,new String(responseBody)).sendToTarget();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onFailure()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_FAILURE).sendToTarget();
            }
        });
    }

    @Override
    public void task(String url, Map<String, Object> params, final Handler handler, final Integer... intArg) {
        client.post(url, getRequestParams(url, params), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onStart()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_START)
                        .sendToTarget();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onFinish()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_FINISH)
                        .sendToTarget();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onCancel()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_CANCEL)
                        .sendToTarget();
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                super.onProgress(bytesWritten, totalSize);
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onProgress()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_PROGRESS)
                        .sendToTarget();
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onSuccess()");
                try {
                    String replace = new String(responseBody).replace("\"id\":", "\"my_id\":");
                    LogUtils.getInstance().i(LogUtils.TAG, replace);
                    JSONObject jsonObject = JSON.parseObject(replace);
                    if (JsonUtils.isRequestSuccess(jsonObject)) {//请求成功
                        if (intArg.length > 0 && intArg[0] != null && intArg[0] != 0) {
                            if (intArg.length > 1 && intArg[1] != null && intArg[1] != 0) {
                                handler.obtainMessage(
                                        StatusConstants.REQUEST_WHAT_SUCCESS,
                                        intArg[0],
                                        intArg[1],
                                        JsonUtils.getData(jsonObject))
                                        .sendToTarget();
                            } else {
                                handler.obtainMessage(
                                        StatusConstants.REQUEST_WHAT_SUCCESS,
                                        intArg[0],
                                        0,
                                        JsonUtils.getData(jsonObject))
                                        .sendToTarget();
                            }
                        }
                        handler.obtainMessage(
                                StatusConstants.REQUEST_WHAT_SUCCESS,
                                JsonUtils.getData(jsonObject))
                                .sendToTarget();
                    } else {//服务错误

                        handler.obtainMessage(
                                StatusConstants.REQUEST_WHAT_SERVICE_ERROR,
                                JsonUtils.parseToObjectBean(new String(responseBody), BaseJson.class))
                                .sendToTarget();
                        if (JsonConstants.CODE_VALUE_0011.equals(jsonObject.getString(JsonConstants.JSON_CODE))) {

                        } else {
                            sendBroadcastToLogin(
                                    jsonObject.getString(JsonConstants.JSON_CODE),
                                    JsonUtils.getData(jsonObject),
                                    jsonObject.getString(JsonConstants.JSON_MSG)
                            );
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onFailure()");
                LogUtils.getInstance().i(LogUtils.TAG, error.toString());
                if (error instanceof SocketTimeoutException) {
                    handler.obtainMessage(StatusConstants.REQUEST_WHAT_TIMEOUT_ERROR).sendToTarget();
                } else {
                    handler.obtainMessage(StatusConstants.REQUEST_WHAT_FAILURE).sendToTarget();
                }
            }
        });
    }

    @Override
    public void httpParams(String url, Map<String, Object> params, final Handler handler) {
        client.post(url, getRequestParams(url, params), new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onStart()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_START)
                        .sendToTarget();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onFinish()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_FINISH)
                        .sendToTarget();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onCancel()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_CANCEL)
                        .sendToTarget();
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                super.onProgress(bytesWritten, totalSize);
                LogUtils.getInstance().i(LogUtils.TAG, "-------------->onProgress()");
                handler.obtainMessage(StatusConstants.REQUEST_WHAT_PROGRESS)
                        .sendToTarget();
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                LogUtils.getInstance().i(LogUtils.TAG, "--------------->onSuccess()");
                try {
                    String replace = new String(responseBody).replace("\"id\":", "\"my_id\":");
                    LogUtils.getInstance().i(LogUtils.TAG, replace);
                    JSONObject jsonObject = JSON.parseObject(replace);
                    if (JsonUtils.isRequestSuccess(jsonObject)) {//请求成功
                        handler.obtainMessage(StatusConstants.REQUEST_WHAT_SUCCESS, JsonUtils.getData(jsonObject))
                                .sendToTarget();
                    } else {//服务错误
                        handler.obtainMessage(StatusConstants.REQUEST_WHAT_SERVICE_ERROR, JsonUtils.parseToObjectBean(new String(responseBody), BaseJson.class))
                                .sendToTarget();
                        if (JsonConstants.CODE_VALUE_0011.equals(jsonObject.getString(JsonConstants.JSON_CODE))) {

                        } else {
                            sendBroadcastToLogin(
                                    jsonObject.getString(JsonConstants.JSON_CODE),
                                    JsonUtils.getData(jsonObject),
                                    jsonObject.getString(JsonConstants.JSON_MSG)
                            );
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtils.getInstance().i(LogUtils.TAG, "----------------onFailure()");
                LogUtils.getInstance().i(LogUtils.TAG, error.toString());
                if (error instanceof SocketTimeoutException) {
                    handler.obtainMessage(StatusConstants.REQUEST_WHAT_TIMEOUT_ERROR)
                            .sendToTarget();
                } else {
                    handler.obtainMessage(StatusConstants.REQUEST_WHAT_FAILURE).
                            sendToTarget();
                }
            }
        });
    }


    /**
     * 获得asynchttp的请求参数集合
     *
     * @param url
     * @param params
     * @return
     */
    private RequestParams getRequestParams(String url, Map<String, Object> params) {
        RequestParams requestParams = new RequestParams();
        StringBuffer log = new StringBuffer("url = " + url);
        if (!MapUtils.isEmpty(params)) {
            log.append(",Parameter = [");
            for (Map.Entry<String, Object> e : params.entrySet()) {
                log.append(e.getKey() + " = " + e.getValue() + ",");
            }
            log.append("]");
            requestParams.put("json", JsonUtils.mapToJson(params));
            LogUtils.getInstance().i(LogUtils.TAG, log.toString());
        }
        return requestParams;
    }

}
