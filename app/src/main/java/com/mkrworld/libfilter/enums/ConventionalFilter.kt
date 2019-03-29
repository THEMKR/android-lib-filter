package com.mkrworld.libfilter.enums

import com.mkrworld.libfilter.dto.FilterMatrix
import com.mkrworld.libfilter.dto.Offset

/**
 * @author THEMKR
 */
enum class ConventionalFilter {

    SKETCH(arrayListOf(
            FilterMatrix(FilterCategory.CONVENTIONAL, floatArrayOf(2f, 3f, -3f, 1f, -1f, 1f, -2f, 1f, -2f)),
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(255f), floatArrayOf(-1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, -1f))
    )),

    SOLID(arrayListOf(
            FilterMatrix(FilterCategory.CONVENTIONAL, floatArrayOf(2f, 3f, -3f, 1f, -1f, 1f, -2f, 1f, -2f))
    ));

    /**
     * Effect List
     */
    val filterMatrixArrayList: ArrayList<FilterMatrix>

    /**
     * Constructor
     *
     * @param filterMatrixArrayList
     */
    constructor(filterMatrixArrayList: ArrayList<FilterMatrix>) {
        this.filterMatrixArrayList = filterMatrixArrayList
    }
}