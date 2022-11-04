package com.lqh.demo.di.component;

import com.lqh.base.di.scope.FragmentScope;
import com.lqh.demo.di.module.WeatherModule;
import com.lqh.demo.mvp.contract.WeatherContract;
import com.lqh.demo.mvp.ui.fragment.WeatherFragment;

import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(modules = WeatherModule.class)
public interface WeatherComponent {

    void injectWeatherFragment(WeatherFragment fragment);

    @Component.Builder
    interface Builder{

        @BindsInstance
        WeatherComponent.Builder view(WeatherContract.View view);

        WeatherComponent build();
    }

}
