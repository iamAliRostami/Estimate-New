package com.leon.estimate_new.utils.gis;

//import com.esri.arcgisruntime.data.TileKey;
//import com.esri.arcgisruntime.layers.WebTiledLayer;

import com.leon.estimate_new.infrastructure.IMapLayer;

import java.util.Arrays;
import java.util.List;

public class OsmMapLayer implements IMapLayer {
    private static final List<String> SUB_DOMAINS = Arrays.asList("a", "b", "c");
//
//    @Override
//    public String getMapUrl(TileKey tileKey) {
//
//        return null;
//    }
//
//    @Override
//    public WebTiledLayer createLayer(MapType layerType) {
//        return null;
//    }
//
//    @Override
//    public WebTiledLayer createLayer() {
//        // build the web tiled layer from stamen
//        // use web tiled layer as Basemap
//        return new WebTiledLayer("http://{subDomain}.tile.openstreetmap.org/{level}/{col}/{row}.png", SUB_DOMAINS);
////        return new WebTiledLayer("http://tile.openstreetmap.org/{level}/{col}/{row}.png");
////        return new WebTiledLayer("https://tile.openstreetmap.org/10/562/353.png");
////        return new WebTiledLayer("http://37.191.92.152:7080/PBS/rest/services/GoogleMapsRoad/MapServer/");
//    }
}
