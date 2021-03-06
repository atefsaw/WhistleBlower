package com.whistleBlower.model;

import java.util.List;

public class Group {

    private int id;
    private String name;
    private List<String> userIds;
    private Message lastMessage;
    private static int groupIdCounter = 0;


    public Group() {
    }

    public Group(List<String> userIds, String name) {
        this.name = name;
        this.userIds = userIds;
    }

    public int getId() {
        return id;
    }

    public void setId(){
        groupIdCounter++;
        id = groupIdCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserId(List<String> userIds) {
        this.userIds = userIds;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

}
