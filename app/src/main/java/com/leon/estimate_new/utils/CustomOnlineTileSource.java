package com.leon.estimate_new.utils;

import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.MapTileIndex;

public class CustomOnlineTileSource extends OnlineTileSourceBase {
    static final String[] baseUrl = new String[]{
            "https://a.tile.opentopomap.org/",
            "https://b.tile.opentopomap.org/",
            "https://c.tile.opentopomap.org/"
    };

    public CustomOnlineTileSource() {
        super("opentopomap", 2, 22, 256, "png", baseUrl, "هیواپرداز اطلس");
    }

    @Override
    public String getTileURLString(long pMapTileIndex) {
        return getBaseUrl() + MapTileIndex.getZoom(pMapTileIndex)
                + "/" + MapTileIndex.getX(pMapTileIndex)
                + "/" + MapTileIndex.getY(pMapTileIndex)
                + "." + mImageFilenameEnding;
    }
}
