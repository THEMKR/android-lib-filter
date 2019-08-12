package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log

/**
 * Class to hold the Per Define Filters or IF You create Some New One then You Should use the [FilterBuilder] for that
 */
class LibFilter {

    enum class FILTER {
        SKETCH_LIGHT,
        SKETCH_DARK,
        COLOR_SKETCH_LIGHT,
        COLOR_SKETCH_DARK,
        EMBOS
    }

    companion object {

        /**
         * Method to convert Bitmap into ARGB_8888 format.
         *
         * @param bitmap
         * @param destWidth   New dest width of the Bitmap
         * @param destHeighgt New dest height of the Bitmap
         */
        fun getARGB888Image(bitmap: Bitmap, destWidth: Int, destHeighgt: Int): Bitmap {
            if (bitmap.config == Bitmap.Config.ARGB_8888 && bitmap.width == destWidth && bitmap.height == destHeighgt) {
                return bitmap
            }
            val newARGBBitmap = Bitmap.createBitmap(destWidth, destHeighgt, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(newARGBBitmap)
            val paint = Paint()
            paint.isAntiAlias = true
            paint.isFilterBitmap = true
            canvas.drawBitmap(bitmap, null, RectF(0f, 0f, destWidth.toFloat(), destHeighgt.toFloat()), paint)
            return newARGBBitmap
        }

        /**
         * Method ot convert the Bitmap into INT[] pixel Array
         *
         * @param bitmap
         * @return
         */
        fun convertBitmapIntoPixelArray(bitmap: Bitmap): IntArray {
            val bitmapPixelArray = IntArray(bitmap.width * bitmap.height)
            bitmap.getPixels(bitmapPixelArray, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
            return bitmapPixelArray
        }

        /**
         * Method to convert the Pixel Array Into the Bitmap
         *
         * @param bitmapPixelArray
         * @param width            Width of the Bitmap
         * @param height           Height of the Bitmap
         * @return
         */
        fun convertPixelArrayIntoBitmap(bitmapPixelArray: IntArray, width: Int, height: Int): Bitmap {
            val filteredBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            filteredBitmap.setPixels(bitmapPixelArray, 0, width, 0, 0, width, height)
            return filteredBitmap
        }

        /**
         * Method to apply the filter
         * @param bitmap
         * @param filter
         */
        fun applyFilter(bitmap: Bitmap, filter: FILTER): Bitmap {
            when (filter) {
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

        /**
         * Method to get the Filter
         */
        private fun getFilterMatrix(matrix: MATRIX, multiplier: Float): FilterMatrix {
            return FilterMatrix.Builder(matrix.filterCategory)
                    .setMatrix(matrix.matrix)
                    .setMultiplier(multiplier)
                    .setOffset(matrix.offset)
                    .build()
        }
    }
}