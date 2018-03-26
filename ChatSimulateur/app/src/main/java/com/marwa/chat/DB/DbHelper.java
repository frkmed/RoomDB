package com.marwa.chat.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.marwa.chat.DB.converter.Converter;
import com.marwa.chat.DB.dao.ChatDao;
import com.marwa.chat.DB.entity.Chat;
import com.marwa.chat.DB.entity.Msg;


@Database(entities = {Chat.class, Msg.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class DbHelper extends RoomDatabase {

    private static DbHelper INSTANCE;

    public static DbHelper getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, DbHelper.class, "chat.db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }


    public abstract ChatDao chatDao();


}
