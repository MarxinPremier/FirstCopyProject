package com.wangzuo.copyproject.component.requestModel;

/**
 * Created by hejie on 2016/11/3.
 *
 * 请求状态跳转符
 *
 */

public class StatusConstants {

    /****网络请求过程提示符****/
    public static final int REQUEST_WHAT_START = 0x00001;// 开始请求
    public static final int REQUEST_WHAT_PROGRESS = 0x00002;// 正在请求
    public static final int REQUEST_WHAT_SUCCESS = 0x00003;// 请求成功
    public static final int REQUEST_WHAT_FAILURE = 0x00004;// 请求失败
    public static final int REQUEST_WHAT_FINISH = 0x00005;// 请求完成
    public static final int REQUEST_WHAT_CANCEL = 0x00006;// 取消请求
    public static final int REQUEST_WHAT_SERVICE_ERROR = 0x00007;// 服务器错误
    public static final int REQUEST_WHAT_TIMEOUT_ERROR = 0x00008;// 请求超时
    public static final int REQUEST_WHAT_CARCH_SUCCESS = 0x00009;// 请求缓存成功
    public static final int REQUEST_WHAT_NEWDATA_SUCCESS = 0x00010;// 请求新数据成功
    public final static int REQUEST_CAMERA = 8002; //相机
    public final static int SELECT_TOPIC = 8009; //话题
    public final static int SHARE_TOPIC=8010;//分享


}
