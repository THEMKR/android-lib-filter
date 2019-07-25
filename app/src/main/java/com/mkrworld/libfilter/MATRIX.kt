package com.mkrworld.libfilter

internal enum class MATRIX {

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

    COLOR_NEUTRALIZER_B(FilterCategory.COLOR, Offset(0f, 0f, 80f), floatArrayOf(
            1F, 0F, 0F,
            0F, 1F, 0F,
            0F, 0F, 1F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_RED(FilterCategory.COLOR, floatArrayOf(
            -0.244F, 0.271F, 0.972F,
            0.417F, 0.754F, -0.172F,
            -0.461F, 1.623F, -0.162F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_BLUE(FilterCategory.COLOR, Offset(0f, 0f, 90f), floatArrayOf(
            1F, 0F, 0F,
            0F, 1F, 0F,
            0F, 0F, 1F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_CONTRAS_BLUE(FilterCategory.COLOR, Offset(0f, 0f, 90f), floatArrayOf(
            1F, 0F, 0.5F,
            0F, 1F, 0.5F,
            0F, 0F, 1F)),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_GREEN(FilterCategory.COLOR, floatArrayOf(
            -0.063F, 1.71F, -0.646F,
            0.2186F, 0.436F, 0.345F,
            0.979F, 0.539F, -0.519F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_CYAN(FilterCategory.COLOR, floatArrayOf(
            0.838F, 0.89F, -0.729F,
            -0.026F, 0.763F, 0.262F,
            0.735F, -0.28F, 0.545F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_MAGENTA(FilterCategory.COLOR, floatArrayOf(
            0.895F, -0.185F, 0.290F,
            0.054F, 1.029F, -0.083F,
            -0.232F, 0.255F, 0.976F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_PINK(FilterCategory.COLOR, floatArrayOf(
            0.083F, -0.07F, 0.9876F,
            0.332F, 0.8846F, -0.216F,
            -0.591F, 1.3519F, 0.24F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_SEPIA(FilterCategory.COLOR, floatArrayOf(
            -0.58F, 1.36F, 0.22F,
            0.43F, 0.44F, 0.11F,
            0.35F, 1.49F, -0.84F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_VIOLET(FilterCategory.COLOR, floatArrayOf(
            0.618F, -0.296F, 0.677F,
            0.163F, 1.015F, -0.179F,
            -0.494F, 0.715F, 0.779F
    )),

    /**
     * BEFORE IT -> GRAY_SCALE + COLOR_NEUTRALIZER_B
     */
    COLOR_YELLOW(FilterCategory.COLOR, floatArrayOf(
            -0.574F, 1.43F, 0.143F,
            0.426F, 0.43F, 0.144F,
            0.426F, 1.429F, -0.855F
    )),

    CONVENTIONAL_EDGE(FilterCategory.CONVENTIONAL, floatArrayOf(
            2f, 3f, -3f,
            1f, -1f, 1f,
            -2f, 1f, -2f
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
    )),

    CONVENTIONAL_SHARPEN(FilterCategory.CONVENTIONAL, Offset(80F), floatArrayOf(
            0f, -1f, 0f,
            -1f, 5f, -1f,
            0f, -1f, 0f
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