package com.shujun.bao.roomdemo.db.bean;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.shujun.bao.roomdemo.dao.UserDao;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}


