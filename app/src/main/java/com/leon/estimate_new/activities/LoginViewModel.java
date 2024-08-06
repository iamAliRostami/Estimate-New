package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.SharedReferenceKeys.PASSWORD;
import static com.leon.estimate_new.enums.SharedReferenceKeys.USERNAME;
import static com.leon.estimate_new.helpers.MyApplication.getAndroidVersion;
import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;

import android.os.Build;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.leon.estimate_new.BR;
import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.utils.Crypto;

public class LoginViewModel extends BaseObservable {
    @Bindable
    private String version;
    @Bindable
    private String username;
    @Bindable
    private String password;
    @Bindable
    private boolean save;

    public LoginViewModel(String version) {
        this.version = version.concat(" ").concat(BuildConfig.VERSION_NAME).concat("\n").
                concat(getAndroidVersion()).concat(" *** Arch: ").concat(Build.SUPPORTED_ABIS[0]);
        setSave(true);
        if (getPreferenceManager().checkIsNotEmpty(USERNAME.getValue()) &&
                getPreferenceManager().checkIsNotEmpty(PASSWORD.getValue())) {
            setUsername(getPreferenceManager().getStringData(USERNAME.getValue()));
            setPassword(Crypto.decrypt(getPreferenceManager().getStringData(PASSWORD.getValue())));
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
        notifyPropertyChanged(BR.version);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
        notifyPropertyChanged(BR.save);
    }
}
