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
     * @param thrashHold
     * @return
     */
    public static native int[] setConventionalEffect(int[] pixelArray, int width, float multiplier, float thrashHold, float[] effectMatrix);

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
     * Method to apply OverlayEffectBuilder Effect
     *
     * @param pixelArray
     * @param width
     * @param overlayPixelArray
     * @param multiplier
     * @return
     */
    public static native int[] setOverlayEffect(int[] pixelArray, int width, int[] overlayPixelArray, float multiplier);

    /**
     * Method to apply MultiplyEffectBuilder Effect
     *
     * @param pixelArray
     * @param width
     * @param overlayPixelArray
     * @param multiplier
     * @return
     */
    public static native int[] setMultiplyEffect(int[] pixelArray, int width, int[] overlayPixelArray, float multiplier);

    /**
     * Method to apply MultiplyEffectBuilder Effect
     *
     * @param pixelArray
     * @param width
     * @param overlayPixelArray
     * @param overlayImageOpacity between 0-1
     * @return
     */
    public static native int[] setMergingEffect(int[] pixelArray, int width, int[] overlayPixelArray, float overlayImageOpacity);

    /**
     * Method to apply MultiplyEffectBuilder Effect
     *
     * @param pixelArray
     * @param width
     * @param overlayPixelArray
     * @return
     */
    public static native int[] setDodgeEffect(int[] pixelArray, int width, int[] overlayPixelArray);
}
