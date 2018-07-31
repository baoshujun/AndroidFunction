package com.shujun.bao.roomdemo.db.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "book")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int bookid;

    @ColumnInfo(name = "book_name")
    private String bookName;

    @ColumnInfo(name = "author")
    private String author;

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }
}