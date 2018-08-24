package com.mkrworld.libfilter.effect.coloreffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.EffectCategory;
import com.mkrworld.libfilter.enums.PixelFormat;

import java.util.ArrayList;

public class ColorBlue extends BaseColorEffect {

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param pixelFormat
     * @param imageWidth
     */
    public ColorBlue(int[] pixelArray, PixelFormat pixelFormat, int imageWidth) {
        super(pixelArray, pixelFormat, imageWidth);
    }

    @Override
    protected ArrayList<EffectMatrix> getEffectMatrixArray() {
        ArrayList<EffectMatrix> effectMatrixArray = new ArrayList<EffectMatrix>();
        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, 0.3333F, new float[]{
                1F, 1F, 1F,
                1F, 1F, 1F,
                1F, 1F, 1F
        }));

        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, new Offset(0F, 0F, 80F), new float[]{
                1F, 0F, 0F,
                0F, 1F, 0F,
                0F, 0F, 1F
        }));

        return effectMatrixArray;
    }
}
