package com.mkrworld.libfilter

internal abstract class MultiImageFilter : OnBaseFilter {
    protected val pixelArray: IntArray
    protected val imageWidth: Int
    protected val overlayPixelArray: IntArray

    /**
     * Constructor
     *
     * @param pixelArray
     * @param overlayPixelArray
     * @param imageWidth
     */
    constructor(pixelArray: IntArray, overlayPixelArray: IntArray, imageWidth: Int) {
        this.pixelArray = pixelArray
        this.imageWidth = imageWidth
        this.overlayPixelArray = overlayPixelArray
    }
}