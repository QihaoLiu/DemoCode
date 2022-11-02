package com.lqh.demo.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

   private static RetrofitInstance mInstance;
   private Retrofit mRetrofit;
   public static final String BASE_URL = "http://cn.bing.com";

   private RetrofitInstance() {
      OkHttpClient okHttpClient = new OkHttpClient.Builder()
              .connectTimeout(20, TimeUnit.SECONDS)
              .writeTimeout(20, TimeUnit.SECONDS)
              .readTimeout(20, TimeUnit.SECONDS)
              .build();
      mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
              .client(okHttpClient)
              .baseUrl(BASE_URL)
              .build();
   }

   public static RetrofitInstance getInstance() {
      if (mInstance == null) {
         synchronized (RetrofitInstance.class) {
            if (mInstance == null) {
               mInstance = new RetrofitInstance();
            }
         }
      }
      return mInstance;
   }
   public <T> T createService(Class<T> tClass) {
      return mRetrofit.create(tClass);
   }

}
