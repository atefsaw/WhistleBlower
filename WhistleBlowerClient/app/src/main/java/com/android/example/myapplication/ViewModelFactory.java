package com.android.example.myapplication;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.android.example.myapplication.ChatViewModel;
import com.android.example.myapplication.GroupViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private int groupId;

    public ViewModelFactory(Application application, int params) {
        mApplication = application;
        groupId = params;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == GroupViewModel.class) {
            return (T) new GroupViewModel(mApplication);
        } else if (modelClass == ChatViewModel.class) {
            return (T) new ChatViewModel(mApplication, (Integer) groupId);
        } else if (modelClass == CurrentUserViewModel.class) {
            return (T) new CurrentUserViewModel(mApplication);
        } else {
            return super.create(modelClass);
        }
    }
}