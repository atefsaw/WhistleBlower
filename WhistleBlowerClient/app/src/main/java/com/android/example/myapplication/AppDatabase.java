package com.android.example.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {GroupItem.class, GroupMember.class, GroupMessages.class, CurrentUserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;

    public abstract GroupDao groupModel();
    public abstract CurrentUserDao currentUserDao();

    public static AppDatabase getInMemoryDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .build();

        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }


}
