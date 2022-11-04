package com.lqh.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.lqh.base.R;
import com.lqh.base.activity.itf.IFragment;
import com.lqh.base.mvp.IPresenter;
import com.lqh.base.utils.ILog;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment {

    private static final String TAG = "BaseFragment";

    protected View view = null;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = (BaseActivity) getActivity();
        isAlive = true;
        view = inflater.inflate(getLayoutId(), container, false);
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
        return view;
    }

    /**
     * 返回activity布局
     * @return
     */
    public abstract int getLayoutId();

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    protected Intent intent = null;

    /**
     * find view
     * @param id
     * @param <V>
     * @return
     */
    public <V extends View> V findView(int id) {
        return (V) view.findViewById(id);
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

    public Intent getIntent() {
        return context.getIntent();
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
        context.runOnUiThread(action);
    }

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
                    context.overridePendingTransition(R.anim.right_push_in, R.anim.hold);
                } else {
                    context.overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
                }
            }
        });
    }

    @Override
    public boolean isAlive() {
        return isAlive && context != null;
    }

    @Override
    public boolean isRunning() {
        return isRunning & isAlive();
    }

    @Override
    public void onResume() {
        ILog.d(TAG, "onResume <<<<<<<<<<<<<<<<<<<<<<<");
        super.onResume();
        isRunning = true;
        ILog.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void onPause() {
        ILog.d(TAG, "onPause <<<<<<<<<<<<<<<<<<<<<<<");
        super.onPause();
        isRunning = false;
        ILog.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>");
    }

    /**销毁并回收内存
     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
     */
    @Override
    public void onDestroy() {
        ILog.d(TAG, "onDestroy <<<<<<<<<<<<<<<<<<<<<<<");

        isAlive = false;
        isRunning = false;
        super.onDestroy();

        intent = null;

        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
        }

        context = null;

        ILog.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>");
    }

}
