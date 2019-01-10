package com.android.example.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class CurrentUserEntity {

    @PrimaryKey
    @NonNull
    public String userPhoneNumber;

    public CurrentUserEntity(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

}
