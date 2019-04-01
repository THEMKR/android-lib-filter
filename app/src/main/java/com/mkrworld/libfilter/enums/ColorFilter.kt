package com.mkrworld.libfilter.enums

import com.mkrworld.libfilter.dto.FilterMatrix
import com.mkrworld.libfilter.dto.Offset

/**
 * @author THEMKR
 */
enum class ColorFilter {

    GRAY_SCALE(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            ))
    )),

    RED(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(
                    -0.244F, 0.271F, 0.972F,
                    0.417F, 0.754F, -0.172F,
                    -0.461F, 1.623F, -0.162F
            ))
    )),

    INVERT(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, Offset(255f), floatArrayOf(
                    -1F, 0F, 0F,
                    0F, -1F, 0F,
                    0F, 0F, -1F
            ))
    )),

    BLUE(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 80f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F))
    )),

    CONTRAS_BLUE(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F)),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0.5F,
                    0F, 1F, 0.5F,
                    0F, 0F, 1F))
    )),

    GREEN(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(
                    -0.063F, 1.71F, -0.646F,
                    0.2186F, 0.436F, 0.345F,
                    0.979F, 0.539F, -0.519F
            ))
    )),

    CYAN(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(
                    0.838F, 0.89F, -0.729F,
                    -0.026F, 0.763F, 0.262F,
                    0.735F, -0.28F, 0.545F
            ))
    )),

    MAGENTA(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(
                    0.895F, -0.185F, 0.290F,
                    0.054F, 1.029F, -0.083F,
                    -0.232F, 0.255F, 0.976F
            ))
    )),

    PINK(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(
                    0.083F, -0.07F, 0.9876F,
                    0.332F, 0.8846F, -0.216F,
                    -0.591F, 1.3519F, 0.24F
            ))
    )),

    SEPIA(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(
                    -0.58F, 1.36F, 0.22F,
                    0.43F, 0.44F, 0.11F,
                    0.35F, 1.49F, -0.84F
            ))
    )),

    VIOLET(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(
                    0.618F, -0.296F, 0.677F,
                    0.163F, 1.015F, -0.179F,
                    -0.494F, 0.715F, 0.779F
            ))
    )),

    YELLOW(arrayListOf(
            FilterMatrix(FilterCategory.COLOR, 0.3333f, floatArrayOf(
                    1F, 1F, 1F,
                    1F, 1F, 1F,
                    1F, 1F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, Offset(0f, 0f, 86f), floatArrayOf(
                    1F, 0F, 0F,
                    0F, 1F, 0F,
                    0F, 0F, 1F
            )),
            FilterMatrix(FilterCategory.COLOR, floatArrayOf(
                    -0.574F, 1.43F, 0.143F,
                    0.426F, 0.43F, 0.144F,
                    0.426F, 1.429F, -0.855F
            ))
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