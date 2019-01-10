package com.android.example.myapplication;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ChatRepository {

    private GroupDao groupDao; // groupDao already have method to get messages for chat

    private LiveData<List<GroupMessages>> groupMessages;

    public ChatRepository(Application application, int groupId){
        AppDatabase db = AppDatabase.getInMemoryDatabase(application);
        groupDao = db.groupModel();
        groupMessages = groupDao.getMessagesForGroup(groupId);
    }

    public LiveData<List<GroupMessages>> getAllMessages() {
        return groupMessages;
    }

    public void insert(GroupMessages groupMessage){
        new insertAsyncTask(groupDao).execute(groupMessage);
    }

    private static class insertAsyncTask extends AsyncTask<GroupMessages, Void, Void>{

        private GroupDao asyncGroupdDao;

        insertAsyncTask(GroupDao dao){
            asyncGroupdDao = dao;
        }

        @Override
        protected Void doInBackground(GroupMessages... groupMessage) {
            asyncGroupdDao.insertMessage(groupMessage[0]);
            return null;
        }

    }

}
