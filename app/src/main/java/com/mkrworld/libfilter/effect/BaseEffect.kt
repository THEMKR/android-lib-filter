package com.mkrworld.libfilter.effect

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import java.io.IOException

abstract class BaseEffect {

    companion object {
        init {
            System.loadLibrary("effector")
        }
    }

    enum class EffectType {
        CONVENTIONAL, COLOR, INVERT, MULTIPLY, OVERLAY
    }

    /**
     * Method to get conventional bitmap, Bitmap Dimension must be same
     *
     * @param srcBitmap
     * @param destBitmap
     * @param multiplier
     * @param valueArray 3X3
     */
    external fun getConventionalEffectedBitmap(srcBitmap: Any, destBitmap: Any, multiplier: Float, valueArray: FloatArray)

    /**
     * Method to get coller effected bitmap, Bitmap Dimension must be same
     *
     * @param srcBitmap
     * @param destBitmap
     * @param multiplier
     * @param valueArray 5X4
     */
    external fun getColorEffectedBitmap(srcBitmap: Any, multiplier: Float, valueArray: FloatArray)

    /**
     * Method to get inverse bitmap Bitmap, Dimension must be same
     *
     * @param srcBitmap
     * @param destBitmap
     * @param multiplier
     * @param offSet
     */
    external fun getInvertColorEffectedBitmap(srcBitmap: Any, multiplier: Float, offSet: Int)

    /**
     * Method to get overlay bitmap, Bitmap Dimension must be same
     *
     * @param srcBitmap
     * @param overlayBitmap
     * @param destBitmap
     * @param multiplier
     */
    external fun getMultiplyBitmap(srcBitmap: Bitmap, overlayBitmap: Bitmap, destBitmap: Bitmap, multiplier: Float)

    /**
     * Method to get overlay bitmap, Bitmap Dimension must be same
     *
     * @param srcBitmap
     * @param overlayBitmap
     * @param destBitmap
     * @param multiplier
     */
    external fun getOverLayBitmap(srcBitmap: Bitmap, overlayBitmap: Bitmap, destBitmap: Bitmap, multiplier: Float)

    constructor() {

    }

    /**
     * Method to apply Effect, No More operation is called after calling this Method
     * @return Effected Bitmap
     */
    @Throws(Exception::class)
    abstract fun applyEffect(): Bitmap?

    /**
     * Method to create the copy of the Bitmap
     */
    protected fun copyBitmap(bitmap: Bitmap): Bitmap {
        val destBitmap = createEmptyBitmap(bitmap)
        val canvas: Canvas = Canvas(destBitmap)
        val paint = Paint()
        paint.isFilterBitmap = true
        paint.isAntiAlias = true
        canvas.drawBitmap(bitmap, 0F, 0F, paint)
        return destBitmap
    }

    /**
     * Method to create the empty copy of the Bitmap
     */
    protected fun createEmptyBitmap(bitmap: Bitmap): Bitmap {
        return Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
    }

    /**
     * Class to hold the Effect Matrix allong with the Multiplier
     */
    class EffectMatrix {
        val metrixValue: FloatArray
        val multiplier: Float
        val effectType: EffectType
        val offSet: Int

        /**
         * Constructor
         * @param effectType
         */
        constructor(effectType: EffectType) : this(floatArrayOf(), 1F, 0, effectType)

        /**
         * Constructor
         * @param metrixValue
         * @param effectType
         */
        constructor(metrixValue: FloatArray, effectType: EffectType) : this(metrixValue, 1F, 0, effectType)

        /**
         * Constructor
         * @param multiplier
         * @param effectType
         */
        constructor(multiplier: Float, effectType: EffectType) : this(floatArrayOf(), multiplier, 0, effectType)

        /**
         * Constructor
         * @param offSet
         * @param effectType
         */
        constructor(offSet: Int, effectType: EffectType) : this(floatArrayOf(), 1F, offSet, effectType)

        /**
         * Constructor
         * @param multiplier
         * @param offSet
         * @param effectType
         */
        constructor(multiplier: Float, offSet: Int, effectType: EffectType) : this(floatArrayOf(), multiplier, offSet, effectType)

        /**
         * Constructor
         * @param metrixValue
         * @param multiplier
         * @param effectType
         */
        constructor(metrixValue: FloatArray, multiplier: Float, effectType: EffectType) : this(metrixValue, multiplier, 0, effectType)

        /**
         * Constructor
         * @param metrixValue
         * @param offSet
         * @param effectType
         */
        constructor(metrixValue: FloatArray, offSet: Int, effectType: EffectType) : this(metrixValue, 1F, offSet, effectType)

        constructor(metrixValue: FloatArray, multiplier: Float, offSet: Int, effectType: EffectType) {
            this.multiplier = multiplier
            this.metrixValue = metrixValue
            this.effectType = effectType
            this.offSet = offSet
        }
    }
}