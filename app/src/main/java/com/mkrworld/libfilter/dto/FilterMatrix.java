package com.mkrworld.libfilter.dto;

import com.mkrworld.libfilter.enums.FilterCategory;

public class FilterMatrix {

    private float[] mMatrix;
    private float mMultiplier;
    private FilterCategory mFilterCategory;
    private Offset mOffSet;

    /**
     * Constructor
     *
     * @param filterCategory
     * @param matrix         3X3 mMatrix
     */
    public FilterMatrix(FilterCategory filterCategory, float[] matrix) {
        this(filterCategory, new Offset(0F), 1F, matrix);
    }

    /**
     * Constructor
     *
     * @param filterCategory
     * @param multiplier
     * @param matrix         3X3 mMatrix
     */
    public FilterMatrix(FilterCategory filterCategory, Float multiplier, float[] matrix) {
        this(filterCategory, new Offset(0F), multiplier, matrix);
    }

    /**
     * Constructor
     *
     * @param filterCategory
     * @param offSet
     * @param matrix         3X3 mMatrix
     */
    public FilterMatrix(FilterCategory filterCategory, Offset offSet, float[] matrix) {
        this(filterCategory, offSet, 1F, matrix);
    }

    /**
     * Constructor
     *
     * @param filterCategory
     * @param offSet
     * @param multiplier
     * @param matrix         3X3 mMatrix
     */
    public FilterMatrix(FilterCategory filterCategory, Offset offSet, Float multiplier, float[] matrix) {
        this.mFilterCategory = filterCategory;
        this.mMultiplier = multiplier;
        this.mOffSet = offSet;
        this.mMatrix = getMatrix(filterCategory, offSet, matrix);
    }

    /**
     * Method to create Filter mMatrix from Input mMatrix
     */
    private float[] getMatrix(FilterCategory filterCategory, Offset offSet, float[] matrix) {
        if (matrix.length == 0) {
            switch (filterCategory) {
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
        switch (filterCategory) {
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
     * Method to get the Filter Category
     *
     * @return
     */
    public FilterCategory getFilterCategory() {
        return mFilterCategory;
    }

    /**
     * Method to get the Filter Multiplier
     *
     * @return
     */
    public float getMultiplier() {
        return mMultiplier;
    }

    /**
     * Method to get the Filter Matrix.<br>
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