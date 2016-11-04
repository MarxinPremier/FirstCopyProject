package com.wangzuo.copyproject.common.utils;

/**
 * Created by heije on 2016/11/3.
 *
 * 在json解析中使用到的关键词
 *
 */

public interface JsonConstants {

    //json
    String JSON_CODE = "code";
    String JSON_MSG = "msg";
    String JSON_DATA = "data";
    String JSON_LIST = "list";
    String JSON_SUCCEE = "success";
    String JSON_WAIT = "wait";
    String JSON_RESULT = "result";//请求接口返回结果
    String JSON_RESULT_SUCCESS = "1";//请求接口返回结果为成功
    String JSON_RESULT_FAILURE = "0";//请求接口返回结果为失败
    String JSON_MESSAGE = "message";//签到返回的提示
    String JSON_STATUS = "status";//签到返回的提示

    /**
     * 请求列表时参数字段名
     */
    String REQUEST_TASK_LIST_PARAM_SID = "sid";
    String REQUEST_TASK_LIST_PARAM_PAGE_SIZE = "pageSize";
    String REQUEST_TASK_LIST_PARAM_PAGE_NUMBER = "pageNumber";
    String REQUEST_TASK_LIST_PARAM_CITY_ID = "city_id";
    String CITY_ID = "";

    // 项目级别消息 用户验证问题
    String CODE_VALUE_0000 = "0000";// 操作成功
    String CODE_VALUE_0001 = "0001";// 查询用户异常
    String CODE_VALUE_0002 = "0002";// 用户不存在
    String CODE_VALUE_0003 = "0003";// 密码错误
    String CODE_VALUE_0004 = "0004";// 参数格式错误
    String CODE_VALUE_0005 = "0005";// 登录名不能为空
    String CODE_VALUE_0006 = "0006";// 密码不能为空
    String CODE_VALUE_0007 = "0007";// 未知异常
    String CODE_VALUE_0008 = "0008";// 未登录
    String CODE_VALUE_0009 = "0009";// 系统反馈内容不能为空
    String CODE_VALUE_0010 = "0010";// 体验度不能为空
    String CODE_VALUE_0011 = "0011";// 网络请求异常
    String CODE_VALUE_0012 = "0012";// 用户过期
    String CODE_VALUE_0013 = "0013";// 用户已禁用
    String CODE_VALUE_0014 = "0014";// 在另一台设备登录
    String CODE_VALUE_0019 = "0019";// 存在敏感词汇

    String CODE_VALUE_0504 = "0504";// 需要跳转登录
}
