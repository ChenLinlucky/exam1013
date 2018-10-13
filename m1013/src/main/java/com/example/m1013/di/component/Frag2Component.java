package com.example.m1013.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.m1013.di.module.Frag2Module;

import com.jess.arms.di.scope.FragmentScope;
import com.example.m1013.mvp.ui.fragment.Frag2Fragment;

@FragmentScope
@Component(modules = Frag2Module.class, dependencies = AppComponent.class)
public interface Frag2Component {
    void inject(Frag2Fragment fragment);
}