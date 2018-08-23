package com.mkrworld.libfilter.effect.coloreffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.effect.conventionaleffect.BaseConventionalEffect;
import com.mkrworld.libfilter.enums.EffectCategory;
import com.mkrworld.libfilter.enums.PixelFormat;

import java.util.ArrayList;

public class ColorInvert extends BaseColorEffect {

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param pixelFormat
     * @param imageWidth
     */
    public ColorInvert(int[] pixelArray, PixelFormat pixelFormat, int imageWidth) {
        super(pixelArray, pixelFormat, imageWidth);
    }

    @Override
    protected ArrayList<EffectMatrix> getEffectMatrixArray() {
        ArrayList<EffectMatrix> effectMatrixArray = new ArrayList<EffectMatrix>();
        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, new Offset(255F), new float[]{
                -1F, 0F, 0F,
                0F, -1F, 0F,
                0F, 0F, -1F
        }));
        return effectMatrixArray;
    }
}
