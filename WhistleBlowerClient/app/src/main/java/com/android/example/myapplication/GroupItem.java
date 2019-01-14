package com.android.example.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class GroupItem {
    @PrimaryKey
    private int groupId;
    private int imageResource;
    private String groupName;
    private String groupLastMessage;


    public GroupItem(int imageResource, String groupName, String groupLastMessage, int groupId){
        this.imageResource = imageResource;
        this.groupName = groupName;
        this.groupLastMessage = groupLastMessage;
        this.groupId = groupId;
    }

    public void changeText(String text) {
        groupName = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupLastMessage() {
        return groupLastMessage;
    }

    public void setGroupLastMessage(String lastSeen) { this.groupLastMessage = lastSeen; }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
