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
    )),

    SHARP(arrayListOf(
            FilterMatrix(FilterCategory.CONVENTIONAL, floatArrayOf(0f, -3f, 0f, -3f, 15f, -3f, 0f, -3f, 0f))
    )),

    EDGE_ENHANCE(arrayListOf(
            FilterMatrix(FilterCategory.CONVENTIONAL, floatArrayOf(0f, 0f, 0f, -1f, -1f, 0f, 0f, 0f, 0f))
    )),

    EMBOSS(arrayListOf(
            FilterMatrix(FilterCategory.CONVENTIONAL, floatArrayOf(-2f, -1f, 0f, -1f, 1f, 1f, 0f, 1f, 2f))
    )),

    LIGHTEN(arrayListOf(
            FilterMatrix(FilterCategory.CONVENTIONAL, floatArrayOf(0f, 0f, 0f, 0f, 12f / 9f, 0f, 0f, 0f, 0f))
    )),

    MIKE_FAVORITE(arrayListOf(
            FilterMatrix(FilterCategory.CONVENTIONAL, floatArrayOf(2f / 9f, 22f / 9f, 1f / 9f, 22f / 9f, 1f / 9f, -22f / 9f, 1f / 9f, -22f / 9f, -2f / 9f))
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