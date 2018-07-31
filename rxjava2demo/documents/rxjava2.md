1.添加依赖
Android端使用RxJava需要依赖新的包名：

       //RxJava的依赖包（我使用的最新版本）
     compile 'io.reactivex.rxjava2:rxjava:2.0.1'
        //RxAndroid的依赖包
      compile 'io.reactivex.rxjava2:rxandroid:2.0.1'

2.观察者模式

 rxjava2.0 出现了两种观察者模式：

 * Observable(被观察者)/Observer（观察者）
 * Flowable(被观察者)/Subscriber(观察者)

>RxJava2.X中，Observeable用于订阅Observer，是不支持背压的，而Flowable用于订阅Subscriber，
是支持背压(Backpressure)的

>背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略

其他观察者模式

* Single/SingleObserver
* Completable/CompletableObserver
* Maybe/MaybeObserver




在github上搜索：RxJava2-Android-Samples

CompositeDisposable