package com.leon.estimate_new.tables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CalculationUserInputSend {
    public final String trackingId;
    public final String trackNumber;
    public final String parNumber;
    public final String billId;
    public final String radif;
    public String neighbourBillId;
    public final String notificationMobile;
    public String phoneNumber;
    public final String mobile;
    public final String firstName;
    public final String sureName;
    public final String address;
    public String description;
    public final String nationalId;
    public final String identityCode;
    public final String fatherName;
    public final String postalCode;
    public final int requestType;
    public final int zoneId;
    public final int qotrEnsheabId;
    public final int noeVagozariId;
    public final int taxfifId;
    public final int arse;
    public final int aianKol;
    public final int aianMaskooni;
    public final int aianTejari;
    public final int sifoon100;
    public final int sifoon125;
    public final int sifoon150;
    public final int sifoon200;
    public final int zarfiatQarardadi;
    public final int tedadMaskooni;
    public final int tedadTejari;

    public final int tedadSaier;
    public final int tedadTaxfif;
    public final int resultId;
    public final boolean ensheabQeireDaem;
    public final boolean adamTaxfifAb;
    public final boolean adamTaxfifFazelab;
    public final String x1;
    public final String x2;
    public final String y1;
    public final String y2;
    public ArrayList<Integer> selectedServices;

    public final int karbariId;
    public final int arzeshMelk;
    public final double accuracy;
    public final String eshterak;
    public final int omqeZirzamin;
    public final boolean chahAbBaran;
    public final boolean hasMap;
    public final int faseleKhakiA;
    public final int faseleKhakiF;
    public final int faseleAsphaultA;
    public final int faseleAsphaultF;
    public final int faseleSangA;
    public final int faseleSangF;
    public final int faseleOtherA;
    public final int faseleOtherF;

    public String blockId;

    public CalculationUserInputSend(CalculationUserInput calculationUserInput, ExaminerDuties examinerDuties) {
        this.eshterak = examinerDuties.eshterak;
        this.omqeZirzamin = examinerDuties.omqeZirzamin;
        this.chahAbBaran = examinerDuties.chahAbBaran;
        this.faseleOtherF = examinerDuties.faseleOtherF;
        this.faseleOtherA = examinerDuties.faseleOtherA;
        this.faseleSangF = examinerDuties.faseleSangF;
        this.faseleSangA = examinerDuties.faseleSangA;
        this.faseleAsphaultF = examinerDuties.faseleAsphaltF;
        this.faseleAsphaultA = examinerDuties.faseleAsphaltA;
        this.faseleKhakiF = examinerDuties.faseleKhakiF;
        this.faseleKhakiA = examinerDuties.faseleKhakiA;

        this.postalCode = calculationUserInput.postalCode;
        this.fatherName = calculationUserInput.fatherName;
        this.identityCode = calculationUserInput.identityCode;
        this.trackingId = calculationUserInput.trackingId;
        this.trackNumber = calculationUserInput.trackNumber;
        this.requestType = calculationUserInput.requestType;
        this.parNumber = calculationUserInput.licenceNumber;
        this.billId = calculationUserInput.billId;
        this.radif = calculationUserInput.radif;
        this.zoneId = calculationUserInput.zoneId;
        this.notificationMobile = calculationUserInput.notificationMobile;
        this.karbariId = calculationUserInput.karbariId;
        this.qotrEnsheabId = calculationUserInput.qotrEnsheabId;
        this.noeVagozariId = calculationUserInput.noeVagozariId;
        this.taxfifId = calculationUserInput.taxfifId;
        this.mobile = calculationUserInput.mobile;
        this.firstName = calculationUserInput.firstName;
        this.sureName = calculationUserInput.sureName;
        this.arse = calculationUserInput.arse;
        this.aianKol = calculationUserInput.aianKol;
        this.aianMaskooni = calculationUserInput.aianMaskooni;
        this.aianTejari = calculationUserInput.aianTejari;
        this.sifoon100 = calculationUserInput.sifoon100;
        this.sifoon125 = calculationUserInput.sifoon125;
        this.sifoon150 = calculationUserInput.sifoon150;
        this.sifoon200 = calculationUserInput.sifoon200;
        this.zarfiatQarardadi = calculationUserInput.zarfiatQarardadi;
        this.arzeshMelk = calculationUserInput.arzeshMelk;
        this.tedadMaskooni = calculationUserInput.tedadMaskooni;
        this.tedadTejari = calculationUserInput.tedadTejari;
        this.tedadSaier = calculationUserInput.tedadSaier;
        this.tedadTaxfif = calculationUserInput.tedadTaxfif;
        this.nationalId = calculationUserInput.nationalId;
        this.ensheabQeireDaem = calculationUserInput.ensheabQeireDaem;
        this.adamTaxfifAb = calculationUserInput.adamTaxfifAb;
        this.adamTaxfifFazelab = calculationUserInput.adamTaxfifFazelab;
        this.address = calculationUserInput.address;
        this.resultId = calculationUserInput.resultId;
        this.x1 = String.valueOf(calculationUserInput.x1);
        this.x2 = String.valueOf(calculationUserInput.x2);
        this.y1 = String.valueOf(calculationUserInput.y1);
        this.y2 = String.valueOf(calculationUserInput.y2);
        this.accuracy = calculationUserInput.accuracy;
        this.blockId = calculationUserInput.blockId;
        hasMap = true;
        setSelectedServices(calculationUserInput);
    }

    private void setSelectedServices(CalculationUserInput calculationUserInput) {
        String json = calculationUserInput.selectedServicesString;
        Gson gson = new GsonBuilder().create();
        Type userListType = new TypeToken<ArrayList<RequestDictionary>>() {
        }.getType();
        ArrayList<RequestDictionary> requestDictionaryArrayList = gson.fromJson(json, userListType);
        selectedServices = new ArrayList<>();
        if (requestDictionaryArrayList != null && !requestDictionaryArrayList.isEmpty()) {
            for (RequestDictionary requestDictionary : requestDictionaryArrayList) {
                if (requestDictionary.isSelected) {
                    selectedServices.add(requestDictionary.id);
                }
            }
        }
    }
}
