package com.mkrworld.libfilter.effect.coloreffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.EffectCategory;
import com.mkrworld.libfilter.enums.PixelFormat;

import java.util.ArrayList;

public class Red extends BaseColorEffect {

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param pixelFormat
     * @param imageWidth
     */
    public Red(int[] pixelArray, PixelFormat pixelFormat, int imageWidth) {
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


        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, new Offset(0F, 0F, 86F), new float[]{
                1F, 0F, 0F,
                0F, 1F, 0F,
                0F, 0F, 1F
        }));


        effectMatrixArray.add(new EffectMatrix(EffectCategory.COLOR, new float[]{
                -0.244F, 0.271F, 0.972F,
                0.417F, 0.754F, -0.172F,
                -0.461F, 1.623F, -0.162F
        }));

        return effectMatrixArray;
    }
}
