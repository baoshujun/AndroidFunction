package com.shujun.bao.roomdemo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shujun.bao.roomdemo.db.bean.Book;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface BookDao {
    //这里使用Maybe
    @Query("SELECT * FROM book")
    Maybe<List<Book>> getAllBooks();

    @Query("SELECT * FROM book where bookid = :id")
    Maybe<Book> getBookById(int id);

    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);

    @Update
    void updateBooks(Book... books);
}