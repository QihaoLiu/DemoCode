package com.lqh.demo.di.module;

import com.lqh.demo.mvp.contract.SplashContract;
import com.lqh.demo.mvp.model.SplashModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SplashModule {

    @Binds
    abstract SplashContract.Model bindSplashModel(SplashModel model);

}
