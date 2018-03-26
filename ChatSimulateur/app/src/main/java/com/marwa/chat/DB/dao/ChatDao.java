package com.marwa.chat.DB.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.marwa.chat.DB.entity.Chat;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
@Dao
public interface ChatDao{

    @Insert(onConflict  = REPLACE)
    void insert(Chat user);

    @Delete
    void delete(Chat user);

    @Query("SELECT * FROM chat")
    List<Chat> getAll();

    @Query("SELECT * FROM chat WHERE name IN(:A,:B)  order By dateMsg DESC")
    List<Chat> loadAllMsgByName(String A,String B);

    @Query("DELETE FROM msg")
    void deleteAllMsg();

    @Query("DELETE FROM chat")
    void deleteAllChat();

}
