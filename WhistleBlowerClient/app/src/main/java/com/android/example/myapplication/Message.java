package com.android.example.myapplication;

import java.sql.Time;

public class Message {

    private String content;
    private User sender;
    private Group group;
    private long time;
    private boolean isBelongsToCurrentUser;


    public Message(String content, User sender, Group group, boolean isBelongsToCurrentUser) {
        this.content = content;
        this.sender = sender;
        this.group = group;
        this.time = System.currentTimeMillis();
        this.isBelongsToCurrentUser = isBelongsToCurrentUser;
        // TODO send message to clients
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isBelongsToCurrentUser() { return isBelongsToCurrentUser; }

}
