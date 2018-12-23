package com.android.example.myapplication;

import android.content.Intent;
import android.widget.ImageButton;

public class GroupItem {
    private int imageResource;
    private String groupName;
    private String groupLastMessage;

    public GroupItem(int imageResource, String groupName, String groupLastMessage){
        this.imageResource = imageResource;
        this.groupName = groupName;
        this.groupLastMessage = groupLastMessage;
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
}
