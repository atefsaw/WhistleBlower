package com.android.example.myapplication;

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
