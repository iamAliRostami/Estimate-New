package com.leon.estimate_new.fragments.forms;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class PersonalViewModel extends BaseObservable {
    @Bindable
    private String firstName;

    @Bindable
    private String sureName;

    @Bindable
    private String nationalId;

    @Bindable
    private String fatherName;

    @Bindable
    private String postalCode;

    @Bindable
    private String radif;
    @Bindable
    private String eshterak;

    @Bindable
    private String phoneNumber;

    @Bindable
    private String mobile;

    @Bindable
    private String address;

    @Bindable
    private String description;

    @Bindable
    private String shenasname;

    @Bindable
    private String zoneTitle;

    @Bindable
    private String trackNumber;

    public String getFirstName() {
        return firstName.trim();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
        notifyPropertyChanged(BR.firstName);
    }

    public String getSureName() {
        return sureName.trim();
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
        notifyPropertyChanged(BR.sureName);
    }

    public String getNationalId() {
        return nationalId.trim();
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
        notifyPropertyChanged(BR.nationalId);
    }

    public String getFatherName() {
        return fatherName.trim();
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
        notifyPropertyChanged(BR.fatherName);
    }

    public String getPostalCode() {
        return postalCode.trim();
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        notifyPropertyChanged(BR.postalCode);
    }

    public String getRadif() {
        return radif.trim();
    }

    public void setRadif(String radif) {
        this.radif = radif;
        notifyPropertyChanged(BR.radif);
    }

    public String getEshterak() {
        return eshterak.trim();
    }

    public void setEshterak(String eshterak) {
        this.eshterak = eshterak;
        notifyPropertyChanged(BR.eshterak);
    }

    public String getPhoneNumber() {
        return phoneNumber.trim();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    public String getMobile() {
        return mobile.trim();
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }

    public String getAddress() {
        return address.trim();
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    public String getDescription() {
        return description.trim();
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    public String getShenasname() {
        return shenasname;
    }

    public void setShenasname(String shenasname) {
        this.shenasname = shenasname;
        notifyPropertyChanged(BR.shenasname);
    }

    public String getZoneTitle() {
        return zoneTitle;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setZoneTitle(String zoneTitle) {
        this.zoneTitle = zoneTitle;
        notifyPropertyChanged(BR.zoneTitle);
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
        notifyPropertyChanged(BR.trackNumber);
    }
}

