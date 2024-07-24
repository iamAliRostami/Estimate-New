package com.leon.estimate_new.fragments.forms;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.leon.estimate_new.BR;

public class BaseInfoViewModel extends BaseObservable {
    @Bindable
    private String sifoon100;
    @Bindable
    private String sifoon125;

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
}
