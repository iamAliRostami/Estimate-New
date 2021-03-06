package com.leon.estimate_new.activities;

import static com.leon.estimate_new.helpers.MyApplication.getAndroidVersion;
import static com.leon.estimate_new.helpers.MyApplication.getPreferenceManager;
import static com.leon.estimate_new.utils.PermissionManager.isNetworkAvailable;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityLoginBinding;
import com.leon.estimate_new.enums.SharedReferenceKeys;
import com.leon.estimate_new.utils.Crypto;
import com.leon.estimate_new.utils.DifferentCompanyManager;
import com.leon.estimate_new.utils.login.AttemptLogin;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private Activity activity;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        initialize();
    }

    private void initialize() {
        binding.textViewVersion.setText(getString(R.string.version).concat(" ").concat(getAndroidVersion())
                .concat(" *** ").concat(BuildConfig.VERSION_NAME));
        TextView textViewCompanyName = findViewById(R.id.text_view_company_name);
        textViewCompanyName.setText(DifferentCompanyManager.getCompanyName(DifferentCompanyManager
                .getActiveCompanyName()));
        loadPreference();
        binding.imageViewPassword.setImageResource(R.drawable.img_password);
        binding.imageViewLogo.setImageResource(R.drawable.img_login_logo);
        binding.imageViewPerson.setImageResource(R.drawable.img_profile);
        binding.imageViewUsername.setImageResource(R.drawable.img_user);
        setOnButtonLoginClickListener();
        setOnButtonLongCLickListener();
        setOnImageViewPasswordClickListener();
        setEditTextUsernameOnFocusChangeListener();
        setEditTextPasswordOnFocusChangeListener();
    }

    void setEditTextUsernameOnFocusChangeListener() {
        binding.editTextUsername.setOnFocusChangeListener((view, b) -> {
            binding.editTextUsername.setHint("");
            if (b) {
                binding.linearLayoutUsername.setBackground(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_black_1));
                binding.editTextPassword.setTextColor(
                        ContextCompat.getColor(activity, android.R.color.black));
            } else {
                binding.linearLayoutUsername.setBackground(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_gray_2));
                binding.editTextPassword.setTextColor(ContextCompat.getColor(activity, R.color.gray_2));
            }
        });
    }

    void setEditTextPasswordOnFocusChangeListener() {
        binding.editTextPassword.setOnFocusChangeListener((view, b) -> {
            binding.editTextPassword.setHint("");
            if (b) {
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
        });
    }

    void setOnImageViewPasswordClickListener() {
        binding.imageViewPassword.setOnClickListener(v ->
                binding.imageViewPassword.setOnClickListener(view -> {
                    if (binding.editTextPassword.getInputType() != InputType.TYPE_CLASS_TEXT) {
                        binding.editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    } else
                        binding.editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }));
    }


    void setOnButtonLongCLickListener() {
        binding.buttonLogin.setOnLongClickListener(v -> {
            attempt(false);
            return false;
        });
    }

    void setOnButtonLoginClickListener() {
        binding.buttonLogin.setOnClickListener(v -> attempt(true));
    }

    void attempt(boolean isLogin) {
        View view;
        boolean cancel = false;
        if (binding.editTextUsername.getText().length() < 1) {
            binding.editTextUsername.setError(getString(R.string.error_empty));
            view = binding.editTextUsername;
            view.requestFocus();
            cancel = true;
        }
        if (!cancel && binding.editTextPassword.getText().length() < 1) {
            binding.editTextPassword.setError(getString(R.string.error_empty));
            view = binding.editTextPassword;
            view.requestFocus();
            cancel = true;
        }
        if (!cancel) {
            username = binding.editTextUsername.getText().toString();
            password = binding.editTextPassword.getText().toString();
            if (isLogin && isNetworkAvailable(activity)) {
                new AttemptLogin(username, password, binding.checkBoxSave.isChecked()).execute(activity);
            }
        }
    }

    void loadPreference() {
        if (getPreferenceManager().checkIsNotEmpty(SharedReferenceKeys.USERNAME.getValue()) &&
                getPreferenceManager().checkIsNotEmpty(SharedReferenceKeys.PASSWORD.getValue())) {
            binding.editTextUsername.setText(getPreferenceManager().getStringData(
                    SharedReferenceKeys.USERNAME.getValue()));
            binding.editTextPassword.setText(Crypto.decrypt(getPreferenceManager().getStringData(
                    SharedReferenceKeys.PASSWORD.getValue())));
        }
    }

    @Override
    protected void onDestroy() {
        binding.imageViewPerson.setImageDrawable(null);
        binding.imageViewPassword.setImageDrawable(null);
        binding.imageViewLogo.setImageDrawable(null);
        binding.imageViewUsername.setImageDrawable(null);
        binding = null;
        Debug.getNativeHeapAllocatedSize();
        System.runFinalization();
        Runtime.getRuntime().totalMemory();
        Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().maxMemory();
        Runtime.getRuntime().gc();
        System.gc();
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