package com.example.m1013.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.m1013.mvp.contract.ShowContract;
import com.example.m1013.mvp.model.ShowModel;


@Module
public class ShowModule {
    private ShowContract.View view;

    /**
     * 构建ShowModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ShowModule(ShowContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ShowContract.View provideShowView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ShowContract.Model provideShowModel(ShowModel model) {
        return model;
    }
}