package com.mkrworld.libfilter.effect.conventionaleffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.EffectCategory;

import java.util.ArrayList;

public class ConventionalSketch extends BaseConventionalEffect {

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     */
    public ConventionalSketch(int[] pixelArray, int imageWidth) {
        super(pixelArray, imageWidth);
    }

    @Override
    protected ArrayList<EffectMatrix> getEffectMatrixArray() {
        ArrayList<EffectMatrix> effectMatrixArray = new ArrayList<EffectMatrix>();
        effectMatrixArray.add(new EffectMatrix(EffectCategory.CONVENTIONAL, new float[]{
                2F, 3F, -3F,
                1F, -1F, 1F,
                -2F, 1F, -2F
        }));

        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, 0.3333F, new float[]{
                1F, 1F, 1F,
                1F, 1F, 1F,
                1F, 1F, 1F
        }));

        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, new Offset(255F), new float[]{
                -1F, 0F, 0F,
                0F, -1F, 0F,
                0F, 0F, -1F
        }));
        return effectMatrixArray;
    }
}
