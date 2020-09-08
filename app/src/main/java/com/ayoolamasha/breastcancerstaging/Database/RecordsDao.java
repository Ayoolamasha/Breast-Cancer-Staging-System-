package com.ayoolamasha.breastcancerstaging.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordsDao {

    @Insert
    void insert(Records records);

    @Update
    void update(Records records);

    @Delete
    void delete(Records records);

    @Query("DELETE FROM patients_records")
    void deleteAll();

    @Query("SELECT * FROM patients_records ORDER BY stageFigure DESC")
    LiveData<List<Records>> getAllRecords();
}
