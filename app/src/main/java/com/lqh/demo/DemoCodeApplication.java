package com.lqh.demo;

import android.app.Application;

import com.lqh.base.BaseManager;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class DemoCodeApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        BaseManager.getInstance().init(this);
        BaseManager.getInstance().setDebug(true);
    }
}
