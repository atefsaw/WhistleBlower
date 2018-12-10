package com.whistleBlower.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String phoneNumber;
    private List<Message> messagesToPull;
    private List<Group> groupsToPull;


    User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.messagesToPull = new ArrayList<>();
        this.groupsToPull = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
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


}
