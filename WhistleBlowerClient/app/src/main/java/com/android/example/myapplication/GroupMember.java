package com.android.example.myapplication;

import android.arch.persistence.room.Entity;

@Entity
public class GroupMember {

    public int userId;

    public int groupId;
}
