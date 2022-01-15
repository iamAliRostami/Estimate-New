package com.leon.estimate_new.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.leon.estimate_new.databinding.ActivityDocumentBinding;

public class DocumentActivity extends AppCompatActivity {
    private ActivityDocumentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

    }
}