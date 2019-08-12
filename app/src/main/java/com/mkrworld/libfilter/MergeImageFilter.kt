package com.mkrworld.libfilter

import com.mkrworld.libfilter.jnicaller.Effector

internal class MergeImageFilter : MultiImageFilter {

    /**
     * Method to get the Multiplier
     * @return
     */
    protected val multiplier: Float

    constructor(pixelArray: IntArray, overlayPixelArray: IntArray, imageWidth: Int, multiplier: Float) : super(pixelArray, overlayPixelArray, imageWidth) {
        this.multiplier = multiplier
    }

    override fun applyFilter(): IntArray {
        return Effector.setMergingEffect(pixelArray, imageWidth, overlayPixelArray, multiplier)
    }
}