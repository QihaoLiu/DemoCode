package com.lqh.demo.di.module;

import com.lqh.demo.mvp.contract.WeatherContract;
import com.lqh.demo.mvp.model.WeatherModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WeatherModule {

    @Binds
    abstract WeatherContract.Model bindWeatherModel(WeatherModel model);

}
