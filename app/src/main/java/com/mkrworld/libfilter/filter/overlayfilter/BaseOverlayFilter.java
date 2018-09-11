package com.mkrworld.libfilter.filter.overlayfilter;

import android.util.Log;

import com.mkrworld.libfilter.dto.FilterMatrix;
import com.mkrworld.libfilter.enums.FilterCategory;
import com.mkrworld.libfilter.filter.BaseFilter;
import com.mkrworld.libfilter.jnicaller.Effector;

import java.util.ArrayList;

public abstract class BaseOverlayFilter extends BaseFilter {

    private int[] mOverlayPixelArray;
    private float mMultiplier;

    /**
     * Constructor
     *
     * @param pixelArray        Array of Pixel
     * @param imageWidth
     * @param overlayPixelArray
     * @param multiplier
     */
    public BaseOverlayFilter(int[] pixelArray, int imageWidth, int[] overlayPixelArray, float multiplier) {
        super(pixelArray, imageWidth);
        mOverlayPixelArray = overlayPixelArray;
        mMultiplier = multiplier;
    }

    /**
     * Method to get the Multiplier
     * @return
     */
    public float getMultiplier() {
        return mMultiplier;
    }

    /**
     * Method to get the Overlay Image Pixel Array
     * @return
     */
    public int[] getOverlayPixelArray() {
        return mOverlayPixelArray;
    }
}
