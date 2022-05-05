package com.dqgb.mvvm;

import android.app.Application;

import com.dqgb.mvvm.lib_network.ApiService;

public class BaseApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiService.init("http://123.56.232.18:8080/serverdemo", null);
//        ApiService.init("https://api.devio.org/as/", null);
    }
}
