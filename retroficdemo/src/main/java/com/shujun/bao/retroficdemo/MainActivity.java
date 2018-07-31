package com.shujun.bao.retroficdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.shujun.bao.retroficdemo.bean.Translation;
import com.shujun.bao.retroficdemo.bean.TranslationYD;
import com.shujun.bao.retroficdemo.network.GetRequest_Interface;
import com.shujun.bao.retroficdemo.network.PostRequest_Interface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    /*
1.初始化OkHttpClient
*/
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request();
       // requestGet();
        // 使用Retrofit封装的方法
    }

    public void requestGet() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(client)
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
                response.body().show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

        /*
取消调用
*/
 //userCall.cancel();
    }

    public void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

      //对 发送请求 进行封装(设置需要翻译的内容)
        Call<TranslationYD> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<TranslationYD>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<TranslationYD> call, Response<TranslationYD> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<TranslationYD> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }


//    private static Retrofit create() {
//        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//        builder.readTimeout(10, TimeUnit.SECONDS);
//        builder.connectTimeout(9, TimeUnit.SECONDS);
//
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(interceptor);
//        }
//
//        return new Retrofit.Builder().baseUrl(ENDPOINT)
//                .client(builder.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//    }


}
