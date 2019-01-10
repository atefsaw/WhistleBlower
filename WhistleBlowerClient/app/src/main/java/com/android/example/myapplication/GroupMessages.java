package com.android.example.myapplication;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"groupId", "senderId", "time"})
public class GroupMessages {

    @NonNull
    public int groupId;

    // TODO change Message to id instead of user
    @NonNull
    public int senderId;

    public String content;

    @NonNull
    public String time;

    public boolean isBelongToUser;

}
