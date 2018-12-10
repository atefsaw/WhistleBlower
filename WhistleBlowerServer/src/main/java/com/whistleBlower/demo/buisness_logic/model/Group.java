package com.whistleBlower.demo.buisness_logic.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id;
    private String name;
    private List<User> users;
    private List<Message> messages;
    private static int groupIdCounter = 0;


    public Group(List<User> users, String name) {
        this.name = name;
        this.users = users;
        this.id = generateGroupId();
        messages = new ArrayList<>();
        Message defaultMessage = new Message("Hello, This is anonymous... ", new User("0000000000"), this);
        messages.add(defaultMessage);
    }

    private int generateGroupId(){
        groupIdCounter++;
        return groupIdCounter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }






}
