package com.leon.estimate_new.tables;

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
    public String karbariS;
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
    public String licenceNumber;
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
    public Integer arseNew;
    public int aianKol;
    public Integer aianKolNew;
    public int aianMaskooni;
    public Integer aianMaskooniNew;
    public int aianNonMaskooni;
    public Integer aianNonMaskooniNew;
    public int qotrEnsheabId;
    public String qotrEnsheabS;

    public int qotrEnsheabFId;
    public String qotrEnsheabFS;
    public int sifoon100;
    public int sifoon125;
    public int sifoon150;
    public int sifoon200;
    public int zarfiatQarardadi;
    public Integer zarfiatQarardadiNew;
    public int arzeshMelk;

    public String blockId;
    public String block;
    public String arz;
    public int tedadMaskooni;
    public Integer tedadMaskooniNew;
    public int tedadTejari;
    public Integer tedadTejariNew;
    public int tedadSaier;
    public Integer tedadSaierNew;
    public int taxfifId;
    public String taxfifS;
    public int tedadTaxfif;
    public String nationalId;
    public String identityCode;
    public String fatherName;
    public String postalCode;
    public String description;
    public boolean adamTaxfifAb;
    public boolean adamTaxfifFazelab;
    public boolean isEnsheabQeirDaem;
    public String requestDictionaryString;
    public String shenasname;

    public boolean ezharNazarA;

    public String ensheabType;
    public int qotrLooleI;
    public int jensLooleI;
    public String qotrLooleS;
    public String jensLooleS;
    public boolean looleA;
    public boolean looleF;
    public int noeVagozariId;
    public String noeVagozariS;
    public int pelak;
    public int sanadNumber;
    public String examinerName;
    public String eshterak;

    public boolean adamLicence;
    public boolean qaradad;
    public String qaradadNumber;

    public int faseleKhakiA;
    public int faseleKhakiF;
    public int faseleAsphaltA;
    public int faseleAsphaltF;
    public int faseleSangA;
    public int faseleSangF;
    public int faseleOtherA;
    public int faseleOtherF;
    public int omqeZirzamin;
    public boolean chahAbBaran;
    public boolean isEdited;

    public double x1, x2, y1, y2;
    public String sodurDate;
    @Ignore
    public String operation;
    @Ignore
    public ArrayList<RequestDictionary> requestDictionary;

    public boolean isPeymayesh() {
        return isPeymayesh;
    }

    public String getExaminationDay() {
        return examinationDay;
    }
}
