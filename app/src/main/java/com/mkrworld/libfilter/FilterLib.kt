package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.mkrworld.libfilter.dto.FilterMatrix
import com.mkrworld.libfilter.enums.FilterCategory

/**
 * Class for LIB Entry point
 * @author THEMKR
 */
class FilterLib {
    companion object {

        /**
         * Method to convert Bitmap into ARGB_8888 format.
         *
         * @param bitmap
         * @param destWidth   New dest width of the Bitmap
         * @param destHeighgt New dest height of the Bitmap
         */
        private fun getARGB888Image(bitmap: Bitmap, destWidth: Int, destHeighgt: Int): Bitmap {
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
        private fun convertBitmapIntoPixelArray(bitmap: Bitmap): IntArray {
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
        private fun convertPixelArrayIntoBitmap(bitmapPixelArray: IntArray, width: Int, height: Int): Bitmap {
            val filteredBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            filteredBitmap.setPixels(bitmapPixelArray, 0, width, 0, 0, width, height)
            return filteredBitmap
        }

        /**
         * Builder used to build the effect
         */
        class Builder {
            private val filterCategory: FilterCategory
            private var srcBitmap: Bitmap? = null
            private var destBitmap: Bitmap? = null
            private var filterMatrixArrayList: ArrayList<FilterMatrix>? = null
            private var multiplier: Float = 1.0F
            private var offSet: Float = 1.0F

            /**
             * Constructor
             * @param filterCategory
             */
            constructor(filterCategory: FilterCategory) {
                this.filterCategory = filterCategory
            }
        }
    }
}