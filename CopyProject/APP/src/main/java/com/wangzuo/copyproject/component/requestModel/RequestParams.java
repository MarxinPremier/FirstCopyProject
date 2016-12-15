package com.wangzuo.copyproject.component.requestModel;

import com.wangzuo.copyproject.common.utils.DESUtils;
import com.wangzuo.copyproject.common.utils.GlobalConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hejie on 2016/11/2.
 *
 * 请求参数
 *
 */

public class RequestParams {

    /**
     * 登录接口的请求参数
     */
    public static Map<String,Object> getMldsLoginParams(String org){
        Map<String,Object> map = new HashMap<>();
        map.put("company_name",org);
        return map;
    }

    /**
     * 请求登录参数
     *
     * @param login_id
     * @param password
     * @return
     */
    public static Map<String, Object> get_LOGIN(String orgName,String login_id, String password,String appMachine,String release) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("company_name", orgName);
        params.put("login_id", login_id);
        params.put("client_type", 6);// 6代表的是Android 客户端标识
        params.put("appMachine",appMachine);
        params.put("appSystem","Android " + release);
        params.put("password",
                DESUtils.encryptStr(password, GlobalConstants.PWD_DES_KEY));
        return params;
    }
}
