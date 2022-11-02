package com.lqh.demo.mvp.presenter;

import com.lqh.base.mvp.BasePresenter;
import com.lqh.demo.bean.BiYing;
import com.lqh.demo.mvp.contract.SplashContract;
import com.lqh.demo.mvp.model.SplashModel;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

public class SplashPresenter extends BasePresenter<SplashContract.Model,SplashContract.View> {

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onCreate() {
        requestPic();
    }

    public void requestPic() {
        mModel.getPic(new SplashModel.OnRequestFinishedListener() {
            @Override
            public void onError() {
                mRootView.showMessage("请求失败");
            }

            @Override
            public void onSuccess(BiYing biYing) {
                mRootView.callResult(biYing);
            }
        });
    }

}
