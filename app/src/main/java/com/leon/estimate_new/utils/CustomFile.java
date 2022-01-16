package com.leon.estimate_new.utils;

import static com.leon.estimate_new.helpers.Constants.IMAGE_FILE_NAME;
import static com.leon.estimate_new.helpers.Constants.MAX_IMAGE_SIZE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import com.leon.estimate_new.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CustomFile {
    @SuppressLint("SimpleDateFormat")
    public static MultipartBody.Part bitmapToFile(Bitmap bitmap, Context context) {
        final String timeStamp = (new SimpleDateFormat(context.getString(R.string.save_format_name))).format(new Date());
        final String fileNameToSave = "JPEG_" + new Random().nextInt() + "_" + timeStamp + ".jpg";
        final File f = new File(context.getCacheDir(), fileNameToSave);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final byte[] bitmapData = compressBitmapToByte(bitmap);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), f);
        return MultipartBody.Part.createFormData("imageFile", f.getName(), requestBody);
    }

    static byte[] compressBitmapToByte(Bitmap bitmap) {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    @SuppressLint({"SimpleDateFormat"})
    public static File createImageFile(Context context) throws IOException {
        final String timeStamp = (new SimpleDateFormat(context.getString(R.string.save_format_name))).format(new Date());
        final String imageFileName = "JPEG_" + timeStamp + "_";
        final File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        final File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        final StringBuilder stringBuilder = (new StringBuilder()).append("file:");
        IMAGE_FILE_NAME = stringBuilder.append(image.getAbsolutePath()).toString();
        return image;
    }

    public static Bitmap compressBitmap(Bitmap original) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            original.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            if (stream.toByteArray().length > MAX_IMAGE_SIZE) {
                final int width, height;
                if (original.getHeight() > original.getWidth()) {
                    height = 1200;
                    width = original.getWidth() / (original.getHeight() / height);
                } else {
                    width = 1200;
                    height = original.getHeight() / (original.getWidth() / width);
                }
                original = Bitmap.createScaledBitmap(original, width, height, false);
                stream = new ByteArrayOutputStream();
                original.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            }
            return original;
        } catch (Exception e) {
            new CustomToast().error(e.getMessage(), Toast.LENGTH_LONG);
        }
        return null;
    }
}
