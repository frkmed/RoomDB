package com.marwa.roomdb.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {


    @Insert
    public void insert(User user);


    @Delete
    public void delete(User user);


    @Update
    public void update(User user);



    @Query("SELECT * FROM user")
    public List<User> getAll();


    @Query("SELECT * FROM user WHERE userName = :userName AND password = :password")
    public User testLogin(String userName,String password);


    @Query("SELECT * FROM user  WHERE id = :id")
    public User getUserById(int id);


}
