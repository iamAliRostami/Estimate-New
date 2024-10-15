package com.leon.estimate_new.fragments.forms;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.leon.estimate_new.BR;

public class BaseInfoViewModel extends BaseObservable {
    @Bindable
    private String karbariS;
    public int karbariId;
    private int noeVagozariId;
    @Bindable
    private String noeVagozariS;
    @Bindable
    private String sifoon100;
    @Bindable
    private String sifoon125;
    @Bindable
    private String sifoon150;
    @Bindable
    private String sifoon200;
    private int qotrEnsheabId;
    @Bindable
    private String qotrEnsheabS;
    @Bindable
    private String qotrEnsheabFS;
    private String zarfiatQarardadi;
    @Bindable
    private String zarfiatQarardadiNew;
    @Bindable
    private String ensheabType;
    @Bindable
    private String licenceNumber;
    @Bindable
    private String sodurDate;
    @Bindable
    private String pelak;
    @Bindable
    private boolean qaradad;
    @Bindable
    private boolean ensheabQeirDaem;
    @Bindable
    private String sanadNumber;
    @Bindable
    private boolean adamLicence;
    @Bindable
    private String qaradadNumber;
    @Bindable
    private String arse;
    @Bindable
    private String arseNew;
    @Bindable
    private String aianKol;
    @Bindable
    private String aianKolNew;
    @Bindable
    private String aianMaskooni;
    @Bindable
    private String aianMaskooniNew;
    @Bindable
    private String aianNonMaskooni;
    @Bindable
    private String aianNonMaskooniNew;
    @Bindable
    private String block;
    @Bindable
    private String arz;
    @Bindable
    private String tedadMaskooni;
    @Bindable
    private String tedadMaskooniNew;
    @Bindable
    private String tedadTejari;
    @Bindable
    private String tedadTejariNew;
    @Bindable
    private String tedadSaier;
    @Bindable
    private String tedadSaierNew;
    @Bindable
    private int taxfifId;
    @Bindable
    private String taxfifS;
    @Bindable
    private String tedadTaxfif;
    @Bindable
    private String arzeshMelk;

    public void fillEmpty() {
        if (arseNew == null || arseNew.isEmpty())
            arseNew = arse;
        if (tedadMaskooniNew == null || tedadMaskooniNew.isEmpty())
            tedadMaskooniNew = tedadMaskooni;
        if (tedadSaierNew == null || tedadSaierNew.isEmpty())
            tedadSaierNew = tedadSaier;
        if (tedadTejariNew == null || tedadTejariNew.isEmpty())
            tedadTejariNew = tedadTejari;
        if (aianKolNew == null || aianKolNew.isEmpty())
            aianKolNew = aianKol;
        if (aianMaskooniNew == null || aianMaskooniNew.isEmpty())
            aianMaskooniNew = aianMaskooni;
        if (aianNonMaskooniNew == null || aianNonMaskooniNew.isEmpty())
            aianNonMaskooniNew = aianNonMaskooni;
        if (zarfiatQarardadiNew == null || zarfiatQarardadiNew.isEmpty())
            zarfiatQarardadiNew = zarfiatQarardadi;
    }

    public String getSifoon100() {
        return sifoon100;
    }

    public void setSifoon100(String sifoon100) {
        this.sifoon100 = sifoon100;
        notifyPropertyChanged(BR.sifoon100);
    }

    public String getSifoon125() {
        return sifoon125;
    }

    public void setSifoon125(String sifoon125) {
        this.sifoon125 = sifoon125;
        notifyPropertyChanged(BR.sifoon125);
    }

    public String getKarbariS() {
        return karbariS;
    }

    public void setKarbariS(String karbariS) {
        this.karbariS = karbariS;
        notifyPropertyChanged(BR.karbariS);
    }

    public int getKarbariId() {
        return karbariId;
    }

    public void setKarbariId(int karbariId) {
        this.karbariId = karbariId;
    }

    public int getNoeVagozariId() {
        return noeVagozariId;
    }

    public void setNoeVagozariId(int noeVagozariId) {
        this.noeVagozariId = noeVagozariId;
    }

    public String getNoeVagozariS() {
        return noeVagozariS;
    }

