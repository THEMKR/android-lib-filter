package com.mkrworld.libfilter

import android.graphics.Bitmap

/**
 * Class to hold the Per Define Filters or IF You create Some New One then You Should use the [FilterBuilder] for that
 */
class LibFilter {

    enum class FILTER {
        GRAYSCALE,
        INVERT,
        RED,
        BLUE,
        CONTRASBLUE,
        GREEN,
        CYAN,
        MAGENTA,
        PINK,
        SEPI,
        VIOLET,
        YELLOW,
        EDGE,
        SKETCH;
    }

    companion object {

        /**
         * Method to apply the filter
         * @param bitmap
         * @param filter
         */
        fun applyFilter(bitmap: Bitmap, filter: FILTER): Bitmap? {
            return when (filter) {
                FILTER.GRAYSCALE -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE))
                    ).buildEffect()
                }
                FILTER.INVERT -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect()
                }
                FILTER.RED -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_RED)
                    )).buildEffect()
                }
                FILTER.BLUE -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_BLUE)
                    )).buildEffect()
                }
                FILTER.CONTRASBLUE -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_CONTRAS_BLUE)
                    )).buildEffect()
                }
                FILTER.GREEN -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_GREEN)
                    )).buildEffect()
                }
                FILTER.CYAN -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_CYAN)
                    )).buildEffect()
                }
                FILTER.MAGENTA -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_MAGENTA)
                    )).buildEffect()
                }
                FILTER.PINK -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_PINK)
                    )).buildEffect()
                }
                FILTER.SEPI -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_SEPIA)
                    )).buildEffect()
                }
                FILTER.VIOLET -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_VIOLET)
                    )).buildEffect()
                }
                FILTER.YELLOW -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_YELLOW)
                    )).buildEffect()
                }
                FILTER.EDGE -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.CONVENTIONAL_EDGE),
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect()
                }
                FILTER.SKETCH -> {
                    FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.CONVENTIONAL_EDGE),
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect()
                }
                else -> {
                    bitmap
                }
            }
        }

        /**
         * Method to get the Filter
         */
        private fun getFilterMatrix(matrix: MATRIX): FilterMatrix {
            return FilterMatrix.Builder(matrix.filterCategory)
                    .setMatrix(matrix.matrix)
                    .setMultiplier(matrix.multiplier)
                    .setOffset(matrix.offset)
                    .build()
        }
    }
}