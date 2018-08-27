package com.mkrworld.libfilter.filter.colorfilter;

import android.util.Log;

import com.mkrworld.libfilter.dto.FilterMatrix;
import com.mkrworld.libfilter.filter.BaseFilter;
import com.mkrworld.libfilter.enums.FilterCategory;
import com.mkrworld.libfilter.jnicaller.Effector;

import java.util.ArrayList;

public abstract class BaseColorFilter extends BaseFilter {

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     */
    public BaseColorFilter(int[] pixelArray, int imageWidth) {
        super(pixelArray, imageWidth);
    }

    /**
     * Method to get the List of the Color Filter Matrix array applied on the Image Pixel One by One, ColorFilter(COLOR)
     */
    protected abstract ArrayList<FilterMatrix> getFilterMatrixArray();

    @Override
    public int[] applyFilter() throws Exception {
        int[] pixelArray = getPixelArray();
        long l = System.currentTimeMillis();
        ArrayList<FilterMatrix> filterMatrixArray = getFilterMatrixArray();
        if (filterMatrixArray.size() == 1) {
            FilterMatrix filterMatrix = filterMatrixArray.get(0);
            if (filterMatrix.getFilterCategory() == FilterCategory.COLOR) {
                pixelArray = Effector.setColorEffect(pixelArray, filterMatrix.getMultiplier(), filterMatrix.getMatrix());
            }
        } else {
            ArrayList<Float> filterMatrixFloatArray = new ArrayList<>();
            ArrayList<Float> multiplierFloatArray = new ArrayList<>();
            for (FilterMatrix filterMatrix : filterMatrixArray) {
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
            pixelArray = Effector.setMultiColorEffect(pixelArray, getImageWidth(), multiplier, matrixArray);
        }
        Log.e("MKR", "TIME TAKEN : BaseColorFilter(" + this + "):      " + (System.currentTimeMillis() - l));
        return pixelArray;
    }
}
