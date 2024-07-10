package com.leon.estimate_new.utils.gis;

//import com.esri.arcgisruntime.geometry.CoordinateFormatter;
//import com.esri.arcgisruntime.geometry.Point;
//import com.esri.arcgisruntime.geometry.SpatialReferences;
//import com.esri.arcgisruntime.mapping.view.Graphic;
//import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
//import com.esri.arcgisruntime.symbology.TextSymbol;

public class GisTools {
//
//    public static Graphic createGraphicTextPoint(final double latitude, final double longitude, String text) {
//
//        final Point textPoint = new Point(longitude, latitude, SpatialReferences.getWgs84());
//        final TextSymbol symbol = new TextSymbol(14, text, Color.rgb(230, 0, 0),
//                TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.TOP);
//        return new Graphic(textPoint, symbol);
//    }
//
//    public static Graphic createGraphicPicturePoint(final double latitude, final double longitude, int id) {
//        final BitmapDrawable drawable = (BitmapDrawable) ContextCompat.getDrawable(getContext(), id);
//        final PictureMarkerSymbol symbol = new PictureMarkerSymbol(drawable);
//        final Point graphicPoint = new Point(longitude, latitude, SpatialReferences.getWgs84());
//        return new Graphic(graphicPoint, symbol);
//    }
//
//    public static Graphic createGraphicPicturePoint(final Point graphicPoint, int id) {
//        final BitmapDrawable drawable = (BitmapDrawable) ContextCompat.getDrawable(getContext(), id);
//        final PictureMarkerSymbol symbol = new PictureMarkerSymbol(drawable);
//        return new Graphic(graphicPoint, symbol);
//    }
//
//    public static Point getPoint(final Point point) {
//        final String latLong = String.valueOf(CoordinateFormatter.toLatitudeLongitude(point,
//                CoordinateFormatter.LatitudeLongitudeFormat.DECIMAL_DEGREES, 10));
//        return new Point(CoordinateFormatter.fromLatitudeLongitude(latLong, null).getX(),
//                CoordinateFormatter.fromLatitudeLongitude(latLong, null).getY());
//    }
//
//    public static Point getUTMPoint(final Point point) {
//        final String utm = "39 S ".concat(String.valueOf(point.getX())).concat(" ")
//                .concat(String.valueOf(point.getY()));
//        final CoordinateConversion conversion = new CoordinateConversion();
//        double[] latLong = conversion.utm2LatLon(utm);
//        return new Point(latLong[0], latLong[1], SpatialReferences.getWgs84());
//    }
}
