package com.mkrworld.libfilter.effect.coloreffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.EffectCategory;

import java.util.ArrayList;

public class ColorVoilet extends BaseColorEffect {

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param imageWidth
     */
    public ColorVoilet(int[] pixelArray, int imageWidth) {
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
                0.618F, -0.296F, 0.677F,
                0.163F, 1.015F, -0.179F,
                -0.494F, 0.715F, 0.779F
        }));

        return effectMatrixArray;
    }
}
