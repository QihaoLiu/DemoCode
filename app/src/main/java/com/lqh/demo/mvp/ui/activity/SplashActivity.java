package com.lqh.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.lqh.base.activity.BaseActivity;
import com.lqh.demo.R;
import com.lqh.demo.bean.BiYing;
import com.lqh.demo.data.SPFactory;
import com.lqh.demo.di.component.DaggerSplashComponent;
import com.lqh.demo.mvp.contract.SplashContract;
import com.lqh.demo.mvp.presenter.SplashPresenter;
import com.lqh.demo.retrofit.RetrofitInstance;

import androidx.annotation.NonNull;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View{

    private ImageView mImageView;
    private TextView mTextView;

    @Override
    public void callResult(BiYing biYing) {
        if (biYing != null && biYing.getImages().size() > 0) {
            BiYing.Images image = biYing.getImages().get(0);
            SPUtils.getInstance().put(SPFactory.KEY.SPLASH_PIC_URL,RetrofitInstance.BASE_URL + image.getUrl());
            SPUtils.getInstance().put(SPFactory.KEY.SPLASH_PIC_COPYRIGHT,image.getCopyright());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void setupActivityComponent() {
        DaggerSplashComponent.builder().view(this).build().injectSplashActivity(this);
    }

    @Override
    public void initView() {
        super.initView();
        mImageView = findView(R.id.imageview);
        mTextView = findView(R.id.details);
        String picUrl = SPUtils.getInstance().getString(SPFactory.KEY.SPLASH_PIC_URL);
        if (!TextUtils.isEmpty(picUrl)) {
            String copyright = SPUtils.getInstance().getString(SPFactory.KEY.SPLASH_PIC_COPYRIGHT);
            Glide.with(this).load(picUrl).into(mImageView);
            mTextView.setText(copyright);
        }
        startTimer(TextUtils.isEmpty(picUrl));
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    public void startTimer(boolean empty){
        CountDownTimer timer = new CountDownTimer((long) ((empty ? 2 : 3) * 1000), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                toActivity(new Intent(SplashActivity.this,MainActivity.class));
                SplashActivity.this.finish();
            }
        };
        timer.start();
    }

}
