package com.mkrworld.libfilter

import java.util.*

/**
 * @author THEMKR
 * Class to hold the Conventional-Filter along with the List of Color-Filter to apply on image in Single Run
 */
internal class FilterSeparator {

    /**
     * List of Color-Filter-Matrix
     */
    val colorFilterMatrixArray: ArrayList<FilterMatrix> = ArrayList()

    /**
     * Conventional Filter-Matrix
     */
    val conventionalFilterMatrix: FilterMatrix?

    /**
     * TRUE is have conventional Filter, Else FALSE
     */
    val isHaveConventionalFilter: Boolean
        get() {
            return conventionalFilterMatrix != null
        }

    /**
     * Constructor called when conventional matrix is added
     *
     * @param conventionalFilterMatrix Conventional-Filter-Matrix
     */
    constructor(conventionalFilterMatrix: FilterMatrix?) {
        this.conventionalFilterMatrix = if (conventionalFilterMatrix != null && conventionalFilterMatrix.filterCategory == FilterCategory.CONVENTIONAL) {
            conventionalFilterMatrix
        } else {
            null
        }
    }

    /**
     * Method to add Color-Filter-Matrix
     *
     * @param filterMatrix
     */
    fun addColorFilter(filterMatrix: FilterMatrix) {
        colorFilterMatrixArray.add(filterMatrix)
    }
}