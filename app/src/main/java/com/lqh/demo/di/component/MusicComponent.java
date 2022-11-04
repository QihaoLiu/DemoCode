package com.lqh.demo.di.component;

import com.lqh.base.di.scope.FragmentScope;
import com.lqh.demo.di.module.MusicModule;
import com.lqh.demo.mvp.contract.MusicContract;
import com.lqh.demo.mvp.ui.fragment.MusicFragment;

import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(modules = MusicModule.class)
public interface MusicComponent {

    void injectMusicFragment(MusicFragment fragment);

    @Component.Builder
    interface Builder{

        @BindsInstance
        MusicComponent.Builder view(MusicContract.View view);

        MusicComponent build();
    }

}
