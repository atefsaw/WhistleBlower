package com.android.example.myapplication;


import java.util.List;

public class MessageListWrapper {

  private List<Message> messageList;

  public MessageListWrapper(List<Message> messageList) {
    this.messageList = messageList;
  }

  public List<Message> getMessageList() {
    return messageList;
  }

  public void setMessageList(List<Message> messageList) {
    this.messageList = messageList;
  }

}