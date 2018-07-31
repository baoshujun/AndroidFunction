package com.shujun.bao.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.shujun.bao.rxjava2demo.rxjava.FlowableTest;
import com.shujun.bao.rxjava2demo.rxjava.SchedulerTest;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Flowable.range(1,10).flatMap(new Function<Integer, Publisher<Integer>>() {
            @Override
            public Publisher<Integer> apply(Integer integer) throws Exception {
                return Flowable.just(integer);
            }
        }).subscribeOn(Schedulers.computation()).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer * integer;
            }
        }).blockingSubscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });


    }


    /**
     * 这种观察者模型是不支持背压的
     */
    public void test1(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
              e.onNext(1);
              e.onNext(2);
              e.onNext(3);

              e.onComplete();
              //e.onError(new Throwable());
                e.onNext(4);
                e.onNext(5);


            }
        });


        Observer<Integer> observer = new Observer<Integer>() {

            //这是新加入的方法，在订阅后发送数据之前，
            //回首先调用这个方法，而Disposable可用于取消订阅
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext: "+  value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+ e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        observable.subscribe(observer);
    }

    public void testFlowable(){
        Flowable.range(0,10).subscribe(new Subscriber<Integer>() {
            Subscription sub;
            //当订阅后，会首先调用这个方法，其实就相当于onStart()，
            //传入的Subscription s参数可以用于请求数据或者取消订阅

            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe: start");
                sub=s;
               // 下游调用request(n)来告诉上游发送多少个数据
                sub.request(1);
                Log.d(TAG, "onSubscribe: end");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: "+  integer);
                sub.request(1);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: "+ t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }
    
    public void test2(){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new Subscriber<Integer>() {
            Subscription sub;
            //当订阅后，会首先调用这个方法，其实就相当于onStart()，
            //传入的Subscription s参数可以用于请求数据或者取消订阅

            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe: start");
                sub=s;
                // 下游调用request(n)来告诉上游发送多少个数据
                sub.request(1);
                Log.d(TAG, "onSubscribe: end");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: "+  integer);
                sub.request(1);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: "+ t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }


    //just()方式
   // 使用just( )，将为你创建一个Observable并自动为你调用onNext( )发射数据。通过just( )方式 直接触发onNext()，
   // just中传递的参数将直接在Observer的onNext()方法中接收到。
    public void test3(){
        Observable<String> observable = Observable.just("Hello");
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }

    //fromIterable()方式
    //使用fromIterable()，遍历集合，发送每个item。相当于多次回调onNext()方法，每次传入一个item。
    //注意：Collection接口是Iterable接口的子接口，所以所有Collection接口的实现类都可以作为Iterable对象直接传入fromIterable()方法

    public void test4(){
        List<String> list = new ArrayList<String>();
        for(int i =0;i<10;i++){
            list.add("Hello"+i);
        }
        Observable<String> observable = Observable.fromIterable((Iterable<String>) list);
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });

    }
    //defer()方式
    //当观察者订阅时，才创建Observable，并且针对每个观察者创建都是一个新的Observable。以何种方式创建这个Observable对象，当满足回调条件后，就会进行相应的回调。
    public void test5(){
        Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just("hello");
            }
        });

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });

    }
    //interval( )方式
    //创建一个按固定时间间隔发射整数序列的Observable，可用作定时器。即按照固定2秒一次调用onNext()方法。
    public void test6(){
        Observable<Long> observable = Observable.interval(2, TimeUnit.SECONDS);

      //  range( )方式
        Observable<Integer> observable1 = Observable.range(1,20);
       // 创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常。
        // 上述表示发射1到20的数。即调用20次nNext()方法，依次传入1-20数字

        //timer( )方式
        Observable<Long> observable3 = Observable.timer(2, TimeUnit.SECONDS);
       // 创建一个Observable，它在一个给定的延迟后发射一个特殊的值，即表示延迟2秒后，调用onNext()方法

       // repeat( )方式
        Observable<Integer> observable4 = Observable.just(123).repeat();
//        创建一个Observable，该Observable的事件可以重复调用。
//        除了Observable(被观察者)的创建之外，RxJava 2.x 还提供了多个函数式接口 ，用于实现简便式的观察者模式
    }

}
