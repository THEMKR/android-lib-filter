package com.mkrworld.libfilter.filter

import com.mkrworld.libfilter.jnicaller.Effector

/**
 * @author THEMKR
 */
class OverlayEffect : BaseOverlayFilter {

    /**
     * Constructor
     *
     * @param pixelArray        Array of Pixel
     * @param imageWidth
     * @param overlayPixelArray
     * @param multiplier
     */
    constructor(pixelArray: IntArray, imageWidth: Int, overlayPixelArray: IntArray, multiplier: Float) : super(pixelArray, imageWidth, overlayPixelArray, multiplier) {}

    @Throws(Exception::class)
    override fun applyFilter(): IntArray {
        return Effector.setOverlayEffect(pixelArray, imageWidth, overlayPixelArray, multiplier)
    }
}
