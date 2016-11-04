package com.wangzuo.copyproject.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wangzuo.copyproject.R;

/**
 * Created by hejie on 2016/10/31.
 *
 * 动画工具类
 */

public class AnimUtils {

    /**
     * 登录页面的logo放大动画
     * @param context
     */
    public static void enLarge(Context context, ImageView imageView){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.login_logo_scale_anim);
        imageView.setImageResource(R.drawable.main_login_logo);
        imageView.setAnimation(animation);
    }

    /**
     * 进入activity的动画
     */
    public static void  inAnim(Context context){
        ((Activity)context).overridePendingTransition(R.anim.translate_to_left,R.anim.translate_to_left_hide);
    }

    /**
     * 进入activity的动画
     */
    public static void  outAnim(Context context){
        ((Activity)context).overridePendingTransition(R.anim.translate_to_right,R.anim.translate_to_right_hide);
    }
}
