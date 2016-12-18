package com.wangzuo.copyproject.component.request;

import android.os.Handler;

import com.wangzuo.copyproject.common.utils.JavaReflectUtils;
import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.component.requestModel.NetRequestTaskModel;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hejie on 2016/12/16.
 * <p>
 * 基于Retrofit的网络请求
 */

public class RetrofitOkhttpClientTask extends NetRequestTaskModel {

    private final Retrofit mRetrofit;
    private final RetrofitService mRetrofitService;
    private final String mMlds;

    /**
     * 私有构造
     */
    private RetrofitOkhttpClientTask() {
        mMlds = PreferenceDB.getInstance().getMlds();
        mRetrofit = new Retrofit.Builder()
                .client(genericClient())
                .baseUrl(mMlds)//主要地址
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .build();
        mRetrofitService = mRetrofit.create(RetrofitService.class);
    }

    /**
     * 单例持有者
     */
    public static class TaskHolder {
        private static final RetrofitOkhttpClientTask CLIENT_TASK = new RetrofitOkhttpClientTask();
    }

    /**
     * 单例获得者
     *
     * @return
     */
    public static RetrofitOkhttpClientTask getInstance() {
        return TaskHolder.CLIENT_TASK;
    }

    @Override
    protected void sourceTask(String url, Map<String, Object> params, Handler handler) {

    }

    @Override
    protected void task(String url, Map<String, Object> params, Handler handler, Integer... intArg) {
//        //截取主地址,获得拼接地址
//        String[] totalUrl = url.split(mMlds);
//        String turnUrl = totalUrl[1];
//        String methodName = turnUrl.replace("/","_");
//        String bodyName = RetrofitRequstBody.REPLACE_STR + turnUrl.replace("/", RetrofitRequstBody.REPLACE_STR);
//        //创建请求体实例,封装参数
//        JavaReflectUtils.createInstance(RetrofitRequstBody.TOTAL_PATH + "." + bodyName);
//        //使用反射加载
//        Set<String> strings = params.keySet();
//        for (String s:strings) {
//            Object o = params.get(s);
//        }
//        //反射
//        try {
//            Method method = .getClass().getMethod(methodName, e.getClass());
//            method.invoke(t, e);
//        } catch (NoSuchMethodException e1) {
//            e1.printStackTrace();
//        } catch (InvocationTargetException e1) {
//            e1.printStackTrace();
//        } catch (IllegalAccessException e1) {
//            e1.printStackTrace();
//        }
    }

    @Override
    protected void httpParams(String url, Map<String, Object> params, Handler handler) {

    }

    /**
     * 配置一个okhttpclient
     *
     * @return
     */
    public static OkHttpClient genericClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(new Interceptor() {//请求头部
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("referer", "http://tc.zhixueyun.com/zxy-student-web/")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(3, TimeUnit.SECONDS)//请求超时世家不
                .build();
        return okHttpClient;
    }
}
