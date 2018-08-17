package com.mkrworld.libfilter.dto;

import com.mkrworld.libfilter.enums.EffectCategory;

public class EffectMatrix {

    private float[] mMatrix;
    private float mMultiplier;
    private EffectCategory mEffectCategory;
    private Offset mOffSet;

    /**
     * Constructor
     *
     * @param effectCategory
     * @param matrix         3X3 mMatrix
     */
    public EffectMatrix(EffectCategory effectCategory, float[] matrix) {
        this(effectCategory, new Offset(0F), 1F, matrix);
    }

    /**
     * Constructor
     *
     * @param effectCategory
     * @param multiplier
     * @param matrix         3X3 mMatrix
     */
    public EffectMatrix(EffectCategory effectCategory, Float multiplier, float[] matrix) {
        this(effectCategory, new Offset(0F), multiplier, matrix);
    }

    /**
     * Constructor
     *
     * @param effectCategory
     * @param offSet
     * @param matrix         3X3 mMatrix
     */
    public EffectMatrix(EffectCategory effectCategory, Offset offSet, float[] matrix) {
        this(effectCategory, offSet, 1F, matrix);
    }

    /**
     * Constructor
     *
     * @param effectCategory
     * @param offSet
     * @param multiplier
     * @param matrix         3X3 mMatrix
     */
    public EffectMatrix(EffectCategory effectCategory, Offset offSet, Float multiplier, float[] matrix) {
        this.mEffectCategory = effectCategory;
        this.mMultiplier = multiplier;
        this.mOffSet = offSet;
        this.mMatrix = getMatrix(effectCategory, offSet, matrix);
    }

    /**
     * Method to create Effect mMatrix from Input mMatrix
     */
    private float[] getMatrix(EffectCategory effectCategory, Offset offSet, float[] matrix) {
        if (matrix.length == 0) {
            switch (effectCategory) {
                case COLOR:
                    matrix = new float[]{
                            1F, 0F, 0F,
                            0F, 1F, 0F,
                            0F, 0F, 1F};
                    break;
                case CONVENTIONAL:
                    matrix = new float[]{
                            0F, 0F, 0F,
                            0F, 1F, 0F,
                            0F, 0F, 10F};
                    break;
                default:
                    matrix = new float[]{0F};
            }
        }
        switch (effectCategory) {
            case COLOR:
                return new float[]{
                        matrix[0], matrix[1], matrix[2], 0F, offSet.getmR(),
                        matrix[3], matrix[4], matrix[5], 0F, offSet.getmG(),
                        matrix[6], matrix[7], matrix[8], 0F, offSet.getmB(),
                        0F, 0F, 0F, 1F, 0F};
            case CONVENTIONAL:
                return new float[]{
                        matrix[0], matrix[1], matrix[2],
                        matrix[3], matrix[4], matrix[5],
                        matrix[6], matrix[7], matrix[8]};
            default:
                return new float[]{0F};
        }
    }

    /**
     * Method to get the Effect Category
     *
     * @return
     */
    public EffectCategory getEffectCategory() {
        return mEffectCategory;
    }

    /**
     * Method to get the Effect Multiplier
     *
     * @return
     */
    public float getMultiplier() {
        return mMultiplier;
    }

    /**
     * Method to get the Effect Matrix.<br>
     * <UL>
     * <li>4X5 for Color</li>
     * <li>3X3 for Conventional</li>
     * <li>1X1 for Rest Other</li>
     * </UL>
     *
     * @return
     */
    public float[] getMatrix() {
        return mMatrix;
    }

    /**
     * Method to get the OffSet
     *
     * @return
     */
    public Offset getOffSet() {
        return mOffSet;
    }
}