package com.android.example.myapplication;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class CurrentUserViewModel extends ViewModel {

    private CurrentUserRepository currentUserRepository;

    private List<CurrentUserEntity> currentUser;

    public CurrentUserViewModel(Application application){
        currentUserRepository = new CurrentUserRepository(application);
        currentUser = currentUserRepository.getCurrentUserEntity();
    }

    /** Groups area */
    public List<CurrentUserEntity> getCurrentUser() {
        return currentUser;
    }

    public void insert(String userPhoneNumber) {
        currentUserRepository.insert(userPhoneNumber);
    }

}
