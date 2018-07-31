package com.shujun.bao.retroficdemo.rxjava;




import android.database.Observable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxjavaTest {
    private static String  baseUrl;
    private static String appkey;

    private static Retrofit create() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);

        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public void request(){
//        Retrofit retrofit = create();
//        Api api = retrofit.create(Api.class);
//        Observable<AllCity> observable = api.getAllCity(appkey);
//        observable.subscribeOn(Schedulers.io())
//                .flatMap(new Function<AllCity, ObservableSource<City>>() {
//                    @Override
//                    public ObservableSource<City> apply(AllCity city) throws Exception {
//                        ArrayList<City> result = city.getResult();
//                        return Observable.fromIterable(result);
//                    }
//                })
//                .filter(new Predicate<City>() {
//                    @Override
//                    public boolean test(City city) throws Exception {
//                        String id = city.getId();
//                        if(Integer.parseInt(id)<5){
//                            return true;
//                        }
//                        return false;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<City>() {
//                    @Override
//                    public void accept(City city) throws Exception {
//                        System.out.println(city);
//                    }
//                });


    }


}
