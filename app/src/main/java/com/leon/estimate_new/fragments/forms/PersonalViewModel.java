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
    private String phoneNumber;

    @Bindable
    private String mobile;

    @Bindable
    private String address;

    @Bindable
    private String description;

    @Bindable
    private String shenasname;

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
        return nationalId;
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
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        notifyPropertyChanged(BR.postalCode);
    }

    public String getRadif() {
        return radif;
    }

    public void setRadif(String radif) {
        this.radif = radif;
        notifyPropertyChanged(BR.radif);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    public String getDescription() {
        return description;
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
}

