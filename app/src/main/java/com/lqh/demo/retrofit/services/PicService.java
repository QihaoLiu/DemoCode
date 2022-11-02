package com.lqh.demo.retrofit.services;

import com.lqh.demo.bean.BiYing;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PicService {

    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Call<BiYing> getBiYingPic();

}
