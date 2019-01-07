package com.android.example.myapplication;

import android.arch.persistence.room.Entity;

@Entity
public class GroupMessages {

    public int groupId;

    // TODO change Message to id instead of user
    public int senderId;

    public String content;

    public String time;

    public boolean isBelongToUser;

}
