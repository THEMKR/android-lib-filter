package com.mkrworld.libfilter.jnicaller;

public class Effector {

    static {
        System.loadLibrary("effector");
    }

    public static native int[] setColorEffect(int[] pixelArray, float multiplier, float[] effectMatrix);

}
