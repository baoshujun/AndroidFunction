package com.shujun.bao.dragger2demo.module;

import com.shujun.bao.dragger2demo.bean.A;

import dagger.Module;
import dagger.Provides;

@Module(includes = {Bmodule.class})
public class AModule {

    @Provides
    A providA(){
        return new A();
    }
}
