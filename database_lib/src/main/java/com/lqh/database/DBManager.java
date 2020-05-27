package com.lqh.database;

import android.app.Application;

import com.lqh.base.utils.LogUtil;
import com.lqh.database.common.DBConstants;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class DBManager {

    static {
        System.loadLibrary("sqlite_");
    }

    private static DBManager mInstance;

    private Application mDBApplication;

    public DBManager() {

    }

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    public void init(Application application) {
        mDBApplication = application;
        DBConstants.DB_Module_Init_Status = true;
        init(DBConstants.Sql_Debug_Status);
        LogUtil.i(DBConstants.TAG,"lqh db module init success");
    }

    public static native int init(boolean debug);

}
