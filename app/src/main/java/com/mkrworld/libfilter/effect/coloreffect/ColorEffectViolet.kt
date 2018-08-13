package com.mkrworld.libfilter.effect.coloreffect

import android.graphics.Bitmap

class ColorEffectViolet : BaseColorEffect {

    constructor(srcBitmap: Bitmap, isRecycleSrc: Boolean) : super(srcBitmap, isRecycleSrc) {

    }

    override fun getEffectMatrixArray(): ArrayList<EffectMatrix> {
        val effectMatrixArray = ArrayList<EffectMatrix>()
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 1F, 1F, 0F, 0F, 1F, 1F, 1F, 0F, 0F, 1F, 1F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 0.35F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 86F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(0.618F, -0.296F, 0.677F, 0F, 0F, 0.163F, 1.015F, -0.179F, 0F, 0F, -0.494F, 0.715F, 0.779F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        return effectMatrixArray
    }
}