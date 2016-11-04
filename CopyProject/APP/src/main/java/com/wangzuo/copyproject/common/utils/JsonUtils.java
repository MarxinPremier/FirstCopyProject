package com.wangzuo.copyproject.common.utils;

import android.content.res.ObbInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by hejie on 2016/11/3.
 * <p>
 * json工具类
 */

public class JsonUtils {

    /**
     * 获得data数据
     *
     * @param json
     * @return
     */
    public static String getData(JSONObject json) {
        try {
            if (StringUtils.isEmpty(json.getString(JsonConstants.JSON_DATA)) || StringUtils.isEquals(JsonConstants.JSON_DATA, "null")) {
                return "[]";
            } else {
                return json.getString(JsonConstants.JSON_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.getInstance().e(LogUtils.TAG_EXCEPTION, e);
            return "[]";
        }
    }

    /**
     * 请求是否成功
     *
     * @param jsonObject
     * @return
     */
    public static boolean isRequestSuccess(JSONObject jsonObject) {
        try {
            if (StringUtils.isEquals(jsonObject.getString(JsonConstants.JSON_CODE), JsonConstants.CODE_VALUE_0000)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 拿到字段的值
     *
     * @param json
     * @param key
     * @return
     */
    public static String getKeyResult(String json, String key) {
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            String string = jsonObject.getString(key);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json字符串转换成bean实例
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T parseToObjectBean(String json, Class<T> tClass) {
        try {
            return JSON.parseObject(json, tClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json字符串转换成bean的集合
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> parseToObjectList(String json, Class<T> tClass) {
        try {
            JSONArray objects = JSON.parseArray(json);
            Iterator<Object> iterator = objects.iterator();
            List<T> list = new ArrayList<T>();
            while (iterator.hasNext()) {
                list.add(JSON.parseObject(iterator.next().toString(), tClass));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            List<T> list = new ArrayList<T>();
            return list;
        }
    }


    /**
     * 通过关键字获取字段并且转换成list集合
     * @param json
     * @param tClass
     * @param key
     * @param <T>
     * @return
     */
    public static <T> List<T> parseToObejectListByKey(String json,Class<T> tClass,String key){
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            String string = jsonObject.getString(key);
            List<T> list = JSON.parseArray(string, tClass);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<T>();
        }
    }

    /**
     * 将一个吗类型的对象装换成json
     * @param map
     * @return
     */
    public static String mapToJson(Map<?,?> map){
        StringBuilder json = new StringBuilder();
        Iterator<?> iterator = map.keySet().iterator();
        json.append("{");
        if (map != null&&map.size()>0){
            while (iterator.hasNext()){
                Object next = iterator.next();
                json.append(next);
                json.append(":");
                json.append(map.get(next));
                json.append(",");
            }
            json.setCharAt(map.size()-1,'}');
        }else {
            json.append("}");
        }
        return json.toString();
    }
}
