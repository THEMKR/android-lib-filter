package com.mkrworld.libfilter.effect.coloreffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.effect.BaseEffect;
import com.mkrworld.libfilter.enums.EffectCategory;
import com.mkrworld.libfilter.enums.PixelFormat;
import com.mkrworld.libfilter.jnicaller.Effector;

import java.util.ArrayList;

public abstract class BaseColorEffect extends BaseEffect {

    private int[] mPixelArray;

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param pixelFormat
     */
    public BaseColorEffect(int[] pixelArray, PixelFormat pixelFormat) {
        super(pixelFormat);
        mPixelArray = pixelArray;
    }

    /**
     * Method to get the List of the ColorEffect Matrix array applied on the Image Pixel One by One, ColorEffect(COLOR)
     */
    protected abstract ArrayList<EffectMatrix> getEffectMatrixArray();

    @Override
    public int[] applyEffect() throws Exception {
        ArrayList<EffectMatrix> effectMatrixArray = getEffectMatrixArray();
        for (EffectMatrix effectMatrix : effectMatrixArray) {
            if (effectMatrix.getEffectCategory() == EffectCategory.COLOR) {
                mPixelArray = Effector.setColorEffect(mPixelArray, effectMatrix.getMultiplier(), effectMatrix.getMatrix());
            }
        }
        return mPixelArray;
    }
}
