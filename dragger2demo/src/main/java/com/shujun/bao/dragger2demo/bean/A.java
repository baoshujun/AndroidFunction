package com.shujun.bao.dragger2demo.bean;

import android.util.Log;

public class A {
    private B b;

    public A(B b) {
        this.b = b;
    }

    public A() {
    }

    private static final String TAG = "A";

    public void eat(){
        Log.d(TAG, "eat: .......... ");
    }
}
