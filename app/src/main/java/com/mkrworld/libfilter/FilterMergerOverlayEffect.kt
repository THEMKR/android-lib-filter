package com.mkrworld.libfilter

import com.mkrworld.libfilter.jnicaller.Effector

/**
 * @author THEMKR
 */
internal class FilterMergerOverlayEffect : FilterBaseOverlayEffect {

    /**
     * Constructor
     *
     * @param pixelArray        Array of Pixel
     * @param imageWidth
     * @param overlayPixelArray
     * @param overlayImageAlpha 1-0
     */
    constructor(pixelArray: IntArray, imageWidth: Int, overlayPixelArray: IntArray, overlayImageAlpha: Float) : super(pixelArray, imageWidth, overlayPixelArray, overlayImageAlpha)

    @Throws(Exception::class)
    override fun applyFilter(): IntArray {
        return Effector.setMergingEffect(pixelArray, imageWidth, overlayPixelArray, multiplier)
    }
}
