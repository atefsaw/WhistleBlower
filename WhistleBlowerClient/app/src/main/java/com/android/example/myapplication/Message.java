package com.android.example.myapplication;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.TimeZone;

@Entity(primaryKeys = {"groupId", "sender", "time"})
public class Message {

    @NonNull
    public int groupId;

    @NonNull
    public String sender;

    public String content;

    @NonNull
    public String time;

    public boolean isBelongToUser;

    public Message() {
    }

    public Message(String content, String sender, int groupId, boolean isBelongsToCurrentUser) {
        this.content = content;
        this.sender = sender;
        this.groupId = groupId;
        this.time = initliazeTime();
        this.isBelongToUser = isBelongsToCurrentUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int group) {
        this.groupId = group;
    }

    public String getTime() {
        return this.time;
    }

    public String getUpdatedTime() {
        return this.time.substring(0, this.time.length() - 3);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isBelongsToCurrentUser() {
        return isBelongToUser;
    }

    public static String initliazeTime() {
        long millis = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        c.setTimeInMillis(millis);
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);
        String minutesFormat, secondsFormat;
        if (minutes < 10) {
            minutesFormat = "0" + minutes;
        }
        else {
            minutesFormat = String.valueOf(minutes);
        }
        if (seconds < 10) {
            secondsFormat = "0" + seconds;
        }
        else {
            secondsFormat = String.valueOf(seconds);
        }
        return String.format("%d:%s:%s", hours, minutesFormat, secondsFormat);
    }

}
