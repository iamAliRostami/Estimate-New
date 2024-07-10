package com.leon.estimate_new.tables;

public class AddDocument {
    public final String trackNumber;
    public final String firstName;
    public final String sureName;
    public final String address;
    public final String zoneId;

    public String data;
    public String error;
    public boolean success;

    public AddDocument(String trackNumber, String firstName, String sureName, String address, String zoneId) {
        this.trackNumber = trackNumber;
        this.firstName = firstName;
        this.sureName = sureName;
        this.address = address;
        this.zoneId = zoneId;
    }
}
