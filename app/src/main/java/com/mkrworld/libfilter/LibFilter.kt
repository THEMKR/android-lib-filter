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
        BLUR,
        SKETCH;
    }

    companion object {

        /**
         * Method to apply the filter
         * @param bitmap
         * @param filter
         */
        fun applyFilter(bitmap: Bitmap, filter: FILTER): Bitmap? {
            when (filter) {
                FILTER.GRAYSCALE -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE))
                    ).buildEffect()
                }
                FILTER.INVERT -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect()
                }
                FILTER.RED -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_RED)
                    )).buildEffect()
                }
                FILTER.BLUE -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_BLUE)
                    )).buildEffect()
                }
                FILTER.CONTRASBLUE -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_CONTRAS_BLUE)
                    )).buildEffect()
                }
                FILTER.GREEN -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_GREEN)
                    )).buildEffect()
                }
                FILTER.CYAN -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_CYAN)
                    )).buildEffect()
                }
                FILTER.MAGENTA -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_MAGENTA)
                    )).buildEffect()
                }
                FILTER.PINK -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_PINK)
                    )).buildEffect()
                }
                FILTER.SEPI -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_SEPIA)
                    )).buildEffect()
                }
                FILTER.VIOLET -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_VIOLET)
                    )).buildEffect()
                }
                FILTER.YELLOW -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_YELLOW)
                    )).buildEffect()
                }
                FILTER.EDGE -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.CONVENTIONAL_EDGE),
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect()
                }
                FILTER.BLUR -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR)
                    )).buildEffect()
                }
                FILTER.SKETCH -> {
                    val grayScaleImage = FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE)
                    )).buildEffect() ?: return bitmap
                    val invertImage = FilterBuilder.SingleImage().setSrcBitmap(grayScaleImage).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect() ?: return bitmap
                    val invertBlur = applyFilter(invertImage, FILTER.BLUR) ?: bitmap
                    return FilterBuilder.Dodge().setSrcBitmap(grayScaleImage).setOverlayBitmap(invertBlur).buildEffect()
                }
            }
            return bitmap
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