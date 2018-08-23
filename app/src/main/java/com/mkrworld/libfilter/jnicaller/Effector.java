package com.mkrworld.libfilter.jnicaller;

public class Effector {

    static {
        System.loadLibrary("effector");
    }

    public static final int COLOR_EFFECT = 0;
    public static final int COLOR_CONVENTIONAL = 0;

    /**
     * Method to apply Color Effect
     *
     * @param pixelArray
     * @param multiplier
     * @param effectMatrix
     * @return
     */
    public static native int[] setColorEffect(int[] pixelArray, float multiplier, float[] effectMatrix);

    /**
     * Method to apply Conventional Effect
     *
     * @param pixelArray
     * @param width
     * @param multiplier
     * @param effectMatrix
     * @return
     */
    public static native int[] setConventionalEffect(int[] pixelArray, int width, float multiplier, float[] effectMatrix);

    /**
     * Method to apply Multiple Color Effect. On a Single Image
     *
     * @param pixelArray
     * @param width
     * @param colorEffectMultiplierArray
     * @param colorEffectMatrixItemArray
     * @return
     */
    public static native int[] setMultiColorEffect(int[] pixelArray, int width, float[] colorEffectMultiplierArray, float[] colorEffectMatrixItemArray);

    /**
     * Method to apply Conventional Effect with further Multiple Color Effect.<br>
     * First Apply Conventional Effect, Then Apply Multiple Color Effect Simultaneously
     *
     * @param pixelArray
     * @param width
     * @param conventionalMultiplier
     * @param conventionalEffectMatrixElementArray
     * @param colorEffectMultiplierArray
     * @param colorEffectMatrixItemArray
     * @return
     */
    public static native int[] setConventionalMultiColorEffect(int[] pixelArray, int width, float conventionalMultiplier, float[] conventionalEffectMatrixElementArray, float[] colorEffectMultiplierArray, float[] colorEffectMatrixItemArray);
}
