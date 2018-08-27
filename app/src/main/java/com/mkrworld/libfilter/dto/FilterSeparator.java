package com.mkrworld.libfilter.dto;

import com.mkrworld.libfilter.enums.FilterCategory;

import java.util.ArrayList;

/**
 * Class to hold the Conventional-Filter along with the List of Color-Filter to apply on image in Single Run
 */
public class FilterSeparator {
    private ArrayList<FilterMatrix> mColorFilterMatrixArray;
    private FilterMatrix mConventionalFilterMatrix;

    /**
     * Constructor called when conventional matrix is added
     *
     * @param conventionalFilterMatrix Conventional-Filter-Matrix
     */
    public FilterSeparator(FilterMatrix conventionalFilterMatrix) {
        this();
        if (conventionalFilterMatrix.getFilterCategory().equals(FilterCategory.CONVENTIONAL)) {
            mConventionalFilterMatrix = conventionalFilterMatrix;
        }
    }

    /**
     * Constructor called when only color matrix is added
     */
    public FilterSeparator() {
        mColorFilterMatrixArray = new ArrayList<>();
    }

    /**
     * Method to add Color-Filter-Matrix
     *
     * @param filterMatrix
     */
    public void addColorFilter(FilterMatrix filterMatrix) {
        mColorFilterMatrixArray.add(filterMatrix);
    }

    /**
     * Method get the List of Color-Filter-Matrix
     *
     * @return
     */
    public ArrayList<FilterMatrix> getColorFilterMatrixArray() {
        return mColorFilterMatrixArray;
    }

    /**
     * Method to get the Conventional Filter-Matrix
     *
     * @return
     */
    public FilterMatrix getConventionalFilterMatrix() {
        return mConventionalFilterMatrix;
    }

    /**
     * Check weather this set have conventional Filter or Not
     *
     * @return TRUE is have conventional Filter, Else FALSE
     */
    public boolean isHaveConventionalFilter() {
        return mConventionalFilterMatrix != null;
    }
}
