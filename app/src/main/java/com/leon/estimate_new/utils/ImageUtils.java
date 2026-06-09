package com.leon.estimate_new.utils;


import static com.leon.estimate_new.helpers.MyApplication.getContext;
import static org.opencv.android.Utils.bitmapToMat;
import static org.opencv.android.Utils.matToBitmap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;

import com.leon.estimate_new.R;
import com.leon.estimate_new.helpers.Constants;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class ImageUtils {
    public static Map<Integer, PointF> getOutlinePoints(Bitmap tempBitmap) {
        Map<Integer, PointF> outlinePoints = new HashMap<>();
        outlinePoints.put(0, new PointF(0, 0));
        outlinePoints.put(1, new PointF(tempBitmap.getWidth(), 0));
        outlinePoints.put(2, new PointF(0, tempBitmap.getHeight()));
        outlinePoints.put(3, new PointF(tempBitmap.getWidth(), tempBitmap.getHeight()));
        return outlinePoints;
    }

    public static Bitmap scaledBitmap(Bitmap bitmap, int width, int height) {
        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                new RectF(0, 0, width, height), Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    }

    public static Bitmap brightnessController(Bitmap bitmap, int value) {
        Mat src = new Mat(bitmap.getHeight(), bitmap.getWidth(), CvType.CV_8UC1);
        bitmapToMat(bitmap, src);
        src.convertTo(src, -1, 1, value);
        Bitmap result = Bitmap.createBitmap(src.cols(), src.rows(), Bitmap.Config.ARGB_8888);
        matToBitmap(src, result);
        return result;
    }

    public static Bitmap contrastController(Bitmap bitmap, float contrast, float brightness) {
        ColorMatrix colorMatrix = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });
        Bitmap ret = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return ret;
    }

    @SuppressLint("SimpleDateFormat")
    public static Bitmap createImage(Bitmap bitmap, boolean isMap, double x, double y) {
        int small = 50;
        if (isMap)
            small = 25;
        Bitmap dest = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas cs = new Canvas(dest);
        cs.drawBitmap(bitmap, 0f, 0f, null);

        Paint tPaint = new Paint();
        tPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Constants.FONT_NAME));
        tPaint.setStyle(Paint.Style.FILL);
        tPaint.setColor(Color.BLACK);
        tPaint.setTextSize(small);

        float yCoordinate = (float) bitmap.getHeight() * 5 / 144;
        float xCoordinate = (float) bitmap.getWidth() * 1 / 36;

        PersianCalendar persianCalendar = new PersianCalendar();
        String dateWaterMark = " - ".concat(persianCalendar.getPersianLongDate());
        String timeWaterMark = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
        cs.drawText(timeWaterMark.concat(dateWaterMark), xCoordinate, yCoordinate, tPaint);

        if (isMap) {
            small = 30;
            tPaint.setTextSize(small);
            if (x > 0 || y > 0) {
                small = 40;
                yCoordinate = (float) bitmap.getHeight() * 140 / 144;
                tPaint.setTextSize(small);
                cs.drawText("x: ".concat(String.valueOf(x)).concat(" , y: ").concat(String.valueOf(y)),
                        xCoordinate, yCoordinate, tPaint);
            }
        }
        return dest;
    }

    public static Uri saveBitmapToInternalCache(Context context, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        File cacheDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "cacheDir"); // Gets the internal cache directory
        String timeStamp = new SimpleDateFormat(context.getString(R.string.save_format_name), Locale.getDefault()).format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File imageFile = new File(cacheDir, imageFileName);

        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            //            bitmap.compress(format, quality, fos);
            return Uri.fromFile(imageFile); // Get Uri from the file
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap restoreBitmapFromUri(Context context, Uri imageUri) {
        if (imageUri == null) {
            return null;
        }

        try (InputStream inputStream = context.getContentResolver().openInputStream(imageUri)) {
            if (inputStream != null) {
                return BitmapFactory.decodeStream(inputStream);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
