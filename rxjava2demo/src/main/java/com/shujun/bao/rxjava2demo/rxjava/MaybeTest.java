package com.shujun.bao.rxjava2demo.rxjava;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MaybeTest {


    public void test1(){
        //判断是否登陆
        Maybe.just(isLogin())
                //可能涉及到IO操作，放在子线程
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new MaybeObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean value) {
                        if(value){

                        }else{

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public boolean isLogin(){
        return false;
    }
}
