package com.example.eventlistener;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserQueryDao {

    @Query("SELECT * FROM events")
    LiveData<List<DataModel>> fetchAllUsers();

    @Query("SELECT * FROM events")
    List<DataModel> getAllUsersList();

    @Insert
    void insertTask(DataModel dataModel);

    @Query("SELECT id, `action`, description,time FROM events WHERE id=:id")
    LiveData<DataModel> getUser(int id);

    @Query("DELETE FROM events WHERE id=:id")
    void deleteSpecific(int id);

    @Query("DELETE FROM events WHERE 1=1")
    void deleteAll();
    @Update
    void updateEvents(DataModel DataModel);
}