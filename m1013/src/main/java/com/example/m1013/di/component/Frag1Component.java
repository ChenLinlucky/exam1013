package com.example.m1013.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.m1013.di.module.Frag1Module;

import com.jess.arms.di.scope.FragmentScope;
import com.example.m1013.mvp.ui.fragment.Frag1Fragment;

@FragmentScope
@Component(modules = Frag1Module.class, dependencies = AppComponent.class)
public interface Frag1Component {
    void inject(Frag1Fragment fragment);
}