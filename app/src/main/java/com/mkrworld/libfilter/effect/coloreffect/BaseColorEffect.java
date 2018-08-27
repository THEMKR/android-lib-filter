package com.mkrworld.libfilter.effect.coloreffect;

import android.util.Log;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.effect.BaseEffect;
import com.mkrworld.libfilter.enums.EffectCategory;
import com.mkrworld.libfilter.jnicaller.Effector;

import java.util.ArrayList;

public abstract class BaseColorEffect extends BaseEffect {

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     */
    public BaseColorEffect(int[] pixelArray, int imageWidth) {
        super(pixelArray, imageWidth);
    }

    /**
     * Method to get the List of the ColorEffect Matrix array applied on the Image Pixel One by One, ColorEffect(COLOR)
     */
    protected abstract ArrayList<EffectMatrix> getEffectMatrixArray();

    @Override
    public int[] applyEffect() throws Exception {
        int[] pixelArray = getPixelArray();
        long l = System.currentTimeMillis();
        ArrayList<EffectMatrix> effectMatrixArray = getEffectMatrixArray();
        if (effectMatrixArray.size() == 1) {
            EffectMatrix effectMatrix = effectMatrixArray.get(0);
            if (effectMatrix.getEffectCategory() == EffectCategory.COLOR) {
                pixelArray = Effector.setColorEffect(pixelArray, effectMatrix.getMultiplier(), effectMatrix.getMatrix());
            }
        } else {
            ArrayList<Float> effectMatrixFloatArray = new ArrayList<>();
            ArrayList<Float> multiplierFloatArray = new ArrayList<>();
            for (EffectMatrix effectMatrix : effectMatrixArray) {
                if (effectMatrix.getEffectCategory() == EffectCategory.COLOR) {
                    float[] matrix = effectMatrix.getMatrix();
                    for (float val : matrix) {
                        effectMatrixFloatArray.add(val);
                    }
                    multiplierFloatArray.add(effectMatrix.getMultiplier());
                }
            }
            float[] matrixArray = new float[effectMatrixFloatArray.size()];
            float[] multiplier = new float[multiplierFloatArray.size()];
            for (int index = 0; index < effectMatrixFloatArray.size(); index++) {
                matrixArray[index] = effectMatrixFloatArray.get(index);
            }
            for (int index = 0; index < multiplierFloatArray.size(); index++) {
                multiplier[index] = multiplierFloatArray.get(index);
            }
            pixelArray = Effector.setMultiColorEffect(pixelArray, getImageWidth(), multiplier, matrixArray);
        }
        Log.e("MKR", "TIME TAKEN : BaseColorEffect(" + this + "):      " + (System.currentTimeMillis() - l));
        return pixelArray;
    }
}
