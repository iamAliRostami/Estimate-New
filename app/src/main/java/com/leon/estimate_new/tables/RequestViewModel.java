package com.leon.estimate_new.tables;

import android.view.View;
import android.widget.RadioGroup;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.leon.estimate_new.BR;
import com.leon.estimate_new.R;

import java.util.ArrayList;
import java.util.Arrays;

public class RequestViewModel extends BaseObservable {
    private String billId;
    private String address;
    private String mobile;
    private String firstName;
    private String sureName;
    private String nationalId;
    private int visibility;
    private boolean newRequest;
    private ArrayList<Integer> selectedServices;

    public RequestViewModel(boolean newRequest) {
        setNewRequest(newRequest);
        setSelectedServices(new ArrayList<>(7));
        setMobile("");
        setBillId("");
        setNationalId("");
        setSureName("");
        setFirstName("");
        setAddress("");
    }

    @Bindable
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
        notifyPropertyChanged(BR.billId);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }

    @Bindable
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
        notifyPropertyChanged(BR.nationalId);
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
        notifyPropertyChanged(BR.sureName);
    }

    public ArrayList<Integer> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(ArrayList<Integer> selectedServices) {
        this.selectedServices = selectedServices;
    }

    @Bindable
    public boolean isNewRequest() {
        return newRequest;
    }

    public void setNewRequest(boolean newRequest) {
        this.newRequest = newRequest;
        notifyPropertyChanged(BR.newRequest);
        setVisibility(isNewRequest() ? View.VISIBLE : View.GONE);
    }

    @Bindable
    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
        notifyPropertyChanged(BR.visibility);
    }

    public void onSplitTypeChanged(RadioGroup group, int checkedId) {
        final int id = group.getId();
        if (id == R.id.radio_group_request_type) {
            if (checkedId == R.id.radio_button_new) {
                setNewRequest(true);
                setSelectedServices(new ArrayList<>(7));
            } else if (checkedId == R.id.radio_button_after_sale) {
                setNewRequest(false);
                setSelectedServices(new ArrayList<>(Arrays.asList(1, 2)));
            }
            setNationalId("");
            setSureName("");
            setFirstName("");
            setAddress("");
        }
    }
}
