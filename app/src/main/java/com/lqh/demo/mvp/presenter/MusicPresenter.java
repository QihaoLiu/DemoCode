package com.lqh.demo.mvp.presenter;

import com.lqh.base.mvp.BasePresenter;
import com.lqh.demo.bean.PlayList;
import com.lqh.demo.mvp.contract.MusicContract;
import com.lqh.demo.mvp.model.MusicModel;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

public class MusicPresenter extends BasePresenter<MusicContract.Model,MusicContract.View> {

    @Inject
    public MusicPresenter(MusicContract.Model model, MusicContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        requestMusic();
    }

    public void requestMusic() {
        mModel.getMusic(new MusicModel.OnRequestFinishedListener() {
            @Override
            public void onSuccess(PlayList playList) {
                mRootView.callResult(playList);
            }

            @Override
            public void onError() {
                mRootView.showMessage("获取列表失败");
            }
        });
    }

}