    public void setNoeVagozariS(String noeVagozariS) {
        this.noeVagozariS = noeVagozariS;
        notifyPropertyChanged(BR.noeVagozariS);
    }

    public String getSifoon150() {
        return sifoon150;
    }

    public void setSifoon150(String sifoon150) {
        this.sifoon150 = sifoon150;
        notifyPropertyChanged(BR.sifoon150);
    }

    public String getSifoon200() {
        return sifoon200;
    }

    public void setSifoon200(String sifoon200) {
        this.sifoon200 = sifoon200;
        notifyPropertyChanged(BR.sifoon200);
    }

    public int getQotrEnsheabId() {
        return qotrEnsheabId;
    }

    public void setQotrEnsheabId(int qotrEnsheabId) {
        this.qotrEnsheabId = qotrEnsheabId;
    }

    public String getQotrEnsheabS() {
        return qotrEnsheabS;
    }

    public void setQotrEnsheabS(String qotrEnsheabS) {
        this.qotrEnsheabS = qotrEnsheabS;
        notifyPropertyChanged(BR.qotrEnsheabS);
    }

    public String getQotrEnsheabFS() {
        return qotrEnsheabFS;
    }

    public void setQotrEnsheabFS(String qotrEnsheabFS) {
        this.qotrEnsheabFS = qotrEnsheabFS;
        notifyPropertyChanged(BR.qotrEnsheabFS);
    }

    public String getZarfiatQarardadi() {
        return zarfiatQarardadi;
    }

    public void setZarfiatQarardadi(String zarfiatQarardadi) {
        this.zarfiatQarardadi = zarfiatQarardadi;
    }

    public String getZarfiatQarardadiNew() {
        return zarfiatQarardadiNew;
    }

    public void setZarfiatQarardadiNew(String zarfiatQarardadiNew) {
        this.zarfiatQarardadiNew = zarfiatQarardadiNew;
    }

    public String getEnsheabType() {
        return ensheabType;
    }

    public void setEnsheabType(String ensheabType) {
        this.ensheabType = ensheabType;
        notifyPropertyChanged(BR.ensheabType);
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
        notifyPropertyChanged(BR.licenceNumber);
    }

    public String getSodurDate() {
        return sodurDate;
    }

    public void setSodurDate(String sodurDate) {
        this.sodurDate = sodurDate;
        notifyPropertyChanged(BR.sodurDate);
    }

    public boolean isQaradad() {
        return qaradad;
    }

    public void setQaradad(boolean qaradad) {
//        if (this.qaradad != qaradad) {
            this.qaradad = qaradad;
            notifyPropertyChanged(BR.qaradad);
//        }
    }

    public boolean isEnsheabQeirDaem() {
        return ensheabQeirDaem;
    }

    public void setEnsheabQeirDaem(boolean ensheabQeirDaem) {
        this.ensheabQeirDaem = ensheabQeirDaem;
        notifyPropertyChanged(BR.ensheabQeirDaem);
    }

    public String getSanadNumber() {
        return sanadNumber;
    }

    public void setSanadNumber(String sanadNumber) {
        this.sanadNumber = sanadNumber;
        notifyPropertyChanged(BR.sanadNumber);
    }

    public boolean isAdamLicence() {
        return adamLicence;
    }

    public void setAdamLicence(boolean adamLicence) {
        this.adamLicence = adamLicence;
        notifyPropertyChanged(BR.adamLicence);
    }

    public String getQaradadNumber() {
        return qaradadNumber;
    }

    public void setQaradadNumber(String qaradadNumber) {
        this.qaradadNumber = qaradadNumber;
        notifyPropertyChanged(BR.qaradadNumber);
    }

    public String getArse() {
        return arse;
    }

    public void setArse(String arse) {
        this.arse = arse;
        notifyPropertyChanged(BR.arse);
    }

    public String getArseNew() {
        return arseNew;
    }

    public void setArseNew(String arseNew) {
        this.arseNew = arseNew;
        notifyPropertyChanged(BR.arseNew);
    }

    public String getAianKol() {
        return aianKol;
    }

    public void setAianKol(String aianKol) {
        this.aianKol = aianKol;
        notifyPropertyChanged(BR.aianKol);
    }

    public String getAianKolNew() {
        return aianKolNew;
    }

