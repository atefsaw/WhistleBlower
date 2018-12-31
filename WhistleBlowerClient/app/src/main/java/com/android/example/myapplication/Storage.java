package com.android.example.myapplication;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Storage extends Application {

    public static Storage instance = null;

    public static List<Message> listOfMessages = new ArrayList<>();
    public static List<Group> listOfGroups = new ArrayList<>();

    public static Context getInstance() {
        if (null == instance) {
            instance = new Storage();
        }
        return instance;
    }

    public static List<Message> getListOfMessages() {
        return listOfMessages;
    }


}
