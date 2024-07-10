package com.leon.estimate_new.tables;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tejariha", indices = @Index(value = {"id"}, unique = true))
public class Tejariha {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public final String karbari;
    public final String noeShoql;
    public final int tedadVahed;
    public final int capacity;
    public final String vahedMohasebe;
    public String tozihat;
    public final String a;
    public final String trackNumber;


    public Tejariha(String karbari, String noeShoql, int tedadVahed, String vahedMohasebe, String a,
                    int capacity, String trackNumber) {
        this.vahedMohasebe = vahedMohasebe;
        this.trackNumber = trackNumber;
        this.tedadVahed = tedadVahed;
        this.capacity = capacity;
        this.noeShoql = noeShoql;
        this.karbari = karbari;
        this.a = a;
    }
}