    public void setAianKolNew(String aianKolNew) {
        this.aianKolNew = aianKolNew;
        notifyPropertyChanged(BR.aianKolNew);
    }

    public String getAianMaskooni() {
        return aianMaskooni;
    }

    public void setAianMaskooni(String aianMaskooni) {
        this.aianMaskooni = aianMaskooni;
        notifyPropertyChanged(BR.aianMaskooni);
    }

    public String getAianMaskooniNew() {
        return aianMaskooniNew;
    }

    public void setAianMaskooniNew(String aianMaskooniNew) {
        this.aianMaskooniNew = aianMaskooniNew;
        notifyPropertyChanged(BR.aianMaskooniNew);
    }

    public String getAianNonMaskooni() {
        return aianNonMaskooni;
    }

    public void setAianNonMaskooni(String aianNonMaskooni) {
        this.aianNonMaskooni = aianNonMaskooni;
        notifyPropertyChanged(BR.aianNonMaskooni);
    }

    public String getAianNonMaskooniNew() {
        return aianNonMaskooniNew;
    }

    public void setAianNonMaskooniNew(String aianNonMaskooniNew) {
        this.aianNonMaskooniNew = aianNonMaskooniNew;
        notifyPropertyChanged(BR.aianNonMaskooniNew);
    }


    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
        notifyPropertyChanged(BR.block);
    }

    public String getArz() {
        return arz;
    }

    public void setArz(String arz) {
        this.arz = arz;
        notifyPropertyChanged(BR.arz);
    }

    public String getTedadMaskooni() {
        return tedadMaskooni;
    }

    public void setTedadMaskooni(String tedadMaskooni) {
        this.tedadMaskooni = tedadMaskooni;
        notifyPropertyChanged(BR.tedadMaskooni);
    }

    public String getTedadMaskooniNew() {
        return tedadMaskooniNew;
    }

    public void setTedadMaskooniNew(String tedadMaskooniNew) {
        this.tedadMaskooniNew = tedadMaskooniNew;
        notifyPropertyChanged(BR.tedadMaskooniNew);
    }

    public String getTedadTejari() {
        return tedadTejari;
    }

    public void setTedadTejari(String tedadTejari) {
        this.tedadTejari = tedadTejari;
        notifyPropertyChanged(BR.tedadTejariNew);
    }

    public String getTedadTejariNew() {
        return tedadTejariNew;
    }

    public void setTedadTejariNew(String tedadTejariNew) {
        this.tedadTejariNew = tedadTejariNew;
        notifyPropertyChanged(BR.tedadTejariNew);
    }

    public String getTedadSaier() {
        return tedadSaier;
    }

    public void setTedadSaier(String tedadSaier) {
        this.tedadSaier = tedadSaier;
        notifyPropertyChanged(BR.tedadSaier);
    }

    public String getTedadSaierNew() {
        return tedadSaierNew;
    }

    public void setTedadSaierNew(String tedadSaierNew) {
        this.tedadSaierNew = tedadSaierNew;
        notifyPropertyChanged(BR.tedadSaierNew);
    }

    public int getTaxfifId() {
        return taxfifId;
    }

    public void setTaxfifId(int taxfifId) {
        this.taxfifId = taxfifId;
        notifyPropertyChanged(BR.taxfifId);
    }

    public String getTaxfifS() {
        return taxfifS;
    }

    public void setTaxfifS(String taxfifS) {
        this.taxfifS = taxfifS;
        notifyPropertyChanged(BR.taxfifS);
    }

    public String getTedadTaxfif() {
        return tedadTaxfif;
    }

    public void setTedadTaxfif(String tedadTaxfif) {
        this.tedadTaxfif = tedadTaxfif;
        notifyPropertyChanged(BR.tedadTaxfif);
    }

    public String getArzeshMelk() {
        return arzeshMelk;
    }

    public void setArzeshMelk(String arzeshMelk) {
        this.arzeshMelk = arzeshMelk;
        notifyPropertyChanged(BR.arzeshMelk);
    }

    public String getPelak() {
        return pelak;
    }

    public void setPelak(String pelak) {
        this.pelak = pelak;
        notifyPropertyChanged(BR.pelak);
    }
}
