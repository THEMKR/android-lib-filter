package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.util.Log

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
        SKETCH_1,
        SKETCH_2,
        COLOR_SKETCH_1,
        COLOR_SKETCH_2,
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
                    val currentTimeMillis = System.currentTimeMillis()
                    val blur = FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                            getFilterMatrix(MATRIX.CONVENTIONAL_BLUR)
                    )).buildEffect() ?: bitmap
                    Log.e("MKR", "BLUR : ${System.currentTimeMillis() - currentTimeMillis}")
                    return blur
                }
                FILTER.COLOR_SKETCH_1 -> {
                    val currentTimeMillis = System.currentTimeMillis()
                    val invertImage = applyFilter(bitmap, FILTER.INVERT)
                    var invertBlur = applyFilter(invertImage, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    Log.e("MKR", "COLOR_SKETCH_2 : ${System.currentTimeMillis() - currentTimeMillis}")
                    return FilterBuilder.Dodge().setSrcBitmap(bitmap).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.COLOR_SKETCH_2 -> {
                    val currentTimeMillis = System.currentTimeMillis()
                    val invertImage = applyFilter(bitmap, FILTER.INVERT)
                    var invertBlur = applyFilter(invertImage, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    Log.e("MKR", "COLOR_SKETCH_2 : ${System.currentTimeMillis() - currentTimeMillis}")
                    return FilterBuilder.Dodge().setSrcBitmap(bitmap).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.SKETCH_1 -> {
                    val currentTimeMillis = System.currentTimeMillis()
                    val grayScaleImage = applyFilter(bitmap, FILTER.GRAYSCALE)
                    val invertImage = applyFilter(grayScaleImage, FILTER.INVERT)
                    var invertBlur = applyFilter(invertImage, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    Log.e("MKR", "SKETCH_2 : ${System.currentTimeMillis() - currentTimeMillis}")
                    return FilterBuilder.Dodge().setSrcBitmap(grayScaleImage).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.SKETCH_2 -> {
                    val currentTimeMillis = System.currentTimeMillis()
                    val grayScaleImage = applyFilter(bitmap, FILTER.GRAYSCALE)
                    val invertImage = applyFilter(grayScaleImage, FILTER.INVERT)
                    var invertBlur = applyFilter(invertImage, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    invertBlur = applyFilter(invertBlur, FILTER.BLUR)
                    Log.e("MKR", "SKETCH_1 : ${System.currentTimeMillis() - currentTimeMillis}")
                    return FilterBuilder.Dodge().setSrcBitmap(grayScaleImage).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
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