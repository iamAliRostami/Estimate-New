package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.SharedReferenceKeys.PASSWORD;
import static com.leon.estimate_new.enums.SharedReferenceKeys.USERNAME;
import static com.leon.estimate_new.helpers.MyApplication.getAndroidVersion;
import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getActiveCompanyName;
import static com.leon.estimate_new.utils.DifferentCompanyManager.getCompanyName;
import static com.leon.estimate_new.utils.PermissionManager.isNetworkAvailable;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityLoginBinding;
import com.leon.estimate_new.utils.Crypto;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.DifferentCompanyManager;
import com.leon.estimate_new.utils.login.AttemptLogin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener, View.OnFocusChangeListener {
    private ActivityLoginBinding binding;
    //    private Activity activity;
    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        activity = this;
        initialize();
    }

    private void initialize() {
        binding.textViewVersion.setText(getString(R.string.version).concat(" ").concat(getAndroidVersion())
                .concat(" *** ").concat(BuildConfig.VERSION_NAME));
        final TextView textViewCompanyName = findViewById(R.id.text_view_company_name);
        textViewCompanyName.setText(getCompanyName(getActiveCompanyName()));
        loadPreference();
        binding.imageViewPassword.setImageResource(R.drawable.img_password);
        binding.imageViewLogo.setImageResource(R.drawable.img_login_logo);
        binding.imageViewPerson.setImageResource(R.drawable.img_profile);
        binding.imageViewUsername.setImageResource(R.drawable.img_user);

        binding.editTextUsername.setOnFocusChangeListener(this);
        binding.editTextPassword.setOnFocusChangeListener(this);
        binding.imageViewPassword.setOnClickListener(this);
        binding.buttonLogin.setOnLongClickListener(this);
        binding.buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        final int id = v.getId();
        if (id == R.id.edit_text_username) {
            binding.editTextUsername.setHint("");
            if (hasFocus) {
                binding.linearLayoutUsername.setBackground(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_black_1));
                binding.editTextPassword.setTextColor(
                        ContextCompat.getColor(getApplicationContext(), android.R.color.black));
            } else {
                binding.linearLayoutUsername.setBackground(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_gray_2));
                binding.editTextPassword.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray_2));
            }
        } else if (id == R.id.edit_text_password) {
            binding.editTextPassword.setHint("");
            if (hasFocus) {
                binding.linearLayoutPassword.setBackground(ContextCompat.getDrawable(
                        getApplicationContext(), R.drawable.border_black_1));
                binding.editTextPassword.setTextColor(ContextCompat.getColor(
                        getApplicationContext(), android.R.color.black));
            } else {
                binding.linearLayoutPassword.setBackground(ContextCompat.getDrawable(
                        getApplicationContext(), R.drawable.border_gray_2));
                binding.editTextPassword.setTextColor(ContextCompat.getColor(
                        getApplicationContext(), R.color.gray_2));
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.image_view_password) {
            if (binding.editTextPassword.getInputType() != InputType.TYPE_CLASS_TEXT) {
                binding.editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else
                binding.editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else if (id == R.id.button_login) {
            attempt(true);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) return false;
        lastClickTime = SystemClock.elapsedRealtime();
        final int id = v.getId();
        if (id == R.id.button_login) {
//            attempt(false);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }

    private void attempt(boolean isLogin) {
        boolean cancel = false;
        if (binding.editTextUsername.getText().length() < 1) {
            binding.editTextUsername.setError(getString(R.string.error_empty));
            binding.editTextUsername.requestFocus();
            cancel = true;
        }
        if (!cancel && binding.editTextPassword.getText().length() < 1) {
            binding.editTextPassword.setError(getString(R.string.error_empty));
            binding.editTextPassword.requestFocus();
            cancel = true;
        }
        if (!cancel) {
            String username = binding.editTextUsername.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            if (isLogin && isNetworkAvailable(getApplicationContext())) {
                new AttemptLogin(username, password, binding.checkBoxSave.isChecked()).execute(this);
            } else {
                new CustomToast().warning(getString(R.string.turn_internet_on));
            }
        }
    }

    private void loadPreference() {
        if (getPreferenceManager().checkIsNotEmpty(USERNAME.getValue()) &&
                getPreferenceManager().checkIsNotEmpty(PASSWORD.getValue())) {
            binding.editTextUsername.setText(getPreferenceManager().getStringData(
                    USERNAME.getValue()));
            binding.editTextPassword.setText(Crypto.decrypt(getPreferenceManager().getStringData(
                    PASSWORD.getValue())));
        }
    }

    @Override
    protected void onDestroy() {
        binding.imageViewPerson.setImageDrawable(null);
        binding.imageViewPassword.setImageDrawable(null);
        binding.imageViewLogo.setImageDrawable(null);
        binding.imageViewUsername.setImageDrawable(null);
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