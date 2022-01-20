package com.leon.estimate_new.helpers;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.leon.estimate_new.activities.LoginActivity;
import com.leon.estimate_new.activities.MainActivity;
import com.leon.estimate_new.activities.SplashActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MainActivity.class));
    }
}