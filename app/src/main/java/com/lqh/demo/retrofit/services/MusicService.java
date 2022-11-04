package com.lqh.demo.retrofit.services;

import com.lqh.demo.bean.PlayList;
import com.lqh.demo.bean.PlayListDetails;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MusicService {

    @GET("https://music.163.com/api/playlist/list")
    Call<PlayList> getPlayList();

    @GET("http://music.163.com/api/v3/playlist/detail")
    Call<PlayListDetails> getPlayListDetails(@QueryMap Map<String , Object> param);

}
