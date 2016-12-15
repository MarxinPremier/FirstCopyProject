package com.wangzuo.copyproject.business.login.presenter;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import com.wangzuo.copyproject.R;
import com.wangzuo.copyproject.common.utils.GlobalConstants;
import com.wangzuo.copyproject.common.utils.HttpURLConnectionUtils;
import com.wangzuo.copyproject.common.utils.ResourceUtils;
import com.wangzuo.copyproject.component.service.UpdataService;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hejie on 2016/11/28.
 * <p>
 * apk下载的任务
 * <p>
 * <p>
 * 注:不要忘了关流
 */

public class DownLoadTask extends AsyncTask<String, Integer, Integer> {

    //通知栏上的view
    private final RemoteViews mRemoteViews;
    //通知管理器
    private final NotificationManager mNotificationManager;
    //通知
    private final Notification mNotification;
    private String urlStr;
    private String fileStr;
    private final Context mContext;

    public DownLoadTask(Context context, RemoteViews contentView, NotificationManager notificationManager, Notification notification) {
        mContext = context;
        mRemoteViews = contentView;
        mNotificationManager = notificationManager;
        mNotification = notification;
    }

    /**
     * 初始化通知栏的进度条，并且开始执行任务
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 在下载的过程需要不停的更新通知栏的进度条表示
     * 在doInBackground中通过publishProgress来通知
     *
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int updateSize = values[0];
        mRemoteViews.setTextViewText(R.id.notificationPercent, updateSize + "%");
        mRemoteViews.setProgressBar(R.id.notificationProgress, 100, updateSize, false);
        mNotificationManager.notify(GlobalConstants.APK_UPDATE_NOTIFACATION_ID, mNotification);
    }


    /**
     * 执行下载的任务方法，在工作线程中，通过publishProgress来实时
     * 更新进度条
     */
    @Override
    protected Integer doInBackground(String... values) {
        HttpURLConnection connection = null;
        //获取下载地址
        urlStr = values[0];
        //文件保存地址
        fileStr = values[1];
        //已经下载的文件进度
        int downCount = 0;
        //每次下载的大小
        int readSize = 0;
        //进度条更新的记录值
        int updateSize = 0;
        //进度每次更新的大小
        int stepSize = 5;
        //文件总大小
        int contentLength;
        //流集合
        ArrayList<Closeable> closeables = new ArrayList<>();
        try {
            URL url = new URL(urlStr);
            //创建io流
            InputStream inputStream;
            OutputStream outputStream;
            connection = (HttpURLConnection) url.openConnection();
            //添加头部
            HttpURLConnectionUtils.addHeader(connection);
            //设定超时时间
            connection.setConnectTimeout(GlobalConstants.TIME_OUT);
            connection.setReadTimeout(GlobalConstants.TIME_OUT);
            //获取下载文件的长度
            contentLength = connection.getContentLength();
            //判断网络状况
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {//成功连接可以开始下载
                inputStream = connection.getInputStream();
                outputStream = new FileOutputStream(fileStr, false);//false表示文件存在就覆盖掉
                //将流添加到集合便于管理
                closeables.add(inputStream);
                closeables.add(outputStream);
                //下载缓存
                byte[] buffer = new byte[1024];
                while ((readSize = inputStream.read(buffer)) != -1) {
                    //写入到文件系统
                    outputStream.write(buffer, 0, readSize);
                    //时刻记录下载进度
                    downCount += readSize;
                    //更新下载进度
                    if ((downCount * 100 / contentLength - stepSize) > updateSize) {//每次下载完成的百分比减去进度最小精度值大于当前的进度值，就更新进度值
                        updateSize += stepSize;
                        publishProgress(updateSize);
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (closeables != null) {
                for (Closeable c : closeables) {
                    try {
                        c.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //断开连接
            if (connection != null) {
                connection.disconnect();
            }
        }
        if (downCount > 0) {//下载成功
            return GlobalConstants.DOWN_OK;
        } else {//下载失败
            return GlobalConstants.DOWN_FAILD;
        }

    }

    /**
     * 执行完下载任务的后续处理，包括下载成功的处理和下载失败的处理
     */
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer) {
            case GlobalConstants.DOWN_OK://下载成功
                //启动安装程序的隐式意图
                Uri uri = Uri.fromFile(new File(fileStr));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                //结束通知
                mNotificationManager.cancel(GlobalConstants.APK_UPDATE_NOTIFACATION_ID);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                break;
            case GlobalConstants.DOWN_FAILD://下载失败
                mNotification.tickerText = ResourceUtils.getString(R.string.down_faild);
                mNotificationManager.notify(GlobalConstants.APK_UPDATE_NOTIFACATION_ID, mNotification);
                break;
            default:
                if (mContext instanceof UpdataService) {
                    ((UpdataService) mContext).stopService(new Intent("com.wangzuo.service.ACTION_UpdataService"));
                }
                break;
        }
    }
}
