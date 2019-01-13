package com.android.example.myapplication;

public class Contact {
    private String name;
    private String phoneNumber;
    private boolean isSelected;


    public Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isSelected = false;
    }

    public String getName(){
        return this.name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public boolean getIsSelected(){
        return isSelected;
    }

    public void setSelected(){
        isSelected = !isSelected;
    }
}
