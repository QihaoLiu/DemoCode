package com.lqh.demo.mvp.model;

import com.lqh.base.di.scope.ActivityScope;
import com.lqh.base.mvp.BaseModel;
import com.lqh.demo.bean.BiYing;
import com.lqh.demo.mvp.contract.SplashContract;
import com.lqh.demo.retrofit.RetrofitInstance;
import com.lqh.demo.retrofit.services.PicService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@ActivityScope
public class SplashModel extends BaseModel implements SplashContract.Model {

    public interface OnRequestFinishedListener {

        void onError();

        void onSuccess(BiYing biYing);

    }

    @Inject
    public SplashModel() {
        super();
    }

    @Override
    public void getPic(OnRequestFinishedListener onRequestFinishedListener) {
        RetrofitInstance.getInstance().createService(PicService.class).getBiYingPic().enqueue(new Callback<BiYing>() {
            @Override
            public void onResponse(Call<BiYing> call, Response<BiYing> response) {
                if (response != null && response.isSuccessful()) {
                    onRequestFinishedListener.onSuccess(response.body());
                }else {
                    onRequestFinishedListener.onError();
                }
            }

            @Override
            public void onFailure(Call<BiYing> call, Throwable t) {
                onRequestFinishedListener.onError();
            }
        });
    }

}
