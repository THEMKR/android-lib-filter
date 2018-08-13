package com.mkrworld.libfilter.effect.coloreffect

import android.graphics.Bitmap

class ColorEffectGreen : BaseColorEffect {

    constructor(srcBitmap: Bitmap, isRecycleSrc: Boolean) : super(srcBitmap, isRecycleSrc) {

    }

    override fun getEffectMatrixArray(): ArrayList<EffectMatrix> {
        val effectMatrixArray = ArrayList<EffectMatrix>()
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 1F, 1F, 0F, 0F, 1F, 1F, 1F, 0F, 0F, 1F, 1F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 0.35F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 86F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(-0.063F, 1.71F, -0.646F, 0F, 0F, 0.2186F, 0.436F, 0.345F, 0F, 0F, 0.979F, 0.539F, -0.519F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        return effectMatrixArray
    }
}