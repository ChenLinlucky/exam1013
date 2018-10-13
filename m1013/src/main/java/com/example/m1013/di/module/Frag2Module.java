package com.example.m1013.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.example.m1013.mvp.contract.Frag2Contract;
import com.example.m1013.mvp.model.Frag2Model;


@Module
public class Frag2Module {
    private Frag2Contract.View view;

    /**
     * 构建Frag2Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public Frag2Module(Frag2Contract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    Frag2Contract.View provideFrag2View() {
        return this.view;
    }

    @FragmentScope
    @Provides
    Frag2Contract.Model provideFrag2Model(Frag2Model model) {
        return model;
    }
}