package com.mkrworld.libfilter.jnicaller;

public class Effector {

    static {
        System.loadLibrary("effector");
    }

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

}
