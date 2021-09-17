package com.leon.estimate_new.tables.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.leon.estimate_new.tables.Tejariha;

import java.util.List;

@Dao
public interface TejarihaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTejariha(Tejariha tejariha);

    @Query("SELECT * FROM Tejariha WHERE trackNumber =:trackNumber")
    List<Tejariha> getTejarihaByTrackNumber(String trackNumber);


//    @Query("SELECT * FROM Tejariha WHERE trackNumber =:trackNumber")
//    ArrayList<Tejariha> getTejariByTrackNumber(String trackNumber);

    @Query("DELETE FROM Tejariha WHERE id = :id")
    void delete(int id);
}
