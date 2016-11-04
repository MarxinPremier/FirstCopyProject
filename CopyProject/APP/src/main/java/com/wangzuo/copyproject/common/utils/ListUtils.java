package com.wangzuo.copyproject.common.utils;

import java.util.List;

/**
 * Created by hejie on 2016/11/1.
 *
 * list工具类
 */

public class ListUtils {


    /**
     * 检查集合是否为空
     * @param list
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(List<V> list){
        return (list == null|| list.size() == 0);
    }

}
