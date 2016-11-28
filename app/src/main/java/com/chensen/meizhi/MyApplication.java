package com.chensen.meizhi;

import android.app.Application;

/**
 * author：chensen on 2016/11/25 14:20
 * desc：
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
