package com.example.m1013.mvp.model;

import android.app.Application;

import com.example.m1013.bean.News;
import com.example.m1013.mvp.model.api.service.MyService;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.example.m1013.mvp.contract.ShowContract;

import io.reactivex.Observable;


@ActivityScope
public class ShowModel extends BaseModel implements ShowContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ShowModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<News> requestshowdata() {
        Observable<News> getshowdata = mRepositoryManager.obtainRetrofitService(MyService.class).getshowdata();
        return getshowdata;
    }
}