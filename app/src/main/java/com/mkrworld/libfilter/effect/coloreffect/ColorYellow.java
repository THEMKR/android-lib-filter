package com.mkrworld.libfilter.effect.coloreffect;

import com.mkrworld.libfilter.dto.EffectMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.EffectCategory;

import java.util.ArrayList;

public class ColorYellow extends BaseColorEffect {

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     */
    public ColorYellow(int[] pixelArray, int imageWidth) {
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
                -0.574F, 1.43F, 0.143F,
                0.426F, 0.43F, 0.144F,
                0.426F, 1.429F, -0.855F
        }));

        return effectMatrixArray;
    }
}
