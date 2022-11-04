package com.lqh.demo.di.module;

import com.lqh.demo.mvp.contract.MusicContract;
import com.lqh.demo.mvp.model.MusicModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MusicModule {

    @Binds
    abstract MusicContract.Model bindMusicModel(MusicModel model);

}
