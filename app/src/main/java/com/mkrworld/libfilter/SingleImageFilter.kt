package com.mkrworld.libfilter

import com.mkrworld.libfilter.jnicaller.Effector
import java.util.*

internal class SingleImageFilter : OnBaseFilter {
    protected val pixelArray: IntArray
    protected val imageWidth: Int
    protected val filterMatrixArrayList: ArrayList<FilterMatrix>

    /**
     * Constructor
     *
     * @param pixelArray            Array of Pixel
     * @param imageWidth
     * @param filterMatrixArrayList
     */
    constructor(pixelArray: IntArray, imageWidth: Int, filterMatrixArrayList: ArrayList<FilterMatrix>) {
        this.pixelArray = pixelArray
        this.imageWidth = imageWidth
        this.filterMatrixArrayList = filterMatrixArrayList
    }

    override fun applyFilter(): IntArray {
        var pixelArray = pixelArray
        val l = System.currentTimeMillis()
        var tempColorFilterMatrixArrayList: ArrayList<FilterMatrix>? = null
        for (filterMatrix in filterMatrixArrayList) {
            // CHECK WEATHER THE THIS FILTER IS COLOR FILTER OR NOT
            if (filterMatrix.filterCategory.equals(FilterCategory.COLOR)) {
                if (tempColorFilterMatrixArrayList == null) {
                    tempColorFilterMatrixArrayList = ArrayList()
                }
                tempColorFilterMatrixArrayList.add(filterMatrix)
                continue
            }
            // APPLY CONVENTIONAL FILTER AND HAVE A COLOR FILTER ARRAY LIST
            if (tempColorFilterMatrixArrayList != null) {
                pixelArray = applyMultiColorFilter(pixelArray, tempColorFilterMatrixArrayList)
                tempColorFilterMatrixArrayList = null
            }
            pixelArray = Effector.setConventionalEffect(pixelArray, imageWidth, filterMatrix.multiplier, filterMatrix.offSet.all, filterMatrix.matrix)
        }

        // IF LAST ELEMENT IS ALSO HAVE A COLOR FILTER
        if (tempColorFilterMatrixArrayList != null) {
            pixelArray = applyMultiColorFilter(pixelArray, tempColorFilterMatrixArrayList)
            tempColorFilterMatrixArrayList = null
        }
        return pixelArray
    }

    /**
     * Method to apply the multi color effect at same time
     * @param pixelArray
     * @param filterMatrixArrayList
     */
    private fun applyMultiColorFilter(pixelArray: IntArray, filterMatrixArrayList: ArrayList<FilterMatrix>): IntArray {
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
        return Effector.setColorEffect(pixelArray, imageWidth, multiplier, matrixArray)
    }
}