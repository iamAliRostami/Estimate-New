package com.leon.estimate_new.activities;

import static com.leon.estimate_new.utils.DifferentCompanyManager.getActiveCompanyName;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getCompanyName;
import static com.leon.estimate_new.utils.PermissionManager.isNetworkAvailable;
import static com.leon.estimate_new.utils.Validator.checkEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.button_login) {
            attempt();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return false;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.button_login) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }

    private void attempt() {

        if (checkEmpty(binding.editTextUsername, this) &&
                checkEmpty(binding.editTextPassword, this)) {
            if (isNetworkAvailable(getApplicationContext())) {
                new AttemptLogin(loginVM.getUsername(), loginVM.getPassword(), loginVM.isSave())
                        .execute(this);
            } else {
                new CustomToast().warning(getString(R.string.turn_internet_on));
            }
        }
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
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
}