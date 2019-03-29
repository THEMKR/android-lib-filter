package com.mkrworld.libfilter.enums

import com.mkrworld.libfilter.dto.FilterMatrix
import com.mkrworld.libfilter.dto.Offset

/**
 * @author THEMKR
 */
enum class ColorFilter {

    GRAY_SCALE(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f))
    )),

    RED(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(-0.244f, 0.271f, 0.972f, 0.417f, 0.754f, -0.172f, -0.461f, 1.623f, -0.162f))
    )),

    INVERT(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, Offset(255f), floatArrayOf(-1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, -1f))
    )),

    BLUE(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 80f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f))
    )),

    CONTRAS_BLUE(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0.5f, 0f, 1f, 0.5f, 0f, 0f, 1f))
    )),

    GREEN(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(-0.063f, 1.71f, -0.646f, 0.2186f, 0.436f, 0.345f, 0.979f, 0.539f, -0.519f))
    )),

    CYAN(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(0.838f, 0.89f, -0.729f, -0.026f, 0.763f, 0.262f, 0.735f, -0.28f, 0.545f))
    )),

    MAGENTA(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(0.895f, -0.185f, 0.290f, 0.054f, 1.029f, -0.083f, -0.232f, 0.255f, 0.976f))
    )),

    PINK(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(0.083f, -0.07f, 0.9876f, 0.332f, 0.8846f, -0.216f, -0.591f, 1.3519f, 0.24f))
    )),

    SEPIA(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(-0.58f, 1.36f, 0.22f, 0.43f, 0.44f, 0.11f, 0.35f, 1.49f, -0.84f))
    )),

    VIOLET(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(0.618f, -0.296f, 0.677f, 0.163f, 1.015f, -0.179f, -0.494f, 0.715f, 0.779f))
    )),

    YELLOW(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(-0.574f, 1.43f, 0.143f, 0.426f, 0.43f, 0.144f, 0.426f, 1.429f, -0.855f))
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