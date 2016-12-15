package com.wangzuo.copyproject.component.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.business.login.presenter.DownLoadTask;
import com.wangzuo.copyproject.business.main.view.MainActivity;
import com.wangzuo.copyproject.common.utils.GlobalConstants;
import com.wangzuo.copyproject.common.utils.PreferenceDB;
import com.wangzuo.copyproject.common.utils.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by hejie on 2016/11/24.
 * <p>
 * 下载更新service
 */

public class UpdataService extends Service {

    private String appName;
    public File updateDir = null;
    public File updateFile = null;
    public String downloadDir = "copyProject/apk";
    private NotificationManager notificationManager;
    private RemoteViews contentView;
    private Intent updateIntent;
    private PendingIntent pendingIntent;
    private Notification notification;
    private DownLoadTask downLoadTask;

    @Override
    public void onCreate() {
        super.onCreate();
        appName = getResources().getString(R.string.app_name);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //创建文件
        createFile(appName);
        //创建通知窗口
        createNotification();
        //创建下载线程
        createThread();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 创建下载线程
     */
    @SuppressLint("HandlerLeak")
    private void createThread() {
        //开启下载任务
        downLoadTask = new DownLoadTask(this,
                contentView,
                notificationManager,
                notification);
        //开始下载
        downLoadTask.execute(
                PreferenceDB.getInstance().getAPKUrl(),
                updateFile.getAbsolutePath()
        );
    }


    /**
     * 创建通知栏
     */
    private void createNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        contentView = new RemoteViews(getPackageName(), R.layout.common_update_notification_layout);
        contentView.setTextViewText(R.id.notificationTitle, ResourceUtils.getString(R.string.notification_title));
        contentView.setTextViewText(R.id.notificationPercent, "0%");
        contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);
        //意图
        updateIntent = new Intent(this, MainActivity.class);
        updateIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);
        notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.applogo)
                .setTicker(ResourceUtils.getString(R.string.begin_down)).build();
        notificationManager.notify(GlobalConstants.APK_UPDATE_NOTIFACATION_ID, notification);
    }

    /**
     * 创建文件
     *
     * @param appName
     */
    private void createFile(String appName) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            updateDir = new File(Environment.getExternalStorageDirectory() + File.separator + downloadDir);
            updateFile = new File(updateDir + File.separator + appName + ".apk");
            if (!updateDir.exists()) {
                updateDir.mkdirs();
            }
            if (!updateFile.exists()) {
                try {
                    updateFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
