
package com.android.example.myapplication;

import java.util.Calendar;
import java.util.TimeZone;


public class Message {

    private String content;
    private User sender;
    private int groupId;
    private String time;
    private boolean isBelongsToCurrentUser;

    public Message() {
    }

    public Message(String content, User sender, int groupId, boolean isBelongsToCurrentUser) {
        this.content = content;
        this.sender = sender;
        this.groupId = groupId;
        this.time = initliazeTime();
        this.isBelongsToCurrentUser = isBelongsToCurrentUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int group) {
        this.groupId = group;
    }

    public String getTime() {
        return this.time.substring(0, this.time.length() - 3);
    }

    public String getRealTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isBelongsToCurrentUser() {
        return isBelongsToCurrentUser;
    }

    private String initliazeTime() {
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