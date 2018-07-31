package com.shujun.bao.roomdemo.db.bean;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.location.Address;

@Entity(indices = {@Index(value = "firstName", unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String firstName;
    public String lastName;
    public int age;




}


