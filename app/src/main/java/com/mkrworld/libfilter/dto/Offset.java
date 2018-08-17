package com.mkrworld.libfilter.dto;

public class Offset {

    private float mR;
    private float mG;
    private float mB;

    /**
     * Constructor
     *
     * @param offSet OffSet of All color
     */
    public Offset(float offSet) {
        this(offSet, offSet, offSet);
    }

    /**
     * Constructor
     *
     * @param offSetR Red-Color OffSet
     * @param offSetG Green-Color OffSet
     * @param offSetB Blue-Color OffSet
     */
    public Offset(float offSetR, float offSetG, float offSetB) {
        mR = offSetR;
        mG = offSetG;
        mB = offSetB;
    }

    /**
     * Metho dto get the Red Color Offset
     *
     * @return
     */
    public float getmR() {
        return mR;
    }

    /**
     * Metho dto get the Green Color Offset
     *
     * @return
     */
    public float getmG() {
        return mG;
    }

    /**
     * Metho dto get the Blue Color Offset
     *
     * @return
     */
    public float getmB() {
        return mB;
    }

    /**
     * Metho dto get the RGB/3 Color Offset
     *
     * @return
     */
    public float getAll() {
        return (mR + mG + mB) / 3F;
    }
}