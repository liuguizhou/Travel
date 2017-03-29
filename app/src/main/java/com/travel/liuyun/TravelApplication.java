/*
 * Copyright (C) 2013 The Yi-Portal Project
 *
 * Innofidei, Inc. All rights reserved
 * 
 */

package com.travel.liuyun;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.travel.liuyun.greendao.DaoMaster;
import com.travel.liuyun.greendao.DaoSession;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Application类
 *
 * @author liuyun
 */
public class TravelApplication extends Application {
    public static Context applicationContext;
    private static TravelApplication instance;
    private static DaoSession daoSession;

    private List<Activity> activitys = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        setupDatabase();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("liuguizhou",true))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static TravelApplication getInstance() {
        return instance;
    }


    public void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : activitys) {
                activity.finish();
            }
        } catch (Exception e) {
        } finally {
            System.exit(0);

        }
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "student.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
