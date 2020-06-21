package com.mkrworld.libfilter

import com.mkrworld.libfilter.jnicaller.Effector

internal class DodgeImageFilter : MultiImageFilter {

    constructor(pixelArray: IntArray, overlayPixelArray: IntArray, imageWidth: Int) : super(pixelArray, overlayPixelArray, imageWidth) {
    }

    override fun applyFilter(): IntArray {
        return Effector.setDodgeEffect(pixelArray, imageWidth, overlayPixelArray)
    }
}