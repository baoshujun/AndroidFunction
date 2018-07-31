package com.shujun.bao.retroficdemo.network;

import com.shujun.bao.retroficdemo.bean.TranslationYD;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostRequest_Interface {
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&veor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<TranslationYD> getCall(@Field("i") String targetSentence);
    //采用@Post表示Post方法进行请求（传入部分url地址）
    // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
    // 需要配合@Field 向服务器提交需要的字段YD

   // @multipart 这个注解不是把请求内容放到 from 表单中，是放到 body 中
}
