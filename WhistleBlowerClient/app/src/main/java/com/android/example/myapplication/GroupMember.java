package com.android.example.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(primaryKeys = {"userId", "groupId"})
public class GroupMember {

    @ColumnInfo(name = "userId")
    public int userId;

    public int groupId;
}
