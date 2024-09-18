package com.leon.estimate_new.fragments.dialog;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.leon.estimate_new.BR;

public class AddDocumentViewModel extends BaseObservable {
    @Bindable
    private String firstName;
    @Bindable
    private String sureName;
    @Bindable
    private String address;
    @Bindable
    private String trackNumber;
    private String zoneId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
        notifyPropertyChanged(BR.sureName);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
        notifyPropertyChanged(BR.trackNumber);
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
