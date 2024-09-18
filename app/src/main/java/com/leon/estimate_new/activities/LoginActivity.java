package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.SharedReferenceKeys.ADDRESS;
import static com.leon.estimate_new.enums.SharedReferenceKeys.DOCUMENT_ADDRESS;
import static com.leon.estimate_new.enums.SharedReferenceKeys.MAP_ADDRESS;
import static com.leon.estimate_new.helpers.MyApplication.getApplicationComponent;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getActiveCompanyName;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getCompanyName;
import static com.leon.estimate_new.utils.PermissionManager.isNetworkAvailable;
import static com.leon.estimate_new.utils.Validator.checkEmpty;
import static com.leon.estimate_new.utils.Validator.proxyValidation;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityLoginBinding;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.login.AttemptLogin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener {
    private ActivityLoginBinding binding;
    private LoginViewModel loginVM;
    private long lastClickTime = 0;
    private boolean login = true;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        loginVM = new LoginViewModel(getString(R.string.version));
        binding.setLoginVm(loginVM);
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        AppCompatTextView textViewCompanyName = findViewById(R.id.text_view_company_name);
        textViewCompanyName.setText(getCompanyName(getActiveCompanyName()));
        binding.buttonLogin.setOnLongClickListener(this);
        binding.buttonLogin.setOnClickListener(this);
        binding.imageViewInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.button_login) {
            if (login)
                attempt(true);
            else {
                setUrls();
            }
        } else if (id == R.id.image_view_info) {
            switchAddressLogin(true);
        }
    }

    private void switchAddressLogin(boolean address) {
        login = !login;
        if (address) {
            binding.buttonLogin.setText(getString(R.string.set_ip));
            binding.layoutProxy.setVisibility(View.VISIBLE);
            binding.layoutDocumentProxy.setVisibility(View.VISIBLE);
            binding.layoutMapProxy.setVisibility(View.VISIBLE);
            binding.layoutPassword.setVisibility(View.GONE);
            binding.layoutUsername.setVisibility(View.GONE);
            binding.checkBoxSave.setVisibility(View.GONE);
            binding.imageViewInfo.setVisibility(View.GONE);
        } else {
            binding.buttonLogin.setText(getString(R.string.login));
            binding.layoutProxy.setVisibility(View.GONE);
            binding.layoutDocumentProxy.setVisibility(View.GONE);
            binding.layoutMapProxy.setVisibility(View.GONE);
            binding.layoutPassword.setVisibility(View.VISIBLE);
            binding.layoutUsername.setVisibility(View.VISIBLE);
            binding.checkBoxSave.setVisibility(View.VISIBLE);
            binding.imageViewInfo.setVisibility(View.VISIBLE);
        }
    }

    private void setUrls() {
        final String ip = loginVM.getAddress();
        boolean cancel = false;
        if (ip != null && (ip.isEmpty() || proxyValidation(binding.editTextProxy, this))) {
            getApplicationComponent().SharedPreferenceModel().putData(ADDRESS.getValue(),
                    ip);
            new CustomToast().success("آدرس با موفقیت تنظیم شد", Toast.LENGTH_LONG);
        } else {
            cancel = true;
        }

        final String mapIp = loginVM.getMapAddress();
        if (mapIp != null && (mapIp.isEmpty() || proxyValidation(binding.editTextMapProxy, this))) {
            getApplicationComponent().SharedPreferenceModel().putData(MAP_ADDRESS.getValue(),
//                    mapIp);
                    (mapIp.isEmpty() || mapIp.endsWith("/")) ? mapIp : mapIp.concat("/"));
            new CustomToast().success("آدرس نقشه با موفقیت تنظیم شد", Toast.LENGTH_LONG);
        } else {
            cancel = true;
        }

        final String documentIp = loginVM.getDocumentAddress();
        if (documentIp != null && (documentIp.isEmpty() || proxyValidation(binding.editTextDocumentProxy, this))) {
            getApplicationComponent().SharedPreferenceModel().putData(DOCUMENT_ADDRESS.getValue(),
                    documentIp);
            new CustomToast().success("آدرس مدارک با موفقیت تنظیم شد", Toast.LENGTH_LONG);
        } else {
            cancel = true;
        }

        if (!cancel) {
            switchAddressLogin(false);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return false;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.button_login) {
            if (login)
                attempt(false);
        }
        return false;
    }

    private void attempt(boolean online) {
        if (checkEmpty(binding.editTextUsername, this) &&
                checkEmpty(binding.editTextPassword, this)) {
            if (isNetworkAvailable(getApplicationContext())) {
                if (online)
                    new AttemptLogin(loginVM.getUsername(), loginVM.getPassword(), loginVM.isSave())
                            .execute(this);
                else
                    new AttemptLogin(loginVM.getUsername(), loginVM.getPassword(), loginVM.isSave(),
                            false).execute(this);
            } else {
                new CustomToast().warning(getString(R.string.turn_internet_on));
            }
        }
    }

    @Override
    protected void onStop() {
        Debug.getNativeHeapAllocatedSize();
        System.runFinalization();
        Runtime.getRuntime().totalMemory();
        Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().maxMemory();
        Runtime.getRuntime().gc();
        System.gc();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}