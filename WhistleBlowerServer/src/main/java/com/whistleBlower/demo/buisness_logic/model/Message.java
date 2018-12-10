package com.whistleBlower.demo.buisness_logic.model;

public class Message {

    private String content;
    private User sender;
    private Group group;
    private long time;

    public Message(String content, User sender, Group group) {
        this.content = content;
        this.sender = sender;
        this.group = group;
        this.time = System.currentTimeMillis();
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


}
