package com.mkrworld.libfilter.effect;

import android.graphics.Bitmap;

import com.mkrworld.libfilter.enums.PixelFormat;

import java.util.ArrayList;

import kotlin.jvm.Throws;

public abstract class BaseEffect {

    private PixelFormat mPixelFormat;

    /**
     * Constructor
     *
     * @param pixelFormat
     */
    public BaseEffect(PixelFormat pixelFormat) {
        mPixelFormat = pixelFormat;
    }

    /**
     * Method to get the Pixel Format
     *
     * @return
     */
    public PixelFormat getPixelFormat() {
        return mPixelFormat;
    }

    /**
     * Method to apply Effect and Get final Value
     *
     * @return Effected PixelArray
     */
    public abstract int[] applyEffect() throws Exception;
}
