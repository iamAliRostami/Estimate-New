package com.leon.estimate_new.activities;

import static com.leon.estimate_new.helpers.Constants.BITMAP_SELECTED;
import static com.leon.estimate_new.helpers.Constants.PERSONAL_FRAGMENT;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.leon.estimate_new.R;
import com.leon.estimate_new.databinding.ActivityDocumentBinding;
import com.leon.estimate_new.fragments.documents.BrightnessContrastFragment;
import com.leon.estimate_new.fragments.documents.CropFragment;
import com.leon.estimate_new.fragments.documents.TakePhotoFragment;
import com.leon.estimate_new.utils.CustomToast;
import com.leon.estimate_new.utils.document.LoginDocument;

import java.util.ArrayList;

public class DocumentActivity extends AppCompatActivity implements TakePhotoFragment.Callback,
        CropFragment.Callback, BrightnessContrastFragment.Callback {
    private ActivityDocumentBinding binding;
    private Bitmap bitmap;
    private final int TAKE_PHOTO_FRAGMENT = 0;
    private final int CROP_FRAGMENT = 1;
    private final int BRIGHTNESS_CONTRAST_FRAGMENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            askPermission();
        } else {
            initialize();
        }
    }

    private void initialize() {
        new LoginDocument(this, this).execute(this);
        if (BITMAP_SELECTED != null) {
            bitmap = BITMAP_SELECTED;
            BITMAP_SELECTED = null;
        }
    }

    public void successLogin() {
        displayView(TAKE_PHOTO_FRAGMENT);
    }

    private void displayView(int position) {
        final String tag = Integer.toString(position);
        if (getFragmentManager().findFragmentByTag(tag) != null && getFragmentManager().findFragmentByTag(tag).isVisible()) {
            return;
        }
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter, R.animator.exit,
                R.animator.pop_enter, R.animator.pop_exit);
        fragmentTransaction.replace(binding.containerBody.getId(), getFragment(position), tag);
        if (position != PERSONAL_FRAGMENT) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }

    private Fragment getFragment(int position) {
        switch (position) {
            case BRIGHTNESS_CONTRAST_FRAGMENT:
                return BrightnessContrastFragment.newInstance();
            case CROP_FRAGMENT:
                return CropFragment.newInstance();
            case TAKE_PHOTO_FRAGMENT:
            default:
                return TakePhotoFragment.newInstance();
        }
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

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("جهت استفاده از برنامه مجوزهای پیشنهادی را قبول فرمایید")
                .setDeniedMessage("در صورت رد این مجوز قادر به استفاده از این دستگاه نخواهید بود" + "\n" +
                        "لطفا با فشار دادن دکمه اعطای دسترسی و سپس در بخش دسترسی ها با این مجوز ها موافقت نمایید")
                .setPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE).check();
    }

    @Override
    public void setTakenBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        displayView(CROP_FRAGMENT);
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
}