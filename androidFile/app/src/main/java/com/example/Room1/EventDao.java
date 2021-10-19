package com.example.Room1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EventDao {

    @Insert                                                         //插入資料
    void insert(Event... event);

    //@Update                                                       //更新資料
    //void updateEvent(int id);

    @Query("DELETE FROM Event_table")                           //查詢資料
    void deleteAll();

    //@Query("SELECT Title & Time FROM Event_table WHERE Date = date")                           //查詢資料
    //void findDetail(String date);

    @Query("SELECT * FROM Event_table WHERE Date = :date ORDER BY Time")             //查詢資料
    LiveData<List<Event>> findEventByDate(String date);

    @Query("SELECT * FROM Event_table ORDER BY DateTime")
    LiveData<List<Event>> getAllEvents();
}
