package com.mkrworld.libfilter

import android.util.Log
import com.mkrworld.libfilter.jnicaller.Effector
import java.util.*

/**
 * @author THEMKR
 */
internal class FilterColorEffect : FilterBaseEffect {

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     * @param filterMatrixArrayList
     */
    constructor(pixelArray: IntArray, imageWidth: Int, filterMatrixArrayList: ArrayList<FilterMatrix>) : super(pixelArray, imageWidth, filterMatrixArrayList)

    @Throws(Exception::class)
    override fun applyFilter(): IntArray {
        var pixelArray = pixelArray
        val l = System.currentTimeMillis()
        val filterMatrixFloatArray = ArrayList<Float>()
        val multiplierFloatArray = ArrayList<Float>()
        for (filterMatrix in filterMatrixArrayList) {
            if (filterMatrix.filterCategory === FilterCategory.COLOR) {
                val matrix = filterMatrix.matrix
                for (`val` in matrix) {
                    filterMatrixFloatArray.add(`val`)
                }
                multiplierFloatArray.add(filterMatrix.multiplier)
            }
        }
        val matrixArray = FloatArray(filterMatrixFloatArray.size)
        val multiplier = FloatArray(multiplierFloatArray.size)
        for (index in filterMatrixFloatArray.indices) {
            matrixArray[index] = filterMatrixFloatArray[index]
        }
        for (index in multiplierFloatArray.indices) {
            multiplier[index] = multiplierFloatArray[index]
        }
        pixelArray = Effector.setColorEffect(pixelArray, imageWidth, multiplier, matrixArray)
        Log.e("MKR", "TIME TAKEN : FilterColorEffect(" + this + "):      " + (System.currentTimeMillis() - l))
        return pixelArray
    }
}