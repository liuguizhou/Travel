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

import java.util.ArrayList;
import java.util.List;

/**
 * Application类
 *
 * @author liuyun
 */
public class TravelApplication extends Application {
    public static Context applicationContext;
    private static TravelApplication instance;
    
    private List<Activity> activitys = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
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
}
