package com.leon.estimate_new.helpers;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.leon.estimate_new.di.component.ActivityComponent;
import com.leon.estimate_new.di.component.ApplicationComponent;
import com.leon.estimate_new.tables.CalculationUserInput;
import com.leon.estimate_new.tables.ExaminerDuties;

import java.io.File;
import java.util.ArrayList;

public class Constants {
    public static final int EXIT_POSITION = 6;
    public static final int GPS_CODE = 1231;
    public static final int REQUEST_NETWORK_CODE = 1232;
    public static final int REQUEST_WIFI_CODE = 1233;
    public static final int IMAGE_CROP_REQUEST = 1234;
    public static final int IMAGE_BRIGHTNESS_CONTRAST_REQUEST = 1324;
    public static final int MAX_IMAGE_SIZE = 200000;
    public static final int CAMERA_REQUEST = 1888;
    public static final int GALLERY_REQUEST = 1889;
    public static final int SPLASH_DURATION = 1000;
    public static final int CARRIER_PRIVILEGE_STATUS = 901;
    public static final int CAMERA = 1446;
    public static final int REPORT = 1445;
    public static final int NAVIGATION = 1903;
    public static final int COUNTER_LOCATION = 1914;
    public static final int DESCRIPTION = 1909;
    public static final int REQUEST_LOCATION_CODE = 1236;
    public static final int GALLERY_IMAGE_LOADED = 1001;
    public static final int TOAST_TEXT_SIZE = 20;
    public static final String DATABASE_NAME = "MyDatabase_1";
    public static final String PDF_FONT_NAME = "assets/fonts/pdf_font.ttf";
    public static final String PDF_FONT_NAME_FA = "assets/fonts/pdf_font_fa.ttf";
    public static final String APP_FONT_NAME = "assets/fonts/font_1.ttf";

    public static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    public static final long MIN_TIME_BW_UPDATES = 10000;
    public static final long FASTEST_INTERVAL = 10000;

    public static Uri PHOTO_URI;
    public static Bitmap BITMAP_SELECTED;
    public static String IMAGE_FILE_NAME;

    public static Context appContext;

    public static CalculationUserInput calculationUserInput, calculationUserInputTemp;

    public static ApplicationComponent applicationComponent;
    public static ActivityComponent activityComponent;
    public static ExaminerDuties examinerDuties;



    public static final int ALL_FILES_ACCESS_REQUEST = 921;
    public static final int SETTING_REQUEST = 922;


    public static int POSITION = -1;
    public static Bitmap BITMAP_SELECTED_IMAGE;

    public static boolean FOCUS_ON_EDIT_TEXT;
    public static final ArrayList<Integer> IS_MANE = new ArrayList<>();

    public static final String[] STORAGE_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final String[] PHOTO_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    public static final String[] RECORD_AUDIO_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

    public static final String[] LOCATION_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final String PHONE_PERMISSIONS = "android.permission.READ_PRIVILEGED_PHONE_STATE";

    public static final String ACTION_USB_PERMISSION = "com.leon.counter_reading.activities.USB_PERMISSION";

    public static final int INTERFACE_SUBCLASS = 6;

    public static final int INTERFACE_PROTOCOL = 80;
    public final static int SORT_BY_NAME = 0;
    public final static int SORT_BY_DATE = 1;
    public final static int SORT_BY_SIZE = 2;
    public final static File otgViewerCachePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/OTGViewer/cache");
    public final static File otgViewerPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/OTGViewer");
    public static String zipAddress;
    public final static int ZIP_ROOT = 7896;
    public final static int OFFLINE_ATTEMPT = 5;
}
