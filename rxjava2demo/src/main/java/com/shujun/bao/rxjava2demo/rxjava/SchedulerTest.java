package com.shujun.bao.rxjava2demo.rxjava;

import android.util.Log;

import com.shujun.bao.rxjava2demo.MainActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SchedulerTest {

    public void test(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(MainActivity.TAG,"所在的线程：" + Thread.currentThread().getName());
                Log.d(MainActivity.TAG,"发送的数据:" +1+"");
                e.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(MainActivity.TAG,"所在的线程："+ Thread.currentThread().getName());
                Log.d(MainActivity.TAG,"接收到的数据:"+  "integer:" + integer);
            }
        });


    }
}
