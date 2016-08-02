package com.travel.liuyun.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;

import com.travel.liuyun.TravelApplication;

import java.util.concurrent.*;


/**
 * Created by shengshibotong.com. User: Joe by 13-3-21 上午9:47
 */
public final class InternetUtil {

    private final static int corePoolSize = 60;
    private final static int maximumPoolSize = 80;
    private final static int keepAliveTime = 10;

    private final static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private final static Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

    // 检查是否有网络
    public static boolean hasInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) TravelApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {

            return true;
        } else {
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static <P extends Object, T extends AsyncTask<P, ?, ?>> void startMyTask(T task, P... params) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            task.executeOnExecutor(threadPoolExecutor, params);
        } else {
            task.execute(params);
        }
    }

}
