package com.android.example.myapplication;


import java.util.ArrayList;
import java.util.List;

public class User {

    private String userId;
    private List<Message> messagesToPull;


    private List<Group> groupsToPull;

    public User() {
    }

    public User(String userId) {
        this.messagesToPull = new ArrayList<>();
        this.groupsToPull = new ArrayList<>();
        this.userId = userId;
    }


    public void addMessage(Message message){
        messagesToPull.add(message);
    }

    public List<Message> pullMessages(){
        List<Message> messageList = new ArrayList<>(messagesToPull);
        messagesToPull.clear();
        return messageList;
    }

    public void addGroup(Group group){
        groupsToPull.add(group);
    }

    public List<Group> pullGroups(){
        List<Group> groupList = new ArrayList<>(groupsToPull);
        groupsToPull.clear();
        return groupList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}