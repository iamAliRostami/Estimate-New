package com.leon.estimate_new.fragments.dialog;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.leon.estimate_new.BR;

public class SearchViewModel extends BaseObservable {
    @Bindable
    private String startDate;
    @Bindable
    private String billId;
    @Bindable
    private String trackNumber;
    @Bindable
    private String name;
    @Bindable
    private String family;
    @Bindable
    private String nationalId;
    @Bindable
    private String mobile;

    public SearchViewModel() {
        startDate = "";
        billId = "";
        trackNumber = "";
        name = "";
        family = "";
        nationalId = "";
        mobile = "";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        notifyPropertyChanged(BR.startDate);
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
        notifyPropertyChanged(BR.billId);
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
        notifyPropertyChanged(BR.trackNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
        notifyPropertyChanged(BR.family);
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
        notifyPropertyChanged(BR.nationalId);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }
}
