package com.lqh.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.blankj.utilcode.util.StringUtils;
import com.lqh.base.utils.ILog;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public abstract class BaseBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "BaseBroadcastReceiver";

    /**
     * 接收信息监听回调
     */
    public interface OnReceiveListener{
        void onReceive(Context context, Intent intent);
    }

    protected OnReceiveListener onReceiveListener = null;

    public void setOnReceiveListener(OnReceiveListener onReceiveListener) {
        this.onReceiveListener = onReceiveListener;
    }

    protected Context context = null;

    public BaseBroadcastReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ILog.i(TAG, "onReceive intent = " + intent);
        if (onReceiveListener != null) {
            onReceiveListener.onReceive(context, intent);
        }
    }

    /**
     * 注册广播接收器,在Activity或Fragment的onCreate中调用
     */
    public abstract BaseBroadcastReceiver register();
    /**
     * 取消注册广播接收器,在Activity或Fragment的onDestroy中调用
     */
    public abstract void unregister();

    public static BroadcastReceiver register(Context context, @Nullable BroadcastReceiver receiver, String action) {
        return register(context, receiver, new String[] {action});
    }

    public static BroadcastReceiver register(Context context, @Nullable BroadcastReceiver receiver, String[] actions) {
        return register(context, receiver, actions == null ? null : Arrays.asList(actions));
    }

    public static BroadcastReceiver register(Context context, @Nullable BroadcastReceiver receiver, List<String> actionList) {
        IntentFilter filter = new IntentFilter();
        for (String action : actionList) {
            if (!StringUtils.isTrimEmpty(action)) {
                filter.addAction(action);
            }
        }
        return register(context, receiver, filter);
    }

    public static BroadcastReceiver register(Context context, @Nullable BroadcastReceiver receiver, IntentFilter filter) {
        ILog.i(TAG, "register >>>");
        if (context == null || filter == null) {
            ILog.e(TAG, "register  context == null || filter == null >> return;");
            return receiver;
        }

        context.registerReceiver(receiver, filter);

        return receiver;
    }

    public static void unregister(Context context, BroadcastReceiver receiver) {
        ILog.i(TAG, "unregister >>>");
        if (context == null || receiver == null) {
            ILog.e(TAG, "unregister  context == null || receiver == null >> return;");
            return;
        }

        try {
            context.unregisterReceiver(receiver);
        } catch (Exception e) {
            ILog.e(TAG, "unregister  try { context.unregisterReceiver(receiver);" +
                    " } catch (Exception e) { \n" + e.getMessage());
        }
    }

}
