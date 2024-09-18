package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.SharedReferenceKeys.ADDRESS;
import static com.leon.estimate_new.enums.SharedReferenceKeys.DOCUMENT_ADDRESS;
import static com.leon.estimate_new.enums.SharedReferenceKeys.MAP_ADDRESS;
import static com.leon.estimate_new.enums.SharedReferenceKeys.PASSWORD;
import static com.leon.estimate_new.enums.SharedReferenceKeys.USERNAME;
import static com.leon.estimate_new.helpers.MyApplication.getAndroidVersion;
import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getActiveCompanyName;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getBaseUrl;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getDocumentUrl;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getMapUrl;

import android.os.Build;

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
    @Bindable
    private String address;
    @Bindable
    private String mapAddress;
    @Bindable
    private String documentAddress;

    public LoginViewModel(String version) {
        this.version = version.concat(" ").concat(BuildConfig.VERSION_NAME).concat("\n").
                concat(getAndroidVersion()).concat(" *** Arch: ").concat(Build.SUPPORTED_ABIS[0]);
        setSave(true);
        if (getPreferenceManager().checkIsNotEmpty(USERNAME.getValue()) &&
                getPreferenceManager().checkIsNotEmpty(PASSWORD.getValue())) {
            setUsername(getPreferenceManager().getStringData(USERNAME.getValue()));
            setPassword(Crypto.decrypt(getPreferenceManager().getStringData(PASSWORD.getValue())));
        }
        if (getPreferenceManager().checkIsNotEmpty(ADDRESS.getValue())) {
            setAddress(getPreferenceManager().getStringData(ADDRESS.getValue()));
        } else {
            setAddress(getBaseUrl(getActiveCompanyName()));
        }
        if (getPreferenceManager().checkIsNotEmpty(MAP_ADDRESS.getValue())) {
            setMapAddress(getPreferenceManager().getStringData(MAP_ADDRESS.getValue()));
        } else {
            setMapAddress(getMapUrl(getActiveCompanyName()));
        }
        if (getPreferenceManager().checkIsNotEmpty(DOCUMENT_ADDRESS.getValue())) {
            setDocumentAddress(getPreferenceManager().getStringData(DOCUMENT_ADDRESS.getValue()));
        } else {
            setDocumentAddress(getDocumentUrl(getActiveCompanyName()));
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    public String getMapAddress() {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress) {
        this.mapAddress = mapAddress;
        notifyPropertyChanged(BR.mapAddress);
    }

    public String getDocumentAddress() {
        return documentAddress;
    }

    public void setDocumentAddress(String documentAddress) {
        this.documentAddress = documentAddress;
        notifyPropertyChanged(BR.documentAddress);
    }
}
