package com.wangzuo.copyproject.common.utils;

import com.wangzuo.copyproject.application.ProjectLitePalApplication;

/**
 * Created by hejie on 2016/10/28.
 *
 * 资源获得工具类
 */

public class ResourceUtils {

    /**
     * 获得字符串
     */
    public static  String  getString(int id){
        return  ProjectLitePalApplication.mContext.getResources().getString(id);
    }

    /**
     * 获得颜色id
     * @param id
     * @return
     */
    public static int getColor(int id) {
        return ProjectLitePalApplication.mContext.getResources().getColor(id);
    }

}
