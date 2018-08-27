package com.mkrworld.libfilter.filter.conventionalfilter;

import com.mkrworld.libfilter.dto.FilterMatrix;
import com.mkrworld.libfilter.dto.Offset;
import com.mkrworld.libfilter.enums.FilterCategory;

import java.util.ArrayList;

public class ConventionalSketch extends BaseConventionalFilter {

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
    protected ArrayList<FilterMatrix> getFilterMatrixArray() {
        ArrayList<FilterMatrix> filterMatrixArray = new ArrayList<FilterMatrix>();
        filterMatrixArray.add(new FilterMatrix(FilterCategory.CONVENTIONAL, new float[]{
                2F, 3F, -3F,
                1F, -1F, 1F,
                -2F, 1F, -2F
        }));

        filterMatrixArray.add(new FilterMatrix(FilterCategory.COLOR, 0.3333F, new float[]{
                1F, 1F, 1F,
                1F, 1F, 1F,
                1F, 1F, 1F
        }));

        filterMatrixArray.add(new FilterMatrix(FilterCategory.COLOR, new Offset(255F), new float[]{
                -1F, 0F, 0F,
                0F, -1F, 0F,
                0F, 0F, -1F
        }));
        return filterMatrixArray;
    }
}
