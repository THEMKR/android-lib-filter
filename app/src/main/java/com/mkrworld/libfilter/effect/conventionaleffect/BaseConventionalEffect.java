package com.mkrworld.libfilter.effect.conventionaleffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.effect.BaseEffect;
import com.mkrworld.libfilter.enums.EffectCategory;
import com.mkrworld.libfilter.enums.PixelFormat;
import com.mkrworld.libfilter.jnicaller.Effector;

import java.util.ArrayList;

public abstract class BaseConventionalEffect extends BaseEffect {

    private int[] mPixelArray;
    private int mImageWidth;

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param pixelFormat
     * @param imageWidth
     */
    public BaseConventionalEffect(int[] pixelArray, PixelFormat pixelFormat, int imageWidth) {
        super(pixelFormat);
        mPixelArray = pixelArray;
        mImageWidth = imageWidth;
    }

    /**
     * Method to get the List of the ColorEffect Matrix array applied on the Image Pixel One by One, ColorEffect(COLOR)
     */
    protected abstract ArrayList<EffectMatrix> getEffectMatrixArray();

    @Override
    public int[] applyEffect() throws Exception {
        ArrayList<EffectMatrix> effectMatrixArray = getEffectMatrixArray();
        for (EffectMatrix effectMatrix : effectMatrixArray) {
            switch (effectMatrix.getEffectCategory()) {
                case COLOR:
                    mPixelArray = Effector.setColorEffect(mPixelArray, effectMatrix.getMultiplier(), effectMatrix.getMatrix());
                    break;
                case CONVENTIONAL:
                    mPixelArray = Effector.setConventionalEffect(mPixelArray, mImageWidth, effectMatrix.getMultiplier(), effectMatrix.getMatrix());
                    break;
            }
        }
        return mPixelArray;
    }
}
