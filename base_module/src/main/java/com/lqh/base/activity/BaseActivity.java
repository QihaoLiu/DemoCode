package com.lqh.base.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lqh.base.R;
import com.lqh.base.activity.itf.IActivity;
import com.lqh.base.mvp.IPresenter;
import com.lqh.base.receiver.BaseBroadcastReceiver;
import com.lqh.base.utils.ILog;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * DemoCode
 *
 * @author LeoLiu
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {

    private static final String TAG = "BaseActivity";

    protected BaseActivity context = null;

    @Inject
    @Nullable
    protected P mPresenter;

    /**
     * 是否存活
     */
    private boolean isAlive = false;

    /**
     * 是否在活动
     */
    private boolean isRunning = false;

    protected int enterAnim = R.anim.fade;

    protected int exitAnim = R.anim.right_push_out;

    /**
     * 屏幕宽高
     */
    protected int mScreenWidth;
    protected int mScreenHeight;

    /**
     * 打开新的Activity
     */
    public void toActivity(Intent intent) {
        toActivity(intent, true);
    }

    /**
     * 打开新的Activity
     */
    public void toActivity(Intent intent, boolean showAnimation) {
        toActivity(intent, -1, showAnimation);
    }

    /**
     * 打开新的Activity
     */
    public void toActivity(Intent intent, int requestCode) {
        toActivity(intent, requestCode, true);
    }

    public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                if (intent == null) {
                    ILog.w(TAG, "toActivity  intent == null >> return;");
                    return;
                }
                //fragment中使用context.startActivity会导致在fragment中不能正常接收onActivityResult
                if (requestCode < 0) {
                    startActivity(intent);
                } else {
                    startActivityForResult(intent, requestCode);
                }
                if (showAnimation) {
                    overridePendingTransition(R.anim.right_push_in, R.anim.hold);
                } else {
                    overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
                }
            }
        });
    }

    /**
     * 在UI线程中运行
     * @param action
     */
    public final void runUiThread(Runnable action) {
        if (isAlive() == false) {
            ILog.w(TAG, "runUiThread  isAlive() == false >> return;");
            return;
        }
        runOnUiThread(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ILog.d(TAG, "onCreate <<<<<<<<<<<<<<<<<<<<<<<");
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        BarUtils.setStatusBarLightMode(this, true);
        setContentView(getLayoutId());
        context = (BaseActivity) getActivity();
        isAlive = true;
        getScreenWidthAndHeight();
        BaseBroadcastReceiver.register(context, receiver, ACTION_EXIT_APP);
        try {
            initView();
            initData();
            initEvent();
            setupActivityComponent();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(TAG, e.getMessage());
        }
        ILog.d(TAG, "onCreate >>>>>>>>>>>>>>>>>>>>>>>>");
    }

    /**
     * 获取当前屏幕宽高
     */
    public void getScreenWidthAndHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;
    }

    /**
     * 返回activity布局
     * @return
     */
    public abstract int getLayoutId();

    /**
     * find view
     * @param id
     * @param <V>
     * @return
     */
    public <V extends View> V findView(int id) {
        return (V) findViewById(id);
    }

    /**
     * find view 并 set listener
     * @param id
     * @param l
     * @param <V>
     * @return
     */
    public <V extends View> V findView(int id, View.OnClickListener l) {
        V v = findView(id);
        v.setOnClickListener(l);
        return v;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public boolean isAlive() {
        return isAlive && context != null;
    }

    @Override
    public boolean isRunning() {
        return isRunning & isAlive();
    }

    /**
     * 获取当前activity实例
     * @return
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * finish
     */
    public void finishWithError(String error) {
        ToastUtils.showShort(error);
        enterAnim = exitAnim = R.anim.null_anim;
        finish();
    }

    @Override
    public void finish() {
        super.finish();//必须写在最前才能显示自定义动画
        runUiThread(new Runnable() {
            @Override
            public void run() {
                if (enterAnim > 0 && exitAnim > 0) {
                    try {
                        overridePendingTransition(enterAnim, exitAnim);
                    } catch (Exception e) {
                        ILog.e(TAG, "finish overridePendingTransition(enterAnim, exitAnim);" +
                                " >> catch (Exception e) {  " + e.getMessage());
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        ILog.d(TAG, "onResume <<<<<<<<<<<<<<<<<<<<<<<");
        super.onResume();
        isRunning = true;
        ILog.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    protected void onPause() {
        ILog.d(TAG, "onPause <<<<<<<<<<<<<<<<<<<<<<<");
        super.onPause();
        isRunning = false;
        ILog.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>");
    }

    /**
     * 重写onDestroy方法并在super.onDestroy();前操作
     */
    @Override
    protected void onDestroy() {
        ILog.d(TAG, "onDestroy <<<<<<<<<<<<<<<<<<<<<<<");
        BaseBroadcastReceiver.unregister(context, receiver);

        isAlive = false;
        isRunning = false;
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
        }

        context = null;
        ILog.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent == null ? null : intent.getAction();
            if (isAlive() == false || StringUtils.isTrimEmpty(action)) {
                ILog.e(TAG, "receiver.onReceive  isAlive() == false" +
                        " || StringUtil.isNotEmpty(action, true) == false >> return;");
                return;
            }

            if (ACTION_EXIT_APP.equals(action)) {
                finish();
            }
        }
    };

}
