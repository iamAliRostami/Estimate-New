package com.leon.estimate_new.tables;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "NoeVagozariDictionary", indices = @Index(value = {"id"}, unique = true))
public class NoeVagozariDictionary {
    @PrimaryKey
    public final int id;
    public final String title;
    public final boolean isSelected;
    public final boolean isDisabled;
    public final boolean hasSms;

    public NoeVagozariDictionary(int id, String title, boolean isSelected, boolean isDisabled, boolean hasSms) {
        this.id = id;
        this.title = title;
        this.isSelected = isSelected;
        this.isDisabled = isDisabled;
        this.hasSms = hasSms;
    }
}
