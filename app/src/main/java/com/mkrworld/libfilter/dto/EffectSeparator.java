package com.mkrworld.libfilter.dto;

import com.mkrworld.libfilter.enums.EffectCategory;

import java.util.ArrayList;

/**
 * Class to hold the Conventional-Effect along with the List of Color-Effect to apply on image in Single Run
 */
public class EffectSeparator {
    private ArrayList<EffectMatrix> mColorEffectMatrixArray;
    private EffectMatrix mConventionalEffectMatrix;

    /**
     * Constructor called when conventional matrix is added
     *
     * @param conventionalEffectMatrix Conventional-Effect-Matrix
     */
    public EffectSeparator(EffectMatrix conventionalEffectMatrix) {
        this();
        if (conventionalEffectMatrix.getEffectCategory().equals(EffectCategory.CONVENTIONAL)) {
            mConventionalEffectMatrix = conventionalEffectMatrix;
        }
    }

    /**
     * Constructor called when only color matrix is added
     */
    public EffectSeparator() {
        mColorEffectMatrixArray = new ArrayList<>();
    }

    /**
     * Method to add Color-Effect-Matrix
     *
     * @param effectMatrix
     */
    public void addColorEffect(EffectMatrix effectMatrix) {
        mColorEffectMatrixArray.add(effectMatrix);
    }

    /**
     * Method get the List of Color-Effect-Matrix
     *
     * @return
     */
    public ArrayList<EffectMatrix> getColorEffectMatrixArray() {
        return mColorEffectMatrixArray;
    }

    /**
     * Method to get the Conventional Effect-Matrix
     *
     * @return
     */
    public EffectMatrix getConventionalEffectMatrix() {
        return mConventionalEffectMatrix;
    }

    /**
     * Check weather this set have conventional Effect or Not
     *
     * @return TRUE is have conventional effect, Else FALSE
     */
    public boolean isHaveConventionalEffect() {
        return mConventionalEffectMatrix != null;
    }
}
