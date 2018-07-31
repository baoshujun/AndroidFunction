package com.shujun.bao.dragger2demo.module;

import com.shujun.bao.dragger2demo.bean.A;
import com.shujun.bao.dragger2demo.bean.B;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    A provideA(B b){
        return new A(b);
    }

    @Provides
    B provideB(){
        return new B();
    }
}
