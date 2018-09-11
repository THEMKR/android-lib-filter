package com.mkrworld.libfilter.filter.overlayfilter;

import com.mkrworld.libfilter.jnicaller.Effector;

public class MultiplierEffect extends BaseOverlayFilter {

    /**
     * Constructor
     *
     * @param pixelArray        Array of Pixel
     * @param imageWidth
     * @param overlayPixelArray
     * @param multiplier
     */
    public MultiplierEffect(int[] pixelArray, int imageWidth, int[] overlayPixelArray, float multiplier) {
        super(pixelArray, imageWidth, overlayPixelArray, multiplier);
    }

    @Override
    public int[] applyFilter() throws Exception {
        return Effector.setMultiplyEffect(getPixelArray(), getImageWidth(), getOverlayPixelArray(), getMultiplier());
    }
}
