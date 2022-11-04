package com.lqh.demo.mvp.model;

import com.lqh.base.mvp.BaseModel;
import com.lqh.base.utils.ILog;
import com.lqh.demo.bean.PlayList;
import com.lqh.demo.mvp.contract.MusicContract;
import com.lqh.demo.retrofit.RetrofitInstance;
import com.lqh.demo.retrofit.services.MusicService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicModel extends BaseModel implements MusicContract.Model {

    public interface OnRequestFinishedListener {

        void onSuccess(PlayList playList);

        void onError();

    }

    @Inject
    public MusicModel() {
        super();
    }

    @Override
    public void getMusic(OnRequestFinishedListener listener) {
        RetrofitInstance.getInstance().createService(MusicService.class).getPlayList().enqueue(new Callback<PlayList>() {
            @Override
            public void onResponse(Call<PlayList> call, Response<PlayList> response) {
                if (response != null && response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }else {
                    listener.onError();
                }
            }

            @Override
            public void onFailure(Call<PlayList> call, Throwable t) {
                listener.onError();
            }
        });
    }

}
