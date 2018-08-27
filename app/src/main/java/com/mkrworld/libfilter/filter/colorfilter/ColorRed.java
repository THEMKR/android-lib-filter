package com.mkrworld.libfilter.filter.colorfilter;

import com.mkrworld.libfilter.dto.FilterMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.FilterCategory;

import java.util.ArrayList;

public class ColorRed extends BaseColorFilter {

    /**
     * Constructor
     *
     * @param pixelArray  Array of Pixel
     * @param imageWidth
     */
    public ColorRed(int[] pixelArray, int imageWidth) {
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
                -0.244F, 0.271F, 0.972F,
                0.417F, 0.754F, -0.172F,
                -0.461F, 1.623F, -0.162F
        }));

        return filterMatrixArray;
    }
}
