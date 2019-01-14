package com.android.example.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {GroupItem.class, GroupMember.class, Message.class, CurrentUserEntity.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;

    public abstract GroupDao groupModel();
    public abstract CurrentUserDao currentUserDao();

    public static AppDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "myDatabase")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }


}
