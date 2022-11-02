package com.lqh.base.utils;

import android.util.Log;

import com.lqh.base.common.BaseConstants;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public class ILog {

    /**
     * 日志输出时的TAG
     */
    private static String mTag = BaseConstants.TAG;

    /**
     * 日志输出级别V
     */
    private static final int LEVEL_VERBOSE = 1;

    /**
     * 日志输出级别D
     */
    private static final int LEVEL_DEBUG = 2;

    /**
     * 日志输出级别I
     */
    private static final int LEVEL_INFO = 3;

    /**
     * 日志输出级别W
     */
    private static final int LEVEL_WARN = 4;

    /**
     * 日志输出级别E
     */
    private static final int LEVEL_ERROR = 5;

    /**
     * 是否允许输出log
     */
    public static int mDebuggable = BaseConstants.LOG_LEVEL_ALL;

    /**
     * 用于记时的变量
     */
    private static long mTimestamp = 0;
    /**
     * 写文件的锁对象
     */
    private static final Object mLogLock = new Object();

    public static LogCallBack callBack;

    public static void setOnLogCallBack(LogCallBack logCallBack) {
        callBack = logCallBack;
    }


    /**---------------日志输出,已固定TAG  begin---------------**/
    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void v(String msg) {
        v("", msg);
    }

    public static void v(String head, String msg) {
        v("", head, msg);
    }

    public static void v(String tag, String head, String msg) {
        if (LEVEL_VERBOSE >= mDebuggable) {
            Log.v(tag.isEmpty() ? mTag : tag, head.isEmpty() ? msg : "< " + head + " > " + msg);
            if (callBack != null) {
                callBack.log(LEVEL_VERBOSE, tag.isEmpty() ? mTag : tag, msg);
            }
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void d(String msg) {
        d("", msg);
    }

    public static void d(String head, String msg) {
        d("", head, msg);
    }

    public static void d(String tag, String head, String msg) {
        if (LEVEL_DEBUG >= mDebuggable) {
            Log.d(tag.isEmpty() ? mTag : tag, head.isEmpty() ? msg : "< " + head + " > " + msg);
            if (callBack != null) {
                callBack.log(LEVEL_DEBUG, tag.isEmpty() ? mTag : tag, msg);
            }
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     */
    public static void i(String msg) {
        i("", msg);
    }

    public static void i(String head, String msg) {
        i("", head, msg);
    }

    public static void i(String tag, String head, String msg) {
        if (LEVEL_INFO >= mDebuggable) {
            Log.i(tag.isEmpty() ? mTag : tag, head.isEmpty() ? msg : "< " + head + " > " + msg);
            if (callBack != null) {
                callBack.log(LEVEL_INFO, tag.isEmpty() ? mTag : tag, msg);
            }
        }
    }

    /**
     * 以级别为 w 的形式输出LOG
     */
    public static void w(String msg) {
        w("", msg);
    }

    public static void w(String head, String msg) {
        w("", head, msg);
    }

    public static void w(String tag, String head, String msg) {
        if (LEVEL_WARN >= mDebuggable) {
            Log.w(tag.isEmpty() ? mTag : tag, head.isEmpty() ? msg : "< " + head + " > " + msg);
            if (callBack != null) {
                callBack.log(LEVEL_WARN, tag.isEmpty() ? mTag : tag, msg);
            }
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */
    public static void e(String msg) {
        e("", msg);
    }

    public static void e(String head, String msg) {
        e("", head, msg);
    }

    public static void e(String tag, String head, String msg) {
        if (LEVEL_ERROR >= mDebuggable) {
            Log.e(tag.isEmpty() ? mTag : tag, head.isEmpty() ? msg : "< " + head + " > " + msg);
            if (callBack != null) {
                callBack.log(LEVEL_ERROR, tag.isEmpty() ? mTag : tag, msg);
            }
        }
    }

    private static int LOG_MAX_LENGTH = 2000;

    public static void dLong(String head, String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAX_LENGTH;
        for (int i = 0; i < 100; i++) {
            if (strLength > end) {
                d(head + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAX_LENGTH;
            } else {
                d(head + i, msg.substring(start, strLength));
                break;
            }
        }
    }

}
