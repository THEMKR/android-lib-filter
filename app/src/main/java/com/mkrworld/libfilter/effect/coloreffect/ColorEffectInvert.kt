package com.mkrworld.libfilter.effect.coloreffect

import android.graphics.Bitmap

class ColorEffectInvert : BaseColorEffect {

    constructor(srcBitmap: Bitmap, isRecycleSrc: Boolean) : super(srcBitmap, isRecycleSrc) {

    }

    override fun getEffectMatrixArray(): ArrayList<EffectMatrix> {
        val effectMatrixArray = ArrayList<EffectMatrix>()
        effectMatrixArray.add(EffectMatrix(EffectType.INVERT))
        return effectMatrixArray
    }
}