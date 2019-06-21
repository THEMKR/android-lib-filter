package com.mkrworld.libfilter

/**
 * @author THEMKR
 */
internal abstract class FilterBaseOverlayEffect : FilterBaseEffect {

    /**
     * Method to get the Overlay Image Pixel Array
     * @return
     */
    protected val overlayPixelArray: IntArray
    /**
     * Method to get the Multiplier
     * @return
     */
    protected val multiplier: Float

    /**
     * Constructor
     *
     * @param pixelArray        Array of Pixel
     * @param imageWidth
     * @param overlayPixelArray
     * @param multiplier
     */
    constructor(pixelArray: IntArray, imageWidth: Int, overlayPixelArray: IntArray, multiplier: Float) : super(pixelArray, imageWidth, arrayListOf()) {
        this.overlayPixelArray = overlayPixelArray
        this.multiplier = multiplier
    }
}
