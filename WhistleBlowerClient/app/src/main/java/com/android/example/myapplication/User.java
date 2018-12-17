package com.android.example.myapplication;


import java.util.ArrayList;
import java.util.List;

public class User {

    private int userId;
    private List<Message> messagesToPull;
    private List<Group> groupsToPull;

    public void addMessage(Message message){
        messagesToPull.add(message);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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



}
