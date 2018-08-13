package com.mkrworld.libfilter.effect.coloreffect

import android.graphics.Bitmap

class ColorEffectRed : BaseColorEffect {

    constructor(srcBitmap: Bitmap, isRecycleSrc: Boolean) : super(srcBitmap, isRecycleSrc) {

    }

    override fun getEffectMatrixArray(): ArrayList<EffectMatrix> {
        val effectMatrixArray = ArrayList<EffectMatrix>()
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 1F, 1F, 0F, 0F, 1F, 1F, 1F, 0F, 0F, 1F, 1F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 0.35F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 86F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(-0.244F, 0.271F, 0.972F, 0F, 0F, 0.417F, 0.754F, -0.172F, 0F, 0F, -0.461F, 1.623F, -0.162F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        return effectMatrixArray
    }
}