package com.mkrworld.libfilter.jnicaller;

public class FilterApplier {

    static {
        System.loadLibrary("filterapplier");
    }

    public static final int COLOR_EFFECT = 0;
    public static final int COLOR_CONVENTIONAL = 1;

    /**
     * Method to apply Multiple Color Effect. On a Single Image
     *
     * @param pixelArray
     * @param width
     * @param colorEffectMultiplierArray
     * @param colorEffectMatrixItemArray
     * @return
     */
    public static native int[] setColorEffect(int[] pixelArray, int width, float[] colorEffectMultiplierArray, float[] colorEffectMatrixItemArray);

}
