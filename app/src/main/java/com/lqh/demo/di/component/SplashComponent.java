package com.lqh.demo.di.component;

import com.lqh.base.di.scope.ActivityScope;
import com.lqh.demo.di.module.SplashModule;
import com.lqh.demo.mvp.contract.SplashContract;
import com.lqh.demo.mvp.ui.activity.SplashActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = SplashModule.class)
public interface SplashComponent {

    void injectSplashActivity(SplashActivity activity);

    @Component.Builder
    interface Builder {

        @BindsInstance
        SplashComponent.Builder view(SplashContract.View view);

        SplashComponent build();
    }

}
