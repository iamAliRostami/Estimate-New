package com.leon.estimate_new.tables.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.leon.estimate_new.tables.CalculationUserInput;

import java.util.List;

@Dao
public interface CalculationUserInputDao {

    @Query("SELECT * FROM CalculationUserInput WHERE sent != 1 and ready ==1 ORDER BY trackNumber desc")
    List<CalculationUserInput> getCalculationUserInput();

    @Query("SELECT * FROM CalculationUserInput WHERE trackNumber =:trackingNumber")
    CalculationUserInput getCalculationUserInput(String trackingNumber);

    @Query("UPDATE CalculationUserInput SET sent = :sent  WHERE trackNumber = :trackNumber")
    void updateCalculationUserInput(boolean sent, String trackNumber);

    @Query("SELECT COUNT (*) FROM CalculationUserInput WHERE sent != 1 and ready ==1")
    int getCalculationUserInputUnsent();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CalculationUserInput calculationUserInput);

    @Query("DELETE FROM CalculationUserInput WHERE trackNumber = :trackNumber")
    void deleteByTrackNumber(String trackNumber);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<CalculationUserInput> values);

    @Update
    void updateCalculationUserInput(CalculationUserInput calculationUserInput);

    @Delete
    void deleteCalculationUserInput(CalculationUserInput calculationUserInput);
}
