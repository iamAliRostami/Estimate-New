package com.leon.estimate_new.tables;

import static com.leon.estimate_new.helpers.Constants.calculationUserInput;
import static com.leon.estimate_new.helpers.Constants.calculationUserInputTemp;
import static com.leon.estimate_new.helpers.Constants.examinerDuties;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "ExaminerDuties", indices = @Index(value = {"trackNumber", "id"}, unique = true))
public class ExaminerDuties {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String trackNumber;
    public String examinationId;
    public int karbariId;
    public String radif;
    public String billId;
    public String examinationDay;
    public String nameAndFamily;
    public String moshtarakMobile;
    public String notificationMobile;
    public String serviceGroup;
    public String address;
    public String neighbourBillId;
    public boolean isPeymayesh;
    public String trackingId;
    public String requestType;
    public String parNumber;
    public String zoneId;
    public String callerId;
    public String zoneTitle;
    public boolean isNewEnsheab;
    public String phoneNumber;
    public String mobile;
    public String firstName;
    public String sureName;
    public boolean hasFazelab;
    public String fazelabInstallDate;
    public boolean isFinished;
    public int arse;
    public int aianKol;
    public int aianMaskooni;
    public int aianNonMaskooni;
    public int qotrEnsheabId;
    public int sifoon100;
    public int sifoon125;
    public int sifoon150;
    public int sifoon200;
    public int zarfiatQarardadi;
    public int arzeshMelk;
    public int tedadMaskooni;
    public int tedadTejari;
    public int tedadSaier;
    public int taxfifId;
    public int tedadTaxfif;
    public String nationalId;
    public String identityCode;
    public String fatherName;
    public String postalCode;
    public String description;
    public boolean adamTaxfifAb;
    public boolean adamTaxfifFazelab;
    public boolean isEnsheabQeirDaem;
    public boolean hasRadif;
    public String requestDictionaryString;
    public String shenasname;

    public boolean ezhaNazarA;
    public boolean ezhaNazarF;
    public int qotrLooleI;
    public int jensLooleI;
    public String qotrLooleS;
    public String jensLooleS;
    public boolean looleA;
    public boolean looleF;
    public int noeMasrafI;
    public String noeMasrafS;
    public int vaziatNasbPompI;
    public boolean etesalZirzamin;
    public int omqFazelab;
    public int noeVagozariId;
    public int pelak;
    public boolean sanad;
    public String examinerName;
    public boolean estelamShahrdari, parvane, motaqazi;
    public String masrafDescription;
    public String chahDescription;
    public String mapDescription;

    public String eshterak;

    public int faseleKhakiA;
    public int faseleKhakiF;
    public int faseleAsphaultA;
    public int faseleAsphaultF;
    public int faseleSangA;
    public int faseleSangF;
    public int faseleOtherA;
    public int faseleOtherF;
    public int omqeZirzamin;
    public boolean chahAbBaran;

    @Ignore
    ArrayList<RequestDictionary> requestDictionary;

    public ExaminerDuties updateExaminerDuties(SecondForm secondForm) {
        faseleKhakiA = secondForm.khakiAb;
        faseleKhakiF = secondForm.khakiFazelab;
        faseleAsphaultA = secondForm.asphalutAb;
        faseleAsphaultF = secondForm.asphalutFazelab;
        faseleSangA = secondForm.sangFarshAb;
        faseleSangF = secondForm.sangFarshFazelab;
        faseleOtherA = secondForm.otherAb;
        faseleOtherF = secondForm.otherFazelab;
        ezhaNazarA = secondForm.ezhaNazarA;
        ezhaNazarF = secondForm.ezhaNazarF;
        qotrLooleI = secondForm.qotreLooleI;
        jensLooleI = secondForm.jenseLooleI;
        qotrLooleS = secondForm.qotreLoole;
        jensLooleS = secondForm.jenseLoole;
        looleA = secondForm.looleA;
        looleF = secondForm.looleF;
        noeMasrafI = secondForm.noeMasraf;
        noeMasrafS = secondForm.noeMasrafString;
        vaziatNasbPompI = secondForm.vaziatNasbePomp;
        omqeZirzamin = secondForm.omqeZirzamin;
        omqFazelab = secondForm.omqFazelab;
        etesalZirzamin = secondForm.etesalZirzamin;
        chahAbBaran = secondForm.chahAbBaran;
        chahDescription = secondForm.chahDescription;
        masrafDescription = secondForm.masrafDescription;
        eshterak = secondForm.eshterak;
        return this;
    }

