package com.leon.estimate_new.tables;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    public String billId;
    public String neighbourBillId;
    public int zoneId;
    public String notificationMobile;
    public String selectedServicesString;
    public int qotrEnsheabId;
    public int noeVagozariId;
    public int taxfifId;
    public int arse;
    public String phoneNumber;
    public String mobile;
    public String firstName;
    public String sureName;
    public int aianKol;
    public int aianMaskooni;
    public int aianTejari;
    public int sifoon100;
    public int sifoon125;
    public int sifoon150;
    public int sifoon200;

    public int zarfiatQarardadi;
    public int tedadMaskooni;
    public int tedadTejari;
    public int tedadSaier;
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
    public int karbariId;

    @Ignore
    public ArrayList<RequestDictionary> selectedServicesObject;
    @Ignore
    int pelak;
    @Ignore
    public double x3, y3;

    public CalculationUserInput() {
    }

    public void setSelectedServicesString(CalculationUserInputSend calculationUserInput) {
        for (int s : calculationUserInput.selectedServices) {
            selectedServicesString = selectedServicesString.concat(String.valueOf(s)).concat(",");
        }
    }

    public ArrayList<RequestDictionary> setSelectedServices(CalculationUserInput calculationUserInput) {
        String json = calculationUserInput.selectedServicesString;
        Gson gson = new GsonBuilder().create();
        Type userListType = new TypeToken<ArrayList<RequestDictionary>>() {
        }.getType();
        return gson.fromJson(json, userListType);
    }

    public void preparePersonal(CalculationUserInput calculationUserInput) {
        nationalId = calculationUserInput.nationalId;
        firstName = calculationUserInput.firstName;
        sureName = calculationUserInput.sureName;
        fatherName = calculationUserInput.fatherName;
        postalCode = calculationUserInput.postalCode;
        radif = calculationUserInput.radif;
        phoneNumber = calculationUserInput.phoneNumber;
        mobile = calculationUserInput.mobile;
        address = calculationUserInput.address;
        description = calculationUserInput.description;
        shenasname = calculationUserInput.shenasname;
        zoneId = calculationUserInput.zoneId;
    }

    public void fillCalculationUserInput(ExaminerDuties examinerDuties) {
        trackingId = examinerDuties.trackingId;
        requestType = Integer.parseInt(examinerDuties.requestType);
        parNumber = examinerDuties.parNumber;
        billId = examinerDuties.billId;
        neighbourBillId = examinerDuties.neighbourBillId;
        notificationMobile = examinerDuties.notificationMobile;
        identityCode = examinerDuties.identityCode;
        trackNumber = examinerDuties.trackNumber;
        sent = false;
    }

    public void prepareCalculationUserInputFromForm(CalculationUserInput calculationUserInput) {
        sifoon100 = calculationUserInput.sifoon100;
        sifoon125 = calculationUserInput.sifoon125;
        sifoon150 = calculationUserInput.sifoon150;
        sifoon200 = calculationUserInput.sifoon200;
        arse = calculationUserInput.arse;
        aianKol = calculationUserInput.aianKol;
        aianMaskooni = calculationUserInput.aianMaskooni;
        aianTejari = calculationUserInput.aianTejari;
        tedadMaskooni = calculationUserInput.tedadMaskooni;
        tedadTejari = calculationUserInput.tedadTejari;
        tedadSaier = calculationUserInput.tedadSaier;
        arzeshMelk = calculationUserInput.arzeshMelk;
        tedadTaxfif = calculationUserInput.tedadTaxfif;
        zarfiatQarardadi = calculationUserInput.zarfiatQarardadi;
        parNumber = calculationUserInput.parNumber;
        karbariId = calculationUserInput.karbariId;
        noeVagozariId = calculationUserInput.noeVagozariId;
        qotrEnsheabId = calculationUserInput.qotrEnsheabId;
        taxfifId = calculationUserInput.taxfifId;
        adamTaxfifAb = calculationUserInput.adamTaxfifAb;
        adamTaxfifFazelab = calculationUserInput.adamTaxfifFazelab;
        ensheabQeireDaem = calculationUserInput.ensheabQeireDaem;
    }
    public void updateCalculationUserInput(ExaminerDuties examinerDuty) {
        sifoon100 = examinerDuty.sifoon100;
        sifoon125 = examinerDuty.sifoon125;
        sifoon150 = examinerDuty.sifoon150;
        sifoon200 = examinerDuty.sifoon200;
        arse = examinerDuty.arse;
        aianKol = examinerDuty.aianKol;
        aianMaskooni = examinerDuty.aianMaskooni;
        aianTejari = examinerDuty.aianNonMaskooni;
        tedadMaskooni = examinerDuty.tedadMaskooni;
        tedadTejari = examinerDuty.tedadTejari;
        tedadSaier = examinerDuty.tedadSaier;
        arzeshMelk = examinerDuty.arzeshMelk;
        tedadTaxfif = examinerDuty.tedadTaxfif;
        zarfiatQarardadi = examinerDuty.zarfiatQarardadi;
        parNumber = examinerDuty.parNumber;
        karbariId = examinerDuty.karbariId;
        noeVagozariId = examinerDuty.noeVagozariId;
        qotrEnsheabId = examinerDuty.qotrEnsheabId;
        taxfifId = examinerDuty.taxfifId;
        ensheabQeireDaem = examinerDuty.isEnsheabQeirDaem;
        adamTaxfifAb = examinerDuty.adamTaxfifAb;
        adamTaxfifFazelab = examinerDuty.adamTaxfifFazelab;
    }
}
