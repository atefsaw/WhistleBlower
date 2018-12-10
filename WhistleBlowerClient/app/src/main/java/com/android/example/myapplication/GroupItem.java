package com.android.example.myapplication;

public class GroupItem {
    private int imageResource;
    private String groupName;
    private String groupLastMessage;
    public GroupItem(int mImageResource, String groupName, String groupLastMessage){
        this.imageResource = mImageResource;
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
