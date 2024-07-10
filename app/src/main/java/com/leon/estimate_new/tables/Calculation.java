package com.leon.estimate_new.tables;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Calculation", indices = @Index(value = {"examinationId"}, unique = true))

public class Calculation {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public final String address;
    public final String billId;
    public final String examinationDay;
    public final String examinationId;
    public final String moshtarakMobile;
    public final String nameAndFamily;
    public final String neighbourBillId;
    public final String notificationMobile;
    public final String radif;
    public final String serviceGroup;
    public final String trackNumber;
    public final boolean isPeymayesh;
    public boolean read;

    public Calculation(String address, String billId, String examinationDay,
                       String examinationId, boolean isPeymayesh, String moshtarakMobile,
                       String nameAndFamily, String neighbourBillId, String notificationMobile,
                       String radif, String serviceGroup, String trackNumber) {
        this.address = address;
        this.billId = billId;
        this.examinationDay = examinationDay;
        this.examinationId = examinationId;
        this.isPeymayesh = isPeymayesh;
        this.moshtarakMobile = moshtarakMobile;
        this.nameAndFamily = nameAndFamily;
        this.neighbourBillId = neighbourBillId;
        this.notificationMobile = notificationMobile;
        this.radif = radif;
        this.serviceGroup = serviceGroup;
        this.trackNumber = trackNumber;
    }
}