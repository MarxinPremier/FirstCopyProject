package com.wangzuo.copyproject.common.utils;


import java.util.Map;

/**
 * Created by hejie on 2016/11/3.
 *
 * map工具类
 *
 */

public class MapUtils {

    /**
     *  检验map是否为空
     * @param sourceMap
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> boolean isEmpty(Map<K,V> sourceMap){
        return (sourceMap == null||sourceMap.size() == 0);
    }

}
