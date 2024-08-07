package com.leon.estimate_new.tables;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ServiceDictionary", indices = @Index(value = {"id"}, unique = true))
public class ServiceDictionary {
    @PrimaryKey
    public final int id;
    public final String title;
    public final boolean hasSms;
    public final boolean isSelected;
    public final boolean isDisabled;

    public ServiceDictionary(int id, String title, boolean isSelected, boolean isDisabled, boolean hasSms) {
        this.id = id;
        this.title = title;
        this.isSelected = isSelected;
        this.isDisabled = isDisabled;
        this.hasSms = hasSms;
    }
}