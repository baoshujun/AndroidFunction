package com.shujun.bao.rxjava2demo.rxjava;

import android.util.Log;

import com.shujun.bao.rxjava2demo.MainActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FlowableTest {


    public  void test1(){
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d(MainActivity.TAG, "emit 1");
                emitter.onNext(1);
                Log.d(MainActivity.TAG, "emit 2");
                emitter.onNext(2);
                Log.d(MainActivity.TAG, "emit 3");
                emitter.onNext(3);
                Log.d(MainActivity.TAG, "emit complete");
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR); //增加了一个参数

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(MainActivity.TAG, "onSubscribe");
                //这个方法就是用来向生产者申请可以消费的事件数量
                s.request(Long.MAX_VALUE);
            }
            @Override
            public void onNext(Integer integer) {
                Log.d(MainActivity.TAG, "onNext: " + integer);

            }
            @Override
            public void onError(Throwable t) {
                Log.w(MainActivity.TAG, "onError: ", t);
            }
            @Override
            public void onComplete() {
                Log.d(MainActivity.TAG, "onComplete");
            }
        };
        flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);


    }

//    我们让Flowable发送129个事件，而Subscriber一个也不处理，就产生了异常。
//    因此，ERROR即保证在异步操作中，事件累积不能超过128，超过即出现异常。消费者不能再接收事件了，但生产者并不会停止
    public void testError(){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 129; i++) {
                    Log.d(MainActivity.TAG, "emit " + i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    Subscription mSubscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mSubscription.request(1);
                        Log.d(MainActivity.TAG, "onNext: " + integer);
                    }
                    @Override
                    public void onError(Throwable t) {
                        Log.w(MainActivity.TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     *
     * 如果Flowable对象不是自己创建的，可以采用onBackpressureBuffer()、onBackpressureDrop()、onBackpressureLatest()的方式指定
     *
     */
    public void test3(){
        Flowable.just(1).onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });


    }
}
