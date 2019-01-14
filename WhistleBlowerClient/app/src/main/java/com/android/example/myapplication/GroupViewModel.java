package com.android.example.myapplication;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class GroupViewModel extends ViewModel {

    private GroupRepository groupRepository;

    private LiveData<List<GroupItem>> allGroups;

    public GroupViewModel(Application application){
        groupRepository = new GroupRepository(application);
        allGroups = groupRepository.getAllGroups();

    }

    /** Groups area */
    public LiveData<List<GroupItem>> getAllGroups() {
        return allGroups;
    }

    public void update(GroupItem groupItem) {
        groupRepository.insert(groupItem);
    }


}
