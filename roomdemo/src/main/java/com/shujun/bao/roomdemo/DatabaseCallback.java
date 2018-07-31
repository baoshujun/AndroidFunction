package com.shujun.bao.roomdemo;

import com.shujun.bao.roomdemo.db.bean.Book;

import java.util.List;

public interface DatabaseCallback {
    void onLoadBooks(List<Book> books);

    void onAdded();

    void onDeleted();

    void onUpdated();

    void onError(String err);
}