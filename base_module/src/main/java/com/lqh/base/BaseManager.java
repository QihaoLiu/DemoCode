package com.lqh.base;

import android.app.Application;
import android.content.Context;

import com.lqh.base.common.BaseConstants;
import com.lqh.base.utils.ILog;
import com.lqh.base.utils.SdcardUtil;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class BaseManager {

    private static BaseManager mInstance;

    private Application mBaseApplication;
    private Context mBaseContext;

    public BaseManager() {

    }

    public static BaseManager getInstance() {
        if (mInstance == null) {
            synchronized (BaseManager.class) {
                if (mInstance == null) {
                    mInstance = new BaseManager();
                }
            }
        }
        return mInstance;
    }

    public void init(Application application) {
        if (BaseConstants.Base_Module_Init_Status) {
            ILog.d("lqh base module had init !");
            return;
        }
        mBaseApplication = application;
        mBaseContext = mBaseApplication.getApplicationContext();
        BaseConstants.Base_Module_Init_Status = true;
        BaseConstants.Sdcard_Path = SdcardUtil.getPath();
        ILog.i("lqh base module init success ! module name is "+getModuleName());
    }

    public void setDebug(boolean debug) {
        ILog.mDebuggable = debug?BaseConstants.LOG_LEVEL_ALL:BaseConstants.LOG_LEVEL_OFF;
    }

    public String getModuleName() {
        return mBaseContext == null?"lqg base module not init":mBaseContext.getResources().getString(R.string.lqh_base_module_name);
    }

}
