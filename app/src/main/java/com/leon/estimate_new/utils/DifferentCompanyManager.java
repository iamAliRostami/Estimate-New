package com.leon.estimate_new.utils;


import com.leon.estimate_new.enums.CompanyNames;

public class DifferentCompanyManager {

    public static CompanyNames getActiveCompanyName() {
        return CompanyNames.ESF;
    }

    public static String getBaseUrl(CompanyNames companyNames) {
        return switch (companyNames) {
            case ESF -> "https://37.191.92.157/";
//            case ESF -> "http://172.18.12.14:100";
            case ZONE1 -> "http://217.146.220.33:50011/";
            case ZONE2 -> "http://212.16.75.194:8080/";
            case ZONE3 -> "http://212.16.69.36:90/";
            case ZONE4 -> "http://81.12.106.167:8081/";
            case ZONE5 -> "http://80.69.252.151/";
            case ZONE6 -> "http://85.133.190.220:4121/";
            case TSW -> "http://81.90.148.25/";
            case TE -> "http://185.120.137.254";
            case TSE -> "http://5.160.85.228:9098/";
            case TOWNS_WEST -> "http://217.66.195.75/";
            case DEBUG -> "http://192.168.43.185:45458/";
            default -> throw new UnsupportedOperationException();
        };
    }

    public static String getLocalBaseUrl(CompanyNames companyNames) {
        return switch (companyNames) {
            case ESF -> "http://172.18.12.14:100";
            case ESF_MAP -> "http://172.18.12.242/osm_tiles/";
            case ZONE1 -> "http://172.21.0.16/";
            case ZONE2 -> "http://172.22.4.71/";
            case ZONE3 -> "http://172.23.0.113/";
            case ZONE4 -> "http://172.24.13.23/";
            case ZONE5 -> "http://172.25.0.72/";
            case ZONE6 -> "http://172.26.0.32/";
            case TSW -> "http://172.30.1.22/";
            case TE -> "http://172.31.0.25/";
            case TSE -> "http://172.28.5.40/";
            case TOWNS_WEST -> "http://172.28.5.41/";
            default -> throw new UnsupportedOperationException();
        };
    }

    public static String getCompanyName(CompanyNames companyName) {
        return switch (companyName) {
            case ZONE1 -> "آبقا منطقه یک";
            case ZONE2 -> "آبفا منطقه2";
            case ZONE3 -> "آبفا منطقه سه";
            case ZONE4 -> "آبفا منطقه چهار";
            case ZONE5 -> "آبفامنطقه پنج";
            case ZONE6 -> "آبقا منطقه شش";
            case TE -> "آبفا شرق";
            case TSW -> "آبفا جنوب غربی";
            case TSE -> "آبفا جنوب شرقی";
            case TOWNS_WEST -> "آبفا شهرک های غرب";
            case ESF -> "آبفا استان اصفهان";
            default -> throw new UnsupportedOperationException();
        };
    }
}
