package com.mkrworld.libfilter

enum class MATRIX {

    COLOR_GRAY_SCALE(FilterCategory.COLOR, 0.3F, floatArrayOf(
            1F, 1F, 1F,
            1F, 1F, 1F,
            1F, 1F, 1F
    )),

    COLOR_INVERT(FilterCategory.COLOR, Offset(255f), floatArrayOf(
            -1F, 0F, 0F,
            0F, -1F, 0F,
            0F, 0F, -1F
    )),

    CONVENTIONAL_BLUR(FilterCategory.CONVENTIONAL, 0.11111F, floatArrayOf(
            1F, 1F, 1F,
            1F, 1F, 1F,
            1F, 1F, 1F
    )),

    CONVENTIONAL_EMBOS(FilterCategory.CONVENTIONAL, floatArrayOf(
            -2f, -1f, 0f,
            -1f, 1f, 1f,
            0f, 1f, 2f
    ));

    val filterCategory: FilterCategory
    val multiplier: Float
    val offset: Offset
    val matrix: FloatArray

    constructor(filterCategory: FilterCategory, matrix: FloatArray) : this(filterCategory, 1F, Offset(0F), matrix)

    constructor(filterCategory: FilterCategory, multiplier: Float, matrix: FloatArray) : this(filterCategory, multiplier, Offset(0F), matrix)

    constructor(filterCategory: FilterCategory, offset: Offset, matrix: FloatArray) : this(filterCategory, 1F, offset, matrix)

    constructor(filterCategory: FilterCategory, multiplier: Float, offset: Offset, matrix: FloatArray) {
        this.filterCategory = filterCategory
        this.multiplier = multiplier
        this.offset = offset
        this.matrix = matrix
    }
}