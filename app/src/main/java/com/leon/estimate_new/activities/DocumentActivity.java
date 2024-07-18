package com.leon.estimate_new.activities;

import static com.leon.estimate_new.enums.BundleEnum.BILL_ID;
import static com.leon.estimate_new.enums.BundleEnum.NEW_ENSHEAB;
import static com.leon.estimate_new.enums.BundleEnum.TRACK_NUMBER;
import static com.leon.estimate_new.helpers.Constants.MAP_SELECTED;
import static com.leon.estimate_new.helpers.Constants.PHOTO_PERMISSIONS;
import static com.leon.estimate_new.helpers.MyApplication.setActivityComponent;
import static com.leon.estimate_new.utils.CustomFile.loadImage;
import static com.leon.estimate_new.utils.PermissionManager.checkCameraPermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.leon.estimate_new.BuildConfig;
import com.leon.estimate_new.R;
import com.leon.estimate_new.adapters.ImageViewAdapter;
import com.leon.estimate_new.adapters.SpinnerCustomAdapter;
import com.leon.estimate_new.databinding.ActivityDocumentBinding;
import com.leon.estimate_new.di.view_model.HttpClientWrapper;
import com.leon.estimate_new.fragments.documents.BrightnessContrastFragment;
import com.leon.estimate_new.fragments.documents.CropFragment;
import com.leon.estimate_new.fragments.documents.TakePhotoFragment;
import com.leon.estimate_new.tables.DataTitle;
import com.leon.estimate_new.tables.ImageData;
import com.leon.estimate_new.tables.ImageDataThumbnail;
import com.leon.estimate_new.tables.ImageDataTitle;
import com.leon.estimate_new.tables.Images;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.document.ImageTitles;
import com.leon.estimate_new.utils.document.LoginDocument;

import java.util.ArrayList;

public class DocumentActivity extends AppCompatActivity implements TakePhotoFragment.Callback,
        CropFragment.Callback, BrightnessContrastFragment.Callback {
    private ActivityDocumentBinding binding;
    private final ArrayList<ImageData> dataThumbnail = new ArrayList<>();
    private final ArrayList<String> dataThumbnailUri = new ArrayList<>();
    private final ArrayList<String> titles = new ArrayList<>();
    private final ArrayList<Images> images = new ArrayList<>();
    private final int TAKE_PHOTO_FRAGMENT = 0;
    private final int CROP_FRAGMENT = 1;
    private final int BRIGHTNESS_CONTRAST_FRAGMENT = 2;
    private int selected;
    private boolean isNew, close;
    private SpinnerCustomAdapter spinnerAdapter;
    private ImageViewAdapter imageViewAdapter;
    private ImageDataTitle imageDataTitle;
    private String trackNumber, billId;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkPermissions();
        addOnBackPressed();
    }

    private void checkPermissions() {
        if (!checkCameraPermission(getApplicationContext())) {
            askPermission();
        } else {
            initialize();
        }
    }

    private void initialize() {
        setActivityComponent(this);
        getExtra();
        new LoginDocument(this, this).execute(this);
        //TODO
        if (MAP_SELECTED != null) {
            bitmap = MAP_SELECTED.copy(Bitmap.Config.ARGB_8888, true);
        }
    }

    private void getExtra() {
        if (getIntent().getExtras() != null) {
            billId = getIntent().getExtras().getString(BILL_ID.getValue());
            trackNumber = getIntent().getExtras().getString(TRACK_NUMBER.getValue());
            isNew = getIntent().getExtras().getBoolean(NEW_ENSHEAB.getValue());
        }
    }

    public void successLogin() {
        new ImageTitles(this, this).execute(this);
    }

    private void displayView(int position) {
        final String tag = Integer.toString(position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null && fragment.isVisible()) {
            return;
        }
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter, R.animator.exit,
                R.animator.pop_enter, R.animator.pop_exit);
        fragmentTransaction.replace(binding.containerBody.getId(), getFragment(position), tag);
        if (position != 0) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
//        fragmentManager.executePendingTransactions();
    }

    private Fragment getFragment(int position) {
        close = false;
        return switch (position) {
            case BRIGHTNESS_CONTRAST_FRAGMENT -> BrightnessContrastFragment.newInstance();
            case CROP_FRAGMENT -> CropFragment.newInstance();
            default -> {
                close = true;
                yield TakePhotoFragment.newInstance();
            }
        };
    }

    private void askPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                new CustomToast().success("مجوز ها داده شده", Toast.LENGTH_LONG);
                initialize();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                new CustomToast().error("مجوز رد شد \n" +
                        deniedPermissions.toString(), Toast.LENGTH_LONG);
                finish();
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                final Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                allFileResultLauncher.launch(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
            } else if (!Settings.System.canWrite(this)) {
                final Uri uri = Uri.fromParts("package", getPackageName(), null);
                settingResultLauncher.launch(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, uri));
            } else if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                new TedPermission(this)
                        .setPermissionListener(permissionlistener)
                        .setRationaleMessage(getString(R.string.confirm_permission))
                        .setRationaleConfirmText(getString(R.string.allow_permission))
                        .setDeniedMessage(getString(R.string.if_reject_permission))
                        .setDeniedCloseButtonText(getString(R.string.close))
                        .setGotoSettingButtonText(getString(R.string.allow_permission))
                        .setPermissions(Manifest.permission.CAMERA).check();
            }
        } else
            new TedPermission(this)
                    .setPermissionListener(permissionlistener)
                    .setRationaleMessage(getString(R.string.confirm_permission))
                    .setRationaleConfirmText(getString(R.string.allow_permission))
                    .setDeniedMessage(getString(R.string.if_reject_permission))
                    .setDeniedCloseButtonText(getString(R.string.close))
                    .setGotoSettingButtonText(getString(R.string.allow_permission))
                    .setPermissions(PHOTO_PERMISSIONS).check();
    }

    private final ActivityResultLauncher<Intent> allFileResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> checkPermissions());
    private final ActivityResultLauncher<Intent> settingResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> checkPermissions());

    @Override
    public void setTakenBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        displayView(CROP_FRAGMENT);
    }

    @Override
    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ImageDataTitle body) {
        imageDataTitle = body;
        for (int i = 0; i < imageDataTitle.data.size(); i++) {
            if (imageDataTitle.data.get(i).title.equals("کروکی"))
                selected = i;
            titles.add(imageDataTitle.data.get(i).title);
        }
        displayView(TAKE_PHOTO_FRAGMENT);
    }

    @Override
    public int getSelected() {
        return selected;
    }

    @Override
    public String getKey() {
        return isNew ? trackNumber : billId;
    }

    @Override
    public String getTrackNumber() {
        return trackNumber;
    }

    @Override
    public String getBillId() {
        return billId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public DataTitle getDataTitle(int position) {
        return imageDataTitle.data.get(position);
    }

    @Override
    public ArrayList<DataTitle> getDataTitle() {
        return imageDataTitle.data;
    }

    @Override
    public ArrayList<String> getDataThumbnailUri() {
        return dataThumbnailUri;
    }

    @Override
    public ArrayList<ImageData> getDataThumbnail() {
        return dataThumbnail;
    }

    @Override
    public void setDataThumbnail(final ImageDataThumbnail thumbnails) {
        dataThumbnail.addAll(thumbnails.data);
        for (ImageData data : dataThumbnail) {
            dataThumbnailUri.add(data.img);
        }
    }

    @Override
    public void setImages() {
        images.addAll(loadImage(trackNumber, billId, imageDataTitle.data, getApplicationContext()));
    }

    @Override
    public void addImage(Images image) {
        images.add(image);
    }

    @Override
    public ArrayList<Images> getImages() {
        return images;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void setFinalBitmap(Bitmap finalBitmap) {
        //TODO
        bitmap = finalBitmap;
        displayView(TAKE_PHOTO_FRAGMENT);
    }

    @Override
    public void setTempBitmap(Bitmap tempBitmap) {
        bitmap = tempBitmap;
    }

    @Override
    public void setCroppedBitmap(Bitmap finalBitmap) {
        bitmap = finalBitmap;
        displayView(BRIGHTNESS_CONTRAST_FRAGMENT);
    }

    @Override
    public void cancelEditing() {
        bitmap = null;
        displayView(TAKE_PHOTO_FRAGMENT);
    }

    @Override
    public SpinnerCustomAdapter getSpinnerAdapter() {
        return spinnerAdapter;
    }

    @Override
    public void setSpinnerAdapter(SpinnerCustomAdapter spinnerAdapter) {
        this.spinnerAdapter = spinnerAdapter;
    }

    @Override
    public ImageViewAdapter getImageViewAdapter() {
        return imageViewAdapter;
    }

    @Override
    public void setImageViewAdapter(ImageViewAdapter imageViewAdapter) {
        this.imageViewAdapter = imageViewAdapter;
    }

    private void addOnBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(
                    OnBackInvokedDispatcher.PRIORITY_DEFAULT, this::backPressed);
        } else {
            getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    backPressed();
                }
            });
        }
    }

    private void backPressed() {
        if (HttpClientWrapper.call != null) {
            HttpClientWrapper.call.cancel();
            HttpClientWrapper.call = null;
        }
        if (close)
            finish();
    }
}