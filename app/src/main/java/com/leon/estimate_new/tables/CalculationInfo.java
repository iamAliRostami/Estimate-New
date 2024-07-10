package com.leon.estimate_new.tables;

import java.util.List;

public record CalculationInfo(String trackingId, String trackNumber, String requestType,
                              String parNumber, String billId, String radif, String neighbourBillId,
                              String zoneId, String callerId, String notificationMobile,
                              String zoneTitle, String isNewEnsheab, String phoneNumber,
                              String mobile, String firstName, String sureName, String hasFazelab,
                              String fazelabInstallDate, String isFinished, String eshterak,
                              String arse, String aianKol, String aianMaskooni,
                              String aianNonMaskooni, String sifoon100, String sifoon125,
                              String sifoon150, String sifoon200, String zarfiatQarardadi,
                              String arzeshMelk, String tedadMaskooni, String tedadTejari,
                              String tedadSaier, String tedadTaxfif, String nationalId,
                              String identityCode, String fatherName, String postalCode,
                              String address, String description, boolean adamTaxfifAb,
                              boolean adamTaxfifFazelab, boolean isEnsheabQeirDaem,
                              boolean hasRadif, List<KarbariDictionary> karbariDictionary,
                              List<NoeVagozariDictionary> noeVagozariDictionary,
                              List<QotrEnsheabDictionary> qotrEnsheabDictionary,
                              List<TaxfifDictionary> taxfifDictionary,
                              List<ServiceDictionary> serviceDictionary) {
}
