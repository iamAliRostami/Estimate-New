package com.leon.estimate_new.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.leon.estimate_new.databinding.ActivityFormBinding;

public class FormActivity extends AppCompatActivity {
    private ActivityFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

    }
}