package com.example.m1013.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.m1013.di.module.ShowModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.m1013.mvp.ui.activity.ShowActivity;

@ActivityScope
@Component(modules = ShowModule.class, dependencies = AppComponent.class)
public interface ShowComponent {
    void inject(ShowActivity activity);
}