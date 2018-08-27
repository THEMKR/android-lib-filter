package com.mkrworld.libfilter.effect.coloreffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.EffectCategory;

import java.util.ArrayList;

public class ColorMagenta extends BaseColorEffect {

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param imageWidth
     */
    public ColorMagenta(int[] pixelArray, int imageWidth) {
        super(pixelArray, imageWidth);
    }

    @Override
    protected ArrayList<EffectMatrix> getEffectMatrixArray() {
        ArrayList<EffectMatrix> effectMatrixArray = new ArrayList<EffectMatrix>();
        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, 0.3333F, new float[]{
                1F, 1F, 1F,
                1F, 1F, 1F,
                1F, 1F, 1F
        }));

        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, new Offset(0F, 0F, 86F), new float[]{
                1F, 0F, 0F,
                0F, 1F, 0F,
                0F, 0F, 1F
        }));

        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, new float[]{
                0.895F, -0.185F, 0.290F,
                0.054F, 1.029F, -0.083F,
                -0.232F, 0.255F, 0.976F
        }));

        return effectMatrixArray;
    }
}
