package com.android.example.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CurrentUserDao {

    @Insert
    void insert(CurrentUserEntity user);

    @Query("SELECT * from CurrentUserEntity")
    List<CurrentUserEntity> getCurrentUserEntity();

}
