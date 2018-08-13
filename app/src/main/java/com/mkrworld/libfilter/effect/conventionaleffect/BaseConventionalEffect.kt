package com.mkrworld.libfilter.effect.conventionaleffect

import android.graphics.Bitmap
import com.mkrworld.libfilter.effect.BaseEffect

abstract class BaseConventionalEffect : BaseEffect {
    private val srcBitmap: Bitmap
    private val isRecycleSrc: Boolean

    /**
     * Constructor
     * @param srcBitmap Bitmap on which filter is applied
     * @param isRecycleSrc If TRUE then recycle the SrcBitmap else No Effect
     */
    constructor(srcBitmap: Bitmap, isRecycleSrc: Boolean) : super() {
        this.srcBitmap = srcBitmap
        this.isRecycleSrc = isRecycleSrc
    }

    /**
     * Method to get the List of the Effect Matrix array applied on the Bitmap One by One, EffectType(COLOR/CONVENTIONAL)
     */
    abstract fun getEffectMatrixArray(): ArrayList<EffectMatrix>

    @Throws(Exception::class)
    override fun applyEffect(): Bitmap? {
        if (srcBitmap.isRecycled) {
            return null
        }
        var destBitmap: Bitmap = copyBitmap(srcBitmap)
        // APPLY EFFECT
        var effectMatrixArray = getEffectMatrixArray()
        for (effect in effectMatrixArray) {
            when (effect.effectType) {
                EffectType.COLOR -> {
                    getColorEffectedBitmap(destBitmap, effect.multiplier, effect.metrixValue)
                }
                EffectType.CONVENTIONAL -> {
                    val newDest = createEmptyBitmap(destBitmap)
                    getConventionalEffectedBitmap(destBitmap, newDest, effect.multiplier, effect.metrixValue)
                    if (!srcBitmap.equals(destBitmap) && !newDest.equals(destBitmap)) {
                        destBitmap.recycle()
                    }
                    destBitmap = newDest
                }
                EffectType.INVERT -> {
                    getInvertColorEffectedBitmap(destBitmap, effect.multiplier, effect.offSet)
                }
            }
        }
        if (isRecycleSrc && !srcBitmap.equals(destBitmap)) {
            srcBitmap.recycle()
        }
        return destBitmap
    }
}