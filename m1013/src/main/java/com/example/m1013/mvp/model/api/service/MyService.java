package com.example.m1013.mvp.model.api.service;

import com.example.m1013.bean.News;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyService {
    @GET("v2/book/search?q=小说&start=2&count=20")
    Observable<News> getshowdata();
}