    public ExaminerDuties updateExaminerDuties(CalculationUserInput calculationUserInput) {
        trackNumber = calculationUserInput.trackNumber;
        karbariId = calculationUserInput.karbariId;
        radif = calculationUserInput.radif;
        billId = calculationUserInput.billId;
        nameAndFamily = calculationUserInput.firstName.trim().concat(" ".concat(calculationUserInput.sureName.trim()));
        notificationMobile = calculationUserInput.notificationMobile;
        address = calculationUserInput.address;
        neighbourBillId = calculationUserInput.neighbourBillId;
        trackingId = calculationUserInput.trackingId;
        requestType = String.valueOf(calculationUserInput.requestType);
        parNumber = calculationUserInput.parNumber;
        phoneNumber = calculationUserInput.phoneNumber;
        mobile = calculationUserInput.mobile;
        firstName = calculationUserInput.firstName;
        sureName = calculationUserInput.sureName;
        arse = calculationUserInput.arse;
        aianKol = calculationUserInput.aianKol;
        aianMaskooni = calculationUserInput.aianMaskooni;
        aianNonMaskooni = calculationUserInput.aianTejari;
        qotrEnsheabId = calculationUserInput.qotrEnsheabId;
        sifoon100 = calculationUserInput.sifoon100;
        sifoon125 = calculationUserInput.sifoon125;
        sifoon150 = calculationUserInput.sifoon150;
        sifoon200 = calculationUserInput.sifoon200;
        zarfiatQarardadi = calculationUserInput.zarfiatQarardadi;
        arzeshMelk = calculationUserInput.arzeshMelk;
        tedadMaskooni = calculationUserInput.tedadMaskooni;
        tedadTejari = calculationUserInput.tedadTejari;
        tedadSaier = calculationUserInput.tedadSaier;
        taxfifId = calculationUserInput.taxfifId;
        tedadTaxfif = calculationUserInput.tedadTaxfif;
        nationalId = calculationUserInput.nationalId;
        identityCode = calculationUserInput.identityCode;
        fatherName = calculationUserInput.fatherName;
        postalCode = calculationUserInput.postalCode;
        description = calculationUserInput.description;
        adamTaxfifAb = calculationUserInput.adamTaxfifAb;
        adamTaxfifFazelab = calculationUserInput.adamTaxfifFazelab;
        isEnsheabQeirDaem = calculationUserInput.ensheabQeireDaem;
        requestDictionaryString = calculationUserInput.selectedServicesString;
        shenasname = calculationUserInput.shenasname;
        return this;
    }

    public void prepareExaminerDutiesFromForm ()
    {
        examinerDuties.sifoon100 = calculationUserInputTemp.sifoon100;
        examinerDuties.sifoon125 = calculationUserInputTemp.sifoon125;
        examinerDuties.sifoon150 = calculationUserInputTemp.sifoon150;
        examinerDuties.sifoon200 = calculationUserInputTemp.sifoon200;
        examinerDuties.arse = calculationUserInputTemp.arse;
        examinerDuties.aianMaskooni = calculationUserInputTemp.aianMaskooni;
        examinerDuties.aianNonMaskooni = calculationUserInputTemp.aianTejari;
        examinerDuties.aianKol = calculationUserInputTemp.aianKol;
        examinerDuties.tedadMaskooni = calculationUserInputTemp.tedadMaskooni;
        examinerDuties.tedadTejari = calculationUserInputTemp.tedadTejari;
        examinerDuties.tedadSaier = calculationUserInputTemp.tedadSaier;
        examinerDuties.tedadTaxfif = calculationUserInputTemp.tedadTaxfif;
        examinerDuties.zarfiatQarardadi = calculationUserInputTemp.zarfiatQarardadi;
        examinerDuties.arzeshMelk = calculationUserInputTemp.arzeshMelk;
        examinerDuties.parNumber = calculationUserInputTemp.parNumber;
        examinerDuties.karbariId = calculationUserInputTemp.karbariId;
        examinerDuties.qotrEnsheabId = calculationUserInputTemp.qotrEnsheabId;
        examinerDuties.taxfifId = calculationUserInputTemp.taxfifId;
        examinerDuties.isEnsheabQeirDaem = calculationUserInputTemp.ensheabQeireDaem;
        examinerDuties.noeVagozariId = calculationUserInputTemp.noeVagozariId;
    }

    public void prepareExaminerDutiesFromPersonal() {
        examinerDuties.nationalId = calculationUserInputTemp.nationalId;
        examinerDuties.firstName = calculationUserInputTemp.firstName;
        examinerDuties.sureName = calculationUserInputTemp.sureName;
        examinerDuties.nameAndFamily = calculationUserInputTemp.firstName.concat(" ")
                .concat(calculationUserInputTemp.sureName);
        examinerDuties.fatherName = calculationUserInputTemp.fatherName;
        examinerDuties.postalCode = calculationUserInput.postalCode;
        examinerDuties.phoneNumber = calculationUserInput.phoneNumber;
        examinerDuties.mobile = calculationUserInputTemp.mobile;
        examinerDuties.address = calculationUserInputTemp.address;
        examinerDuties.description = calculationUserInputTemp.description;
        examinerDuties.shenasname = calculationUserInputTemp.shenasname;
    }
}
