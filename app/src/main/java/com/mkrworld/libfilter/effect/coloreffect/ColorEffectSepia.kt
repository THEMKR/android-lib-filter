package com.mkrworld.libfilter.effect.coloreffect

import android.graphics.Bitmap

class ColorEffectSepia : BaseColorEffect {

    constructor(srcBitmap: Bitmap, isRecycleSrc: Boolean) : super(srcBitmap, isRecycleSrc) {

    }

    override fun getEffectMatrixArray(): ArrayList<EffectMatrix> {
        val effectMatrixArray = ArrayList<EffectMatrix>()
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 86F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(-0.58F, 1.36F, 0.22F, 0F, 0F, 0.43F, 0.44F, 0.11F, 0F, 0F, 0.35F, 1.49F, -0.84F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        return effectMatrixArray
    }
}