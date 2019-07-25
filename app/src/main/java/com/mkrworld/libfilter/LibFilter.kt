package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.util.Log

/**
 * Class to hold the Per Define Filters or IF You create Some New One then You Should use the [FilterBuilder] for that
 */
class LibFilter {

    enum class FILTER {
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
        EXPREMENT,
        // ==========================================================================================
        // FINAL
        // ==========================================================================================
        SKETCH_LIGHT,
        SKETCH_DARK,
        COLOR_SKETCH_LIGHT,
        COLOR_SKETCH_DARK,
        EMBOS
    }

    companion object {

        /**
         * Method to apply the filter
         * @param bitmap
         * @param filter
         */
        fun applyFilter(bitmap: Bitmap, filter: FILTER): Bitmap {
            when (filter) {
                FILTER.EXPREMENT -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.CONVENTIONAL_SHARPEN)
                    )).buildEffect() ?: bitmap
                }
                // ==========================================================================================
                // FINAL
                // ==========================================================================================
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
                FILTER.COLOR_SKETCH_LIGHT -> {
                    val currentTimeMillis = System.currentTimeMillis()
                    val invertImage = FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect() ?: bitmap
                    val invertBlur = getBlurBitmap(invertImage, 3)
                    Log.e("MKR", "COLOR_SKETCH_DARK : ${System.currentTimeMillis() - currentTimeMillis}")
                    return FilterBuilder.Dodge().setSrcBitmap(bitmap).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.COLOR_SKETCH_DARK -> {
                    val currentTimeMillis = System.currentTimeMillis()
                    val invertImage = FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect() ?: bitmap
                    val invertBlur = getBlurBitmap(invertImage, 6)
                    Log.e("MKR", "COLOR_SKETCH_DARK : ${System.currentTimeMillis() - currentTimeMillis}")
                    return FilterBuilder.Dodge().setSrcBitmap(bitmap).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.SKETCH_LIGHT -> {
                    val currentTimeMillis = System.currentTimeMillis()
                    val grayScaleImage = FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE)
                    )).buildEffect() ?: bitmap
                    val invertImage = FilterBuilder.SingleImage().setSrcBitmap(grayScaleImage).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect() ?: bitmap
                    val invertBlur = getBlurBitmap(invertImage, 3)
                    Log.e("MKR", "SKETCH_DARK : ${System.currentTimeMillis() - currentTimeMillis}")
                    return FilterBuilder.Dodge().setSrcBitmap(grayScaleImage).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.SKETCH_DARK -> {
                    val currentTimeMillis = System.currentTimeMillis()
                    val grayScaleImage = FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE)
                    )).buildEffect() ?: bitmap
                    val invertImage = FilterBuilder.SingleImage().setSrcBitmap(grayScaleImage).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_INVERT)
                    )).buildEffect() ?: bitmap
                    val invertBlur = getBlurBitmap(invertImage, 6)
                    Log.e("MKR", "SKETCH_LIGHT : ${System.currentTimeMillis() - currentTimeMillis}")
                    return FilterBuilder.Dodge().setSrcBitmap(grayScaleImage).setOverlayBitmap(invertBlur).buildEffect() ?: bitmap
                }
                FILTER.EMBOS -> {
                    return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                            getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                            getFilterMatrix(MATRIX.CONVENTIONAL_EMBOS)
                    )).buildEffect() ?: bitmap
                }
            }
            return bitmap
        }

        /**
         * Method to get the blur bitmap
         * @param bitmap
         * @param level
         * @return
         */
        private fun getBlurBitmap(bitmap: Bitmap, level: Int): Bitmap {
            var bitmap = bitmap
            for (count in 0 until level) {
                Log.e("MKR", "BLUR INDEX : $level  :  $count")
                bitmap = FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(arrayListOf(
                        getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                        getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                        getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                        getFilterMatrix(MATRIX.CONVENTIONAL_BLUR),
                        getFilterMatrix(MATRIX.CONVENTIONAL_BLUR)
                )).buildEffect() ?: bitmap
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