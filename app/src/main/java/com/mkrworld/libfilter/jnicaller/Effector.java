package com.mkrworld.libfilter.jnicaller;

/**
 * @author THEMKR
 */
public class Effector {

    static {
        System.loadLibrary("effector");
    }

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
    public static native int[] setColorEffect(int[] pixelArray, int width, float[] colorEffectMultiplierArray, float[] colorEffectMatrixItemArray);

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

    /**
     * Method to apply Overlay Effect
     *
     * @param pixelArray
     * @param width
     * @param overlayPixelArray
     * @param multiplier
     * @return
     */
    public static native int[] setOverlayEffect(int[] pixelArray, int width, int[] overlayPixelArray, float multiplier);

    /**
     * Method to apply Multiply Effect
     *
     * @param pixelArray
     * @param width
     * @param overlayPixelArray
     * @param multiplier
     * @return
     */
    public static native int[] setMultiplyEffect(int[] pixelArray, int width, int[] overlayPixelArray, float multiplier);

    /**
     * Method to apply Multiply Effect
     *
     * @param pixelArray
     * @param width
     * @param overlayPixelArray
     * @param overlayImageOpacity between 0-1
     * @return
     */
    public static native int[] setMergingEffect(int[] pixelArray, int width, int[] overlayPixelArray, float overlayImageOpacity);
}
