package com.mkrworld.libfilter

import android.util.Log
import com.mkrworld.libfilter.dto.FilterMatrix
import com.mkrworld.libfilter.dto.FilterSeparator
import com.mkrworld.libfilter.enums.FilterCategory
import com.mkrworld.libfilter.jnicaller.Effector
import java.util.*

/**
 * @author THEMKR
 */
internal class ConventionalFilter : BaseFilter {

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     * @param filterMatrixArrayList
     */
    constructor(pixelArray: IntArray, imageWidth: Int, filterMatrixArrayList: ArrayList<FilterMatrix>) : super(pixelArray, imageWidth, filterMatrixArrayList) {}

    @Throws(Exception::class)
    override fun applyFilter(): IntArray {
        var pixelArray = pixelArray
        val l = System.currentTimeMillis()
        val filterMatrixArray = filterMatrixArrayList
        if (filterMatrixArray.size == 1) {
            val filterMatrix = filterMatrixArray[0]
            if (filterMatrix.filterCategory === FilterCategory.CONVENTIONAL) {
                pixelArray = Effector.setConventionalEffect(pixelArray, imageWidth, filterMatrix.multiplier, filterMatrix.matrix)
            }
        } else {
            val filterSeparatorList = ArrayList<FilterSeparator>()
            var filterSeparatorTemp: FilterSeparator? = null
            for (filterMatrix in filterMatrixArray) {
                when (filterMatrix.filterCategory) {
                    FilterCategory.COLOR -> {
                        if (filterSeparatorTemp == null) {
                            filterSeparatorTemp = FilterSeparator(null)
                        }
                        filterSeparatorTemp.addColorFilter(filterMatrix)
                    }
                    FilterCategory.CONVENTIONAL -> {
                        if (filterSeparatorTemp != null) {
                            filterSeparatorList.add(filterSeparatorTemp)
                        }
                        filterSeparatorTemp = FilterSeparator(filterMatrix)
                    }
                }
            }
            if (filterSeparatorTemp != null) {
                filterSeparatorList.add(filterSeparatorTemp)
            }
            for (filterSeparator in filterSeparatorList) {
                val colorFilterMatrixArray = filterSeparator.colorFilterMatrixArray
                val filterMatrixFloatArray = ArrayList<Float>()
                val multiplierFloatArray = ArrayList<Float>()
                for (filterMatrix in colorFilterMatrixArray) {
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
                if (filterSeparator.isHaveConventionalFilter) {
                    val conventionalFilterMatrix = filterSeparator.conventionalFilterMatrix
                    pixelArray = Effector.setConventionalMultiColorEffect(pixelArray, imageWidth, conventionalFilterMatrix!!.multiplier, conventionalFilterMatrix.matrix, multiplier, matrixArray)
                } else {
                    pixelArray = Effector.setColorEffect(pixelArray, imageWidth, multiplier,
                            matrixArray)
                }
            }
        }
        Log.e("MKR", "TIME TAKEN : ConventionalFilter(" + this + "):      " + (System.currentTimeMillis() - l))
        return pixelArray
    }
}
