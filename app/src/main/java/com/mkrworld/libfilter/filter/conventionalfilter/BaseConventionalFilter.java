package com.mkrworld.libfilter.filter.conventionalfilter;

import android.util.Log;

import com.mkrworld.libfilter.dto.FilterMatrix;
import com.mkrworld.libfilter.dto.FilterSeparator;
import com.mkrworld.libfilter.filter.BaseFilter;
import com.mkrworld.libfilter.enums.FilterCategory;
import com.mkrworld.libfilter.jnicaller.Effector;

import java.util.ArrayList;

public abstract class BaseConventionalFilter extends BaseFilter {

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     */
    public BaseConventionalFilter(int[] pixelArray, int imageWidth) {
        super(pixelArray, imageWidth);
    }

    /**
     * Method to get the List of the Color/Conventional Filter Matrix array applied on the Image Pixel One by One, Conventional/Color Filter(COLOR)
     */
    protected abstract ArrayList<FilterMatrix> getFilterMatrixArray();

    @Override
    public int[] applyFilter() throws Exception {
        int[] pixelArray = getPixelArray();
        long l = System.currentTimeMillis();
        ArrayList<FilterMatrix> filterMatrixArray = getFilterMatrixArray();
        if (filterMatrixArray.size() == 1) {
            FilterMatrix filterMatrix = filterMatrixArray.get(0);
            if (filterMatrix.getFilterCategory() == FilterCategory.CONVENTIONAL) {
                pixelArray = Effector.setConventionalEffect(pixelArray, getImageWidth(), filterMatrix.getMultiplier(), filterMatrix.getMatrix());
            }
        } else {
            ArrayList<FilterSeparator> filterSeparatorList = new ArrayList<>();
            FilterSeparator filterSeparatorTemp = null;
            for (FilterMatrix filterMatrix : filterMatrixArray) {
                switch (filterMatrix.getFilterCategory()) {
                    case COLOR:
                        if (filterSeparatorTemp == null) {
                            filterSeparatorTemp = new FilterSeparator();
                        }
                        filterSeparatorTemp.addColorFilter(filterMatrix);
                        break;
                    case CONVENTIONAL:
                        if (filterSeparatorTemp != null) {
                            filterSeparatorList.add(filterSeparatorTemp);
                        }
                        filterSeparatorTemp = new FilterSeparator(filterMatrix);
                        break;
                }
            }
            filterSeparatorList.add(filterSeparatorTemp);
            for (FilterSeparator filterSeparator : filterSeparatorList) {
                ArrayList<FilterMatrix> colorFilterMatrixArray = filterSeparator.getColorFilterMatrixArray();
                ArrayList<Float> filterMatrixFloatArray = new ArrayList<>();
                ArrayList<Float> multiplierFloatArray = new ArrayList<>();
                for (FilterMatrix filterMatrix : colorFilterMatrixArray) {
                    if (filterMatrix.getFilterCategory() == FilterCategory.COLOR) {
                        float[] matrix = filterMatrix.getMatrix();
                        for (float val : matrix) {
                            filterMatrixFloatArray.add(val);
                        }
                        multiplierFloatArray.add(filterMatrix.getMultiplier());
                    }
                }
                float[] matrixArray = new float[filterMatrixFloatArray.size()];
                float[] multiplier = new float[multiplierFloatArray.size()];
                for (int index = 0; index < filterMatrixFloatArray.size(); index++) {
                    matrixArray[index] = filterMatrixFloatArray.get(index);
                }
                for (int index = 0; index < multiplierFloatArray.size(); index++) {
                    multiplier[index] = multiplierFloatArray.get(index);
                }
                if (filterSeparator.isHaveConventionalFilter()) {
                    FilterMatrix conventionalFilterMatrix = filterSeparator.getConventionalFilterMatrix();
                    pixelArray = Effector.setConventionalMultiColorEffect(pixelArray, getImageWidth(), conventionalFilterMatrix.getMultiplier(), conventionalFilterMatrix.getMatrix(), multiplier, matrixArray);
                } else {
                    pixelArray = Effector.setMultiColorEffect(pixelArray, getImageWidth(), multiplier,
                            matrixArray);
                }
            }
        }
        Log.e("MKR", "TIME TAKEN : BaseConventionalFilter(" + this + "):      " + (System.currentTimeMillis() - l));
        return pixelArray;
    }
}
