package com.lqh.base.activity.itf;

import android.app.Activity;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public interface IActivity {

    static final String ACTION_EXIT_APP = "ACTION_EXIT_APP";

    void setupActivityComponent();

    void initView();

    void initData();

    void initEvent();

    boolean isAlive();

    boolean isRunning();

    public Activity getActivity();

}
