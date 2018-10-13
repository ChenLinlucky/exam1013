package com.example.m1013.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.example.m1013.mvp.contract.Frag1Contract;
import com.example.m1013.mvp.model.Frag1Model;


@Module
public class Frag1Module {
    private Frag1Contract.View view;

    /**
     * 构建Frag1Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public Frag1Module(Frag1Contract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    Frag1Contract.View provideFrag1View() {
        return this.view;
    }

    @FragmentScope
    @Provides
    Frag1Contract.Model provideFrag1Model(Frag1Model model) {
        return model;
    }
}