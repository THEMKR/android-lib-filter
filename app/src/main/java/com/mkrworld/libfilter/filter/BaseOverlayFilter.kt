package com.mkrworld.libfilter.filter

/**
 * @author THEMKR
 */
abstract class BaseOverlayFilter : BaseFilter {

    /**
     * Method to get the Overlay Image Pixel Array
     * @return
     */
    val overlayPixelArray: IntArray
    /**
     * Method to get the Multiplier
     * @return
     */
    val multiplier: Float

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
