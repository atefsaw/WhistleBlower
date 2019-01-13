package com.android.example.myapplication;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface GroupDao {

//    @Update(onConflict = REPLACE)
//    void updateGroup(GroupItem groupItem);

    @Query("SELECT userId from GroupMember where groupId = :groupId")
    public List<Integer> getUserIds(int groupId);

    @Update(onConflict = REPLACE)
    public void updateMembers(GroupMember groupMember);

    @Insert
    public void insertMessage(Message message);

    @Query("SELECT groupLastMessage from GroupItem where groupId = :groupId")
    public String getLastMessage(int groupId);

    @Query("SELECT * from Message where groupId = :groupId order by time ASC")
    public LiveData<List<Message>> getMessagesForGroup(int groupId);

    @Query("SELECT * from GroupItem")
     LiveData<List<GroupItem>> getAllGroups();

    @Insert(onConflict = REPLACE)
    public void insertGroup(GroupItem groupItem);


}
