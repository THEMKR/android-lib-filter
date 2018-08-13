package com.mkrworld.libfilter.effect.coloreffect

import android.graphics.Bitmap

class ColorEffectNegativeOriginal : BaseColorEffect {

    constructor(srcBitmap: Bitmap, isRecycleSrc: Boolean) : super(srcBitmap, isRecycleSrc) {

    }

    override fun getEffectMatrixArray(): ArrayList<EffectMatrix> {
        val effectMatrixArray = ArrayList<EffectMatrix>()
        effectMatrixArray.add(EffectMatrix(floatArrayOf(-1F, 0F, 0F, 0F, 255F, 0F, -1F, 0F, 0F, 255F, 0F, 0F, -1F, 0F, 255F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        return effectMatrixArray
    }
}