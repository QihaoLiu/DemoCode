package com.lqh.demo;

import android.view.View;

import com.lqh.base.activity.BaseActivity;
import com.lqh.base.common.BaseConstants;
import com.lqh.base.utils.LogUtil;
import com.lqh.base.utils.ThreadPoolUtil;

/**
 * DemoCode
 *
 * @author LeoLiu
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initEvent() {
        super.initView();
        findView(R.id.hello,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.hello:
                ThreadPoolUtil.getSingleThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.d(BaseConstants.TAG,"Run ...... ");
                    }
                });
                ThreadPoolUtil.shutDown();
                break;

            default:break;
        }
    }



}
