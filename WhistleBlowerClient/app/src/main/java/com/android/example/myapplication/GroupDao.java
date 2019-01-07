package com.android.example.myapplication;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface GroupDao {

    @Update(onConflict = REPLACE)
    void updateGroup(GroupItem groupItem);

    @Query("SELECT id from GroupMember where groupdId = :groupId")
    public List<String> getUserIds(int groupId);

    @Update(onConflict = REPLACE)
    public void updateMembers(GroupMember groupMember);

    @Query("SELECT lastMessage from GroupItemh where groupId = :groupId")
    public String getLastMessage(int groupId);

    @Query("SELECT * from GroupMessages where groupId = :groupId order by time ASC")
    public LiveData<List<GroupMessages>> getMessagesForGroup(int groupId);

    @Query("SELECT * from GroupItem")
    public LiveData<List<GroupItem>> getAllGroups();

}
