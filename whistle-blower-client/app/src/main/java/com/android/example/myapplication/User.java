package com.android.example.myapplication;


import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Group> groups;
    private String phoneNumber;
    private int userId;


    public User(int userId, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.groups = new ArrayList<>();
        this.userId = userId;
    }


    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() { return this.userId; }

    public void setUsedId(int id) { this.userId = id; }


}
