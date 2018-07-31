package com.shujun.bao.retroficdemo.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
        @GET("service/getIpIp")
        Call<Object> getIpInfo(@Query("ip") String ip);

    }
