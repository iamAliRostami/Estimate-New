package com.leon.estimate_new.fragments.forms;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.leon.estimate_new.BR;

public class TechnicalInfoViewModel extends BaseObservable {
    @Bindable
    private String faseleKhakiA;
    @Bindable
    private String faseleKhakiF;
    @Bindable
    private String faseleAsphaltA;
    @Bindable
    private String faseleAsphaltF;
    @Bindable
    private String faseleSangA;
    @Bindable
    private String faseleSangF;
    @Bindable
    private String faseleOtherA;
    @Bindable
    private String faseleOtherF;
    @Bindable
    private String omqeZirzamin;
    @Bindable
    private boolean chahAbBaran;
    @Bindable
    private int qotrLooleI;
    @Bindable
    private int jensLooleI;
    @Bindable
    private String qotrLooleS;
    @Bindable
    private String jensLooleS;
    @Bindable
    private boolean looleA;
    @Bindable
    private boolean looleF;
    @Bindable
    private String eshterak;
    @Bindable
    private boolean newEnsheab;
    @Bindable
    private boolean ezharNazarA;
    private String block;
    private String arz;

    public String getFaseleKhakiA() {
        return faseleKhakiA;
    }

    public void setFaseleKhakiA(String faseleKhakiA) {
        this.faseleKhakiA = faseleKhakiA;
        notifyPropertyChanged(BR.faseleKhakiA);
    }

    public String getFaseleKhakiF() {
        return faseleKhakiF;
    }

    public void setFaseleKhakiF(String faseleKhakiF) {
        this.faseleKhakiF = faseleKhakiF;
        notifyPropertyChanged(BR.faseleKhakiF);
    }

    public String getFaseleAsphaltA() {
        return faseleAsphaltA;
    }

    public void setFaseleAsphaltA(String faseleAsphaltA) {
        this.faseleAsphaltA = faseleAsphaltA;
        notifyPropertyChanged(BR.faseleAsphaltA);
    }

    public String getFaseleAsphaltF() {
        return faseleAsphaltF;
    }

    public void setFaseleAsphaltF(String faseleAsphaltF) {
        this.faseleAsphaltF = faseleAsphaltF;
        notifyPropertyChanged(BR.faseleAsphaltF);
    }

    public String getFaseleSangA() {
        return faseleSangA;
    }

    public void setFaseleSangA(String faseleSangA) {
        this.faseleSangA = faseleSangA;
        notifyPropertyChanged(BR.faseleSangA);
    }

    public String getFaseleSangF() {
        return faseleSangF;
    }

    public void setFaseleSangF(String faseleSangF) {
        this.faseleSangF = faseleSangF;
        notifyPropertyChanged(BR.faseleSangF);
    }

    public String getFaseleOtherA() {
        return faseleOtherA;
    }

    public void setFaseleOtherA(String faseleOtherA) {
        this.faseleOtherA = faseleOtherA;
        notifyPropertyChanged(BR.faseleOtherA);
    }

    public String getFaseleOtherF() {
        return faseleOtherF;
    }

    public void setFaseleOtherF(String faseleOtherF) {
        this.faseleOtherF = faseleOtherF;
        notifyPropertyChanged(BR.faseleOtherF);
    }

    public String getOmqeZirzamin() {
        return omqeZirzamin;
    }

    public void setOmqeZirzamin(String omqeZirzamin) {
        this.omqeZirzamin = omqeZirzamin;
        notifyPropertyChanged(BR.omqeZirzamin);
    }

    public boolean isChahAbBaran() {
        return chahAbBaran;
    }

    public void setChahAbBaran(boolean chahAbBaran) {
        this.chahAbBaran = chahAbBaran;
        notifyPropertyChanged(BR.chahAbBaran);
    }

    public int getQotrLooleI() {
        return qotrLooleI;
    }

    public void setQotrLooleI(int qotrLooleI) {
        this.qotrLooleI = qotrLooleI;
        notifyPropertyChanged(BR.qotrLooleI);
    }

    public int getJensLooleI() {
        return jensLooleI;
    }

    public void setJensLooleI(int jensLooleI) {
        this.jensLooleI = jensLooleI;
        notifyPropertyChanged(BR.jensLooleI);
    }

    public String getQotrLooleS() {
        return qotrLooleS;
    }

    public void setQotrLooleS(String qotrLooleS) {
        this.qotrLooleS = qotrLooleS;
        notifyPropertyChanged(BR.qotrLooleS);
    }

    public String getJensLooleS() {
        return jensLooleS;
    }

    public void setJensLooleS(String jensLooleS) {
        this.jensLooleS = jensLooleS;
        notifyPropertyChanged(BR.jensLooleS);
    }

    public boolean isLooleA() {
        return looleA;
    }

    public void setLooleA(boolean looleA) {
        this.looleA = looleA;
        notifyPropertyChanged(BR.looleA);
    }

    public boolean isLooleF() {
        return looleF;
    }

    public void setLooleF(boolean looleF) {
        this.looleF = looleF;
        notifyPropertyChanged(BR.looleF);
    }

    public String getEshterak() {
        return eshterak.trim();
    }

    public void setEshterak(String eshterak) {
        this.eshterak = eshterak;
        notifyPropertyChanged(BR.eshterak);
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

    public boolean isNewEnsheab() {
        return newEnsheab;
    }

    public void setNewEnsheab(boolean newEnsheab) {
        this.newEnsheab = newEnsheab;
        notifyPropertyChanged(BR.newEnsheab);
    }

    public boolean isEzharNazarA() {
        return ezharNazarA;
    }

    public void setEzharNazarA(boolean ezharNazarA) {
        this.ezharNazarA = ezharNazarA;
        notifyPropertyChanged(BR.ezharNazarA);
    }
}
