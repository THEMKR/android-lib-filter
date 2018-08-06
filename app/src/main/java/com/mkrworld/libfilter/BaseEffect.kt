package com.mkrworld.libfilter

abstract class BaseEffect {

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("effector")
        }

        var count = 10
    }

    constructor() {

    }

    /**
     * Method to get conventional bitmap, Bitmap Dimension must be same
     *
     * @param srcBitmap
     * @param destBitmap
     * @param multiplier
     * @param valueArray
     */
    external fun getConventionalEffectedBitmap(srcBitmap: Any, destBitmap: Any, multiplier: Float, valueArray: FloatArray)

    /**
     * Method to get coller effected bitmap, Bitmap Dimension must be same
     *
     * @param srcBitmap
     * @param destBitmap
     * @param multiplier
     * @param valueArray
     */
    external fun getColorEffectedBitmap(srcBitmap: Any, destBitmap: Any, multiplier: Float, valueArray: FloatArray)

    /**
     * Method to get inverse bitmap Bitmap, Dimension must be same
     *
     * @param srcBitmap
     * @param destBitmap
     * @param multiplier
     * @param offSet
     */
    external fun getInvertColorEffectedBitmap(srcBitmap: Any, destBitmap: Any, multiplier: Float, offSet: Int)

    /**
     * Method to get overlay bitmap, Bitmap Dimension must be same
     *
     * @param srcBitmap
     * @param overlayBitmap
     * @param destBitmap
     */
    external fun getOverLayBitmap(srcBitmap: Any, overlayBitmap: Any, destBitmap: Any)
}