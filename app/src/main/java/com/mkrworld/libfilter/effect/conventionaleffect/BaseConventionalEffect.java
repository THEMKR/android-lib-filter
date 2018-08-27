package com.mkrworld.libfilter.effect.conventionaleffect;

import android.util.Log;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.dto.EffectSeparator;
import com.mkrworld.libfilter.effect.BaseEffect;
import com.mkrworld.libfilter.enums.EffectCategory;
import com.mkrworld.libfilter.jnicaller.Effector;

import java.util.ArrayList;

public abstract class BaseConventionalEffect extends BaseEffect {

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     */
    public BaseConventionalEffect(int[] pixelArray, int imageWidth) {
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
            if (effectMatrix.getEffectCategory() == EffectCategory.CONVENTIONAL) {
                pixelArray = Effector.setConventionalEffect(pixelArray, getImageWidth(), effectMatrix.getMultiplier(), effectMatrix.getMatrix());
            }
        } else {
            ArrayList<EffectSeparator> effectSeparatorList = new ArrayList<>();
            EffectSeparator effectSeparatorTemp = null;
            for (EffectMatrix effectMatrix : effectMatrixArray) {
                switch (effectMatrix.getEffectCategory()) {
                    case COLOR:
                        if (effectSeparatorTemp == null) {
                            effectSeparatorTemp = new EffectSeparator();
                        }
                        effectSeparatorTemp.addColorEffect(effectMatrix);
                        break;
                    case CONVENTIONAL:
                        if (effectSeparatorTemp != null) {
                            effectSeparatorList.add(effectSeparatorTemp);
                        }
                        effectSeparatorTemp = new EffectSeparator(effectMatrix);
                        break;
                }
            }
            effectSeparatorList.add(effectSeparatorTemp);
            for (EffectSeparator effectSeparator : effectSeparatorList) {
                ArrayList<EffectMatrix> colorEffectMatrixArray = effectSeparator.getColorEffectMatrixArray();
                ArrayList<Float> effectMatrixFloatArray = new ArrayList<>();
                ArrayList<Float> multiplierFloatArray = new ArrayList<>();
                for (EffectMatrix effectMatrix : colorEffectMatrixArray) {
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
                if (effectSeparator.isHaveConventionalEffect()) {
                    EffectMatrix conventionalEffectMatrix = effectSeparator.getConventionalEffectMatrix();
                    pixelArray = Effector.setConventionalMultiColorEffect(pixelArray, getImageWidth(), conventionalEffectMatrix.getMultiplier(), conventionalEffectMatrix.getMatrix(), multiplier, matrixArray);
                } else {
                    pixelArray = Effector.setMultiColorEffect(pixelArray, getImageWidth(), multiplier,
                            matrixArray);
                }
            }
        }
        Log.e("MKR", "TIME TAKEN : BaseConventionalEffect(" + this + "):      " + (System.currentTimeMillis() - l));
        return pixelArray;
    }
}
