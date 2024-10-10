package com.leon.estimate_new.fragments.dialog;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.leon.estimate_new.BR;

public class TejarihaSayerViewModel extends BaseObservable {
    @Bindable
    private String karbari;
    @Bindable
    private String noeShoql;
    @Bindable
    private String tedadVahed;
    @Bindable
    private String capacity;
    @Bindable
    private String vahedMohasebe;
    @Bindable
    private String a;
    private String trackNumber;

    public TejarihaSayerViewModel(String trackNumber,String karbari) {
        noeShoql = "";
        tedadVahed = "";
        capacity = "";
        vahedMohasebe = "";
        a = "";
        this.karbari = karbari;
        this.trackNumber = trackNumber;
    }

    public TejarihaSayerViewModel() {
        noeShoql = "";
        tedadVahed = "";
        capacity = "";
        vahedMohasebe = "";
        a = "";
    }

    public String getKarbari() {
        return karbari;
    }

    public void setKarbari(String karbari) {
        this.karbari = karbari;
        notifyPropertyChanged(BR.karbari);
    }

    public String getNoeShoql() {
        return noeShoql;
    }

    public void setNoeShoql(String noeShoql) {
        this.noeShoql = noeShoql;
        notifyPropertyChanged(BR.noeShoql);
    }

    public String getTedadVahed() {
        return tedadVahed;
    }

    public void setTedadVahed(String tedadVahed) {
        this.tedadVahed = tedadVahed;
        notifyPropertyChanged(BR.tedadVahed);
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
        notifyPropertyChanged(BR.capacity);
    }

    public String getVahedMohasebe() {
        return vahedMohasebe;
    }

    public void setVahedMohasebe(String vahedMohasebe) {
        this.vahedMohasebe = vahedMohasebe;
        notifyPropertyChanged(BR.vahedMohasebe);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
        notifyPropertyChanged(BR.a);
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }
}
