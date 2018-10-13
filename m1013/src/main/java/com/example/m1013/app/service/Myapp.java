package com.example.m1013.app.service;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jess.arms.base.BaseApplication;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class Myapp extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);
    }
}
