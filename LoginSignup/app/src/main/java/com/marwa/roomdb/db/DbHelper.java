package com.marwa.roomdb.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class},version = 1)
public abstract  class DbHelper  extends RoomDatabase{

    private static DbHelper INSTANCE;

    public static DbHelper getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DbHelper.class, "room.db").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

    public abstract UserDao userDao();

}
