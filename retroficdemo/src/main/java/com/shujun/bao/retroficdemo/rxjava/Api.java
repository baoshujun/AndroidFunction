package com.shujun.bao.retroficdemo.rxjava;

import android.database.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("citys")
    Observable<AllCity> getAllCity(@Query("key") String key);
}