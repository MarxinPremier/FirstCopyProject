package com.wangzuo.copyproject.business.start.presenter;

import android.os.Handler;
import android.os.Message;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.guide.view.GuideActivity;
import com.wangzuo.copyproject.business.start.inter.StartActivityInterface;
import com.wangzuo.copyproject.common.utils.ActivityUtils;
import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.common.utils.ResourceUtils;
import com.wangzuo.copyproject.common.utils.StringUtils;

/**
 * Created by hejie on 2016/10/28.
 * <p>
 * 倒计时handler
 */

public class TimeHandler extends Handler {

    private int startTime = 2;
    private StartActivityInterface mStartActivityInterface;

    public TimeHandler(StartActivityInterface startActivityInterface) {
        mStartActivityInterface = startActivityInterface;

    }


    @Override
    public void handleMessage(Message msg) {
        //更新UI
        upDateUi();
        //更新数据
        startTime--;
        //发送通知
        if (startTime >= 0)
            sendMessageDelayed(obtainMessage(), 1000);
        else {
            toOtherActivity();
        }
    }

    /**
     * 跳转到其他的activity
     */
    private void toOtherActivity() {
        if (PreferenceDB.getInstance().getFirstOpen()){//第一次打开app
            ActivityUtils.startActivity(mStartActivityInterface.getContext(), GuideActivity.class);
            ActivityUtils.finishActivity(mStartActivityInterface.getContext());
        }else {//非第一次打开app

        }
    }

    /**
     * 更新UI
     */
    private void upDateUi() {
        mStartActivityInterface.setText(
                StringUtils.changeText(
                        ResourceUtils.getString(R.string.start_tv),
                        startTime + "",
                        ResourceUtils.getString(R.string.replace_tv))
        );
    }

    /**
     * 开启计时器
     */
    public void startCounter() {
        sendMessage(new Message());
    }

}
