package com.leon.estimate_new.helpers;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;

import com.leon.estimate_new.di.component.ActivityComponent;
import com.leon.estimate_new.di.component.ApplicationComponent;

import java.util.regex.Pattern;

public class Constants {
    public static final int EXIT_POSITION = 6;
    public static final int GPS_CODE = 1231;
    public static final int REQUEST_NETWORK_CODE = 1232;
    public static final int REQUEST_WIFI_CODE = 1233;
    public static final int MAX_IMAGE_SIZE = 150000;
    public static final int SPLASH_DURATION = 1000;
    public static final int TOAST_TEXT_SIZE = 20;

    public static final String FONT_NAME = "fonts/font_1.ttf";
    public static final String DATABASE_NAME = "MyDatabase_15";
    public static final String PDF_FONT_NAME = "assets/fonts/pdf_font.ttf";
    public static final String PDF_FONT_NAME_FA = "assets/fonts/pdf_font_fa.ttf";

    public static final int HOME_FRAGMENT = 0;
    public static final int REQUEST_FRAGMENT = 1;
    public static final int DOWNLOAD_FRAGMENT = 2;
    public static final int DUTIES_FRAGMENT = 3;
    public static final int UPLOAD_FRAGMENT = 4;
    public static final int HELP_FRAGMENT = 5;
    public static final int OFFLINE_MAP_FRAGMENT = 6;

    public static final int PERSONAL_FRAGMENT = 7;
    public static final int SERVICES_FRAGMENT = 8;
    public static final int BASE_FRAGMENT = 9;
    public static final int TECHNICAL_INFO_FRAGMENT = 10;
    public static final int MAP_DESCRIPTION_FRAGMENT = 11;
    public static final int EDIT_MAP_FRAGMENT = 12;

    public static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    public static final long MIN_TIME_BW_UPDATES = 10000;
    public static final long FASTEST_INTERVAL = 10000;
    public static final String[] PHOTO_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    public static final String[] LOCATION_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    public static Bitmap MAP_SELECTED;
    public static String IMAGE_FILE_NAME;
    public static final String[] NECESSARY_IMAGES = {"124", "125"};

    public static Context appContext;
    public static ApplicationComponent applicationComponent;
    public static ActivityComponent activityComponent;
    public static boolean exit = false;
    public static int POSITION = 0;

    public static final Pattern MOBILE_REGEX = Pattern.compile("^((\\+98|0)9\\d{9})$");
}
