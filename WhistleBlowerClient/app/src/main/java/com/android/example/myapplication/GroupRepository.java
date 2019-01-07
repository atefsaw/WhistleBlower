package com.android.example.myapplication;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class GroupRepository {

    private GroupDao groupDao;

    private LiveData<List<GroupItem>> allGroups;

    public GroupRepository(Application application){
        AppDatabase db = AppDatabase.getInMemoryDatabase(application);
        groupDao = db.groupModel();
        allGroups = groupDao.getAllGroups();
    }

    public LiveData<List<GroupItem>> getAllGroups() {
        return allGroups;
    }

    public void insert(GroupItem groupItem){
        new insertAsyncTask(groupDao).execute(groupItem);
    }

    private static class insertAsyncTask extends AsyncTask<GroupItem, Void, Void>{

        private GroupDao asyncGroupdDao;

        insertAsyncTask(GroupDao dao){
            asyncGroupdDao = dao;
        }

        @Override
        protected Void doInBackground(GroupItem... groupItems) {
            asyncGroupdDao.updateGroup(groupItems[0]);
            return null;
        }



    }

}
