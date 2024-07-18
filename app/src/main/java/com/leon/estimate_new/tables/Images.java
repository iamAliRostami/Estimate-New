package com.leon.estimate_new.tables;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Images", indices = @Index(value = {"imageId"}, unique = true))
public class Images {
    @PrimaryKey(autoGenerate = true)
    public int imageId;
    public String address;
    public String billId;
    public String trackingNumber;
    public String docId;
    public String peygiri;
    @Ignore
    public String docTitle;
    @Ignore
    public Bitmap bitmap;
    @Ignore
    public String uri;

    public Images(String address, String billId, String trackingNumber, String docId, String peygiri) {
        this.address = address;
        this.billId = billId;
        this.trackingNumber = trackingNumber;
        this.docId = docId;
        this.peygiri = peygiri;
    }
    public Images(String address, String billId, String trackingNumber, String docId,
                  String docTitle, Bitmap bitmap) {
        this.address = address;
        this.billId = billId;
        this.trackingNumber = trackingNumber;
        this.docId = docId;
        this.docTitle = docTitle;
        this.bitmap = bitmap;
    }

    public Images(String billId, String trackingNumber, String uri, String docTitle, int docId,
                  Bitmap bitmap) {
        this.billId = billId;
        this.trackingNumber = trackingNumber;
        this.docTitle = docTitle;
        this.docId = String.valueOf(docId);
        this.uri = uri;
        this.bitmap = bitmap;
    }

    public Images( String uri, String docTitle,Bitmap bitmap) {
        this.docTitle = docTitle;
        this.uri = uri;
        this.bitmap = bitmap;
    }

    public Images(String address, String billId, String trackingNumber, int docId,
                  String docTitle, Bitmap bitmap) {
        this.address = address;
        this.billId = billId;
        this.trackingNumber = trackingNumber;
        this.docId = String.valueOf(docId);
        this.docTitle = docTitle;
        this.bitmap = bitmap;
    }
}
