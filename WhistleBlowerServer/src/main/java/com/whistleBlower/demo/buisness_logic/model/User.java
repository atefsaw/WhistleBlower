package com.whistleBlower.demo.buisness_logic.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Group> groups;
    private String phoneNumber;


    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        groups = new ArrayList<>();
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

}
