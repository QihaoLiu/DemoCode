package com.lqh.base.utils;

import android.os.Environment;

import com.lqh.base.common.BaseConstants;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class SdcardUtil {

    public static final String TAG = "SdcardUtil";

    public static String getPath() {
        String finalPath;

        try {
            String getPath = Environment.getExternalStorageDirectory().getPath();
            if (!getPath.contains("null")) {
                finalPath = getPath;
                LogUtil.d(TAG,"getPath final path is " + finalPath);
                return finalPath;
            }
        } catch (Exception e) {
            LogUtil.d(TAG,"getPath found exception is " + e.toString());
        }

        try {
            String getAbsolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!getAbsolutePath.contains("null")) {
                finalPath = getAbsolutePath;
                LogUtil.d(TAG,"getAbsolutePath final path is " + finalPath);
                return finalPath;
            }
        } catch (Exception e) {
            LogUtil.d(TAG,"getAbsolutePath found exception is " + e.toString());
        }

        finalPath = BaseConstants.SDCARD;
        LogUtil.d(TAG,"sdcard getPath use default path is " + finalPath);
        return finalPath;
    }

}
