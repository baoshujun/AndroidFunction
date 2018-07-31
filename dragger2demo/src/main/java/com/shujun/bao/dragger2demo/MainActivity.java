package com.shujun.bao.dragger2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shujun.bao.dragger2demo.component.DaggerMainComponent;
import com.shujun.bao.dragger2demo.bean.A;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    A a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.create().inject(this);
    }
}
