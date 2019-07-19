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
        BLUR,
        SKETCH,
        COLOR_SKETCH,
        P1;
    }

    companion object {

        /**
         * Method to apply the filter
         * @param bitmap
         * @param filter
         */
        fun applyFilter(bitmap: Bitmap, filter: FILTER): Bitmap {
            when (filter) {
                FILTER.GRAYSCALE -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE))
                    ).buildEffect() ?: bitmap
                }
                FILTER.INVERT -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect() ?: bitmap
                }
                FILTER.RED -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_RED)
                    )).buildEffect() ?: bitmap
                }
                FILTER.BLUE -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_BLUE)
                    )).buildEffect() ?: bitmap
                }
                FILTER.CONTRASBLUE -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_CONTRAS_BLUE)
                    )).buildEffect() ?: bitmap
                }
                FILTER.GREEN -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_GREEN)
                    )).buildEffect() ?: bitmap
                }
                FILTER.CYAN -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_CYAN)
                    )).buildEffect() ?: bitmap
                }
                FILTER.MAGENTA -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_MAGENTA)
                    )).buildEffect() ?: bitmap
                }
                FILTER.PINK -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_PINK)
                    )).buildEffect() ?: bitmap
                }
                FILTER.SEPI -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_SEPIA)
                    )).buildEffect() ?: bitmap
                }
                FILTER.VIOLET -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_VIOLET)
                    )).buildEffect() ?: bitmap
                }
                FILTER.YELLOW -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                            getFilterMatrix(MATRIX.COLOR_YELLOW)
                    )).buildEffect() ?: bitmap
                }
                FILTER.BLUR -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR)
                    )).buildEffect() ?: bitmap
                }
                FILTER.SKETCH -> {
                    val grayScaleImage = applyFilter(bitmap, FILTER.GRAYSCALE)
                    val invertImage = applyFilter(grayScaleImage, FILTER.INVERT)
                    val invertBlur = applyFilter(invertImage, FILTER.BLUR)
                    return FilterBuilder.Dodge().setSrcBitmap(grayScaleImage).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.COLOR_SKETCH -> {
                    val invertImage = applyFilter(bitmap, FILTER.INVERT)
                    val invertBlur = applyFilter(invertImage, FILTER.BLUR)
                    return FilterBuilder.Dodge().setSrcBitmap(bitmap).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.P1 -> {
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