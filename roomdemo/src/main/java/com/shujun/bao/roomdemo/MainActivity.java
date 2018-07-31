package com.shujun.bao.roomdemo;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shujun.bao.roomdemo.db.bean.MyDatabase;
import com.shujun.bao.roomdemo.db.bean.User;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(this, MyDatabase.class, "room.db").build();


//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                List<User> users = database.userDao().searchAllUsers();
//                e.onNext(new Gson().toJson(users));
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        textView.setText(s);
//                    }
//                });


    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        User user = new User("first","last",10);
//                        database.userDao().insertUsers(user);
                    }
                }).start();
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;

        }
    }
}
