package com.android.example.myapplication;

public class Message {

    private String content;
    private User sender;
    private Group group;
    private long time;

    Message(String content, User sender, Group group) {
        this.content = content;
        this.sender = sender;
        this.group = group;
        this.time = System.currentTimeMillis();
    }

    public Group getGroup() {
        return group;
    }


}
