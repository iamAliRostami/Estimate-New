package com.leon.estimate_new.tables;

import okhttp3.MultipartBody;

public class UploadImage {
    public boolean success;
    public String error;

    public final String billId;
    public final String trackingNumber;
    public final int docId;
    public final MultipartBody.Part imageFile;

    public UploadImage(String billId, String trackingNumber, int docId, MultipartBody.Part imageFile) {
        this.billId = billId;
        this.trackingNumber = trackingNumber;
        this.docId = docId;
        this.imageFile = imageFile;
    }
}
