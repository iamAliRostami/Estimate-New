package com.leon.estimate_new.utils.gis;

public class FeatureGIS {
    public String type;
    public GeometryGIS geometry;
    public final Properties properties;
    public int id;

    public FeatureGIS() {
        properties = new Properties();
    }
}
