package com.android.example.myapplication;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class ChatViewModel extends ViewModel {

    private ChatRepository chatRepository;

    private LiveData<List<Message>> messages;

    public ChatViewModel(Application application, int groupId){
        chatRepository = new ChatRepository(application, groupId);
        messages = chatRepository.getAllMessages();
    }

    public void insertMessage(Message groupMessage) {
        chatRepository.insert(groupMessage);
    }


    /** Messages area */
    public LiveData<List<Message>> getAllMessagesForGroup() {
        return messages;
    }
}
