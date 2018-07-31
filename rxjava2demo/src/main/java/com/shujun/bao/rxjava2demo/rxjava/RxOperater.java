package com.shujun.bao.rxjava2demo.rxjava;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 *
 * RxJava中的操作符
 * 操作符就是用于在Observable和最终的Observer之间，通过转换Observable为其他观察者对象的过程，修改发出的事件，最终将最简洁的数据传递给Observer对象
 *
 */
public class RxOperater {

    //map()操作符
   // map()操作符，就是把原来的Observable对象转换成另一个Observable对象，同时将传输的数据进行一些灵活的操作，方便Observer获得想要的数据形式。
    public void map(){
        Observable<Integer> observable = Observable.just("hello").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        });


    }

    //flatMap()操作符
    public void flatMap(){
        List<String> list = new ArrayList();

       // flatMap()对于数据的转换比map()更加彻底，如果发送的数据是集合，flatmap()重新生成一个Observable对象，
        // 并把数据转换成Observer想要的数据形式。它可以返回任何它想返回的Observable对象。
        Observable<Object> observable = Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        });


    }
    //filter()操作符
   // filter()操作符根据test()方法中，根据自己想过滤的数据加入相应的逻辑判断，返回true则表示数据满足条件，
    // 返回false则表示数据需要被过滤。最后过滤出的数据将加入到新的Observable对象中，方便传递给Observer想要的数据形式。
    public void filter(){
        List<String> list = new ArrayList();
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).filter(new Predicate<Object>() {
            @Override
            public boolean test(Object s) throws Exception {
                String newStr = (String) s;
                if (newStr.charAt(5) - '0' > 5) {
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println((String)o);
            }
        });


    }
    //take()操作符
    //take()操作符：输出最多指定数量的结果。
    public void take(){
        List<String> list = new ArrayList();
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).take(5).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                System.out.println((String)s);
            }
        });


    }
    //doOnNext()允许我们在每次输出一个元素之前做一些额外的事情。
    public void doOnNext(){
        List<String> list = new ArrayList();
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).take(5).doOnNext(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("准备工作");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                System.out.println((String)s);
            }
        });


    }
}
