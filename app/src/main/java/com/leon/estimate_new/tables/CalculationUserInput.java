package com.leon.estimate_new.tables;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "CalculationUserInput", indices = @Index(value = {"trackNumber"}, unique = true))

public class CalculationUserInput {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public boolean sent;
    public boolean ready;
    public String trackNumber;
    public String trackingId;
    public String radif;
    public int requestType;
    public String parNumber;
    public String licenceNumber;
    public String billId;
    public String neighbourBillId;
    public int zoneId;
    public String notificationMobile;
    public String selectedServicesString;
    public int qotrEnsheabId;
    public String qotrEnsheabS;
    public int noeVagozariId;
    public int taxfifId;
    public String phoneNumber;
    public String mobile;
    public String firstName;
    public String sureName;
    public int arse;
    public int arseNew;
    public int aianKol;
    public int aianKolNew;
    public int aianMaskooni;
    public int aianMaskooniNew;
    public int aianTejari;
    public int aianTejariNew;
    public int sifoon100;
    public int sifoon125;
    public int sifoon150;
    public int sifoon200;

    public int zarfiatQarardadi;
    public int tedadMaskooni;
    public int tedadMaskooniNew;
    public int tedadTejari;
    public int tedadTejariNew;
    public int tedadSaier;
    public int tedadSaierNew;
    public int tedadTaxfif;
    public String nationalId;
    public String identityCode;
    public String fatherName;
    public String postalCode;
    public boolean ensheabQeireDaem;
    public boolean adamTaxfifAb;
    public boolean adamTaxfifFazelab;
    public String address;
    public String description;
    public String shenasname;
    public int resultId;
    public double x1, x2, y1, y2;
    public double accuracy;

    public int arzeshMelk;
    public String block;
    public String arz;
    public int karbariId;
    public int pelak;
    public double x3, y3;
    public boolean sanad;
    public int sanadNumber;

    public String adamLicence;
    public String qaradad;
    public String qaradadNumber;

    public String blockId;
    @Ignore
    public ArrayList<RequestDictionary> selectedServicesObject;

    public CalculationUserInput() {
    }

    public void updateConstField(ExaminerDuties examinerDuty) {
        trackingId = examinerDuty.trackingId;
        requestType = Integer.parseInt(examinerDuty.requestType);
        licenceNumber = examinerDuty.licenceNumber;
        billId = examinerDuty.billId;
        neighbourBillId = examinerDuty.neighbourBillId;
        notificationMobile = examinerDuty.notificationMobile;
        identityCode = examinerDuty.identityCode;
        trackNumber = examinerDuty.trackNumber;
        sent = false;
    }
}
