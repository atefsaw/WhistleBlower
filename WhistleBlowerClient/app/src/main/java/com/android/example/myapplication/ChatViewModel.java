package com.android.example.myapplication;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class ChatViewModel extends ViewModel {

    private ChatRepository chatRepository;

    private LiveData<List<GroupMessages>> groupMessages;

    public ChatViewModel(Application application, int groupId){
        chatRepository = new ChatRepository(application, groupId);
        groupMessages = chatRepository.getAllMessages();
    }

    public void insertMessage(GroupMessages groupMessages) {
        chatRepository.insert(groupMessages);
    }


    /** Messages area */
    public LiveData<List<GroupMessages>> getAllMessagesForGroup(int groupId) {
        return groupMessages;
    }
}
