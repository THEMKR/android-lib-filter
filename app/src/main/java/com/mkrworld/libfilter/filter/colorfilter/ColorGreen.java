package com.mkrworld.libfilter.filter.colorfilter;

import com.mkrworld.libfilter.dto.FilterMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.FilterCategory;

import java.util.ArrayList;

public class ColorGreen extends BaseColorFilter {

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param imageWidth
     */
    public ColorGreen(int[] pixelArray, int imageWidth) {
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
                -0.063F, 1.71F, -0.646F,
                0.2186F, 0.436F, 0.345F,
                0.979F, 0.539F, -0.519F
        }));

        return filterMatrixArray;
    }
}
