package com.mkrworld.libfilter.effect.coloreffect

import android.graphics.Bitmap

class ColorEffectPink : BaseColorEffect {

    constructor(srcBitmap: Bitmap, isRecycleSrc: Boolean) : super(srcBitmap, isRecycleSrc) {

    }

    override fun getEffectMatrixArray(): ArrayList<EffectMatrix> {
        val effectMatrixArray = ArrayList<EffectMatrix>()
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 1F, 1F, 0F, 0F, 1F, 1F, 1F, 0F, 0F, 1F, 1F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 0.35F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 86F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        effectMatrixArray.add(EffectMatrix(floatArrayOf(0.083F, -0.07F, 0.9876F, 0F, 0F, 0.332F, 0.8846F, -0.216F, 0F, 0F, -0.591F, 1.3519F, 0.24F, 0F, 0F, 0F, 0F, 0F, 1F, 0F), 1F, EffectType.COLOR))
        return effectMatrixArray
    }
}