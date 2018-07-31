package com.shujun.bao.dragger2demo.component;

import com.shujun.bao.dragger2demo.MainActivity;
import com.shujun.bao.dragger2demo.module.AModule;
import com.shujun.bao.dragger2demo.module.Bmodule;
import com.shujun.bao.dragger2demo.module.MainModule;

import dagger.Component;

@Component(modules = {MainModule.class, AModule.class, Bmodule.class})
public interface MainComponent {

    void inject(MainActivity activity);
}
