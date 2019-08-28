package com.lqh.base.interfaces;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public interface Presenter {

    static final String ACTION_EXIT_APP = "ACTION_EXIT_APP";

    void initView();

    void initData();

    void initEvent();

    boolean isAlive();

    boolean isRunning();

}
