package com.shujun.bao.roomdemo.db.bean;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.shujun.bao.roomdemo.dao.BookDao;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}