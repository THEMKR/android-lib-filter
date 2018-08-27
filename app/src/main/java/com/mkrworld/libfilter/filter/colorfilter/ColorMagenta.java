package com.mkrworld.libfilter.filter.colorfilter;

import com.mkrworld.libfilter.dto.FilterMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.FilterCategory;

import java.util.ArrayList;

public class ColorMagenta extends BaseColorFilter {

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
    protected ArrayList<FilterMatrix> getFilterMatrixArray() {
        ArrayList<FilterMatrix> filterMatrixArray = new ArrayList<FilterMatrix>();
        filterMatrixArray.add(new FilterMatrix(FilterCategory.COLOR, 0.3333F, new float[]{
                1F, 1F, 1F,
                1F, 1F, 1F,
                1F, 1F, 1F
        }));

        filterMatrixArray.add(new FilterMatrix(FilterCategory.COLOR, new Offset(0F, 0F, 86F), new float[]{
                1F, 0F, 0F,
                0F, 1F, 0F,
                0F, 0F, 1F
        }));

        filterMatrixArray.add(new FilterMatrix(FilterCategory.COLOR, new float[]{
                0.895F, -0.185F, 0.290F,
                0.054F, 1.029F, -0.083F,
                -0.232F, 0.255F, 0.976F
        }));

        return filterMatrixArray;
    }
}
