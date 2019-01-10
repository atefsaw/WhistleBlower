package com.android.example.myapplication;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CurrentUserRepository {

    private CurrentUserDao currentUserDao;


    public CurrentUserRepository(Application application){
        AppDatabase db = AppDatabase.getInMemoryDatabase(application);
        currentUserDao = db.currentUserDao();
    }


    public void insert(String userPhoneNumber){
        currentUserDao.insert(new CurrentUserEntity(userPhoneNumber));
    }

    public List<CurrentUserEntity> getCurrentUserEntity() {
        return currentUserDao.getCurrentUserEntity();
    }

}
