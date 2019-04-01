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
class FilterCreator {
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
    }

    private val filterCategory: FilterCategory
    private val srcBitmapIntArray: IntArray
    private val imageWidth: Int
    private val imageHeight: Int
    private val overlayBitmapIntArray: IntArray?
    private val filterMatrixArrayList: ArrayList<FilterMatrix>?
    private val multiplier: Float
    private val offSet: Float

    /**
     * CONSTRUCTOR
     * @param filterCategory
     * @param srcBitmapIntArray
     * @param imageWidth
     * @param imageHeight
     * @param overlayBitmapIntArray
     * @param filterMatrixArrayList
     * @param multiplier
     * @param offSet
     */
    constructor(filterCategory: FilterCategory, srcBitmapIntArray: IntArray, imageWidth: Int, imageHeight: Int, overlayBitmapIntArray: IntArray?, filterMatrixArrayList: ArrayList<FilterMatrix>?, multiplier: Float, offSet: Float) {
        this.filterCategory = filterCategory
        this.srcBitmapIntArray = srcBitmapIntArray
        this.imageWidth = imageWidth
        this.imageHeight = imageHeight
        this.overlayBitmapIntArray = overlayBitmapIntArray
        this.filterMatrixArrayList = filterMatrixArrayList
        this.multiplier = multiplier
        this.offSet = offSet
    }

    /**
     * Method to apply effect
     */
    fun applyEffect(): Bitmap? {
        var filterImageIntArray: IntArray?
        when (filterCategory) {
            FilterCategory.COLOR -> {
                filterImageIntArray = ColorFilter(srcBitmapIntArray, imageWidth, filterMatrixArrayList ?: ArrayList()).applyFilter()
            }
            FilterCategory.CONVENTIONAL -> {
                filterImageIntArray = ColorFilter(srcBitmapIntArray, imageWidth, filterMatrixArrayList ?: ArrayList()).applyFilter()
            }
            FilterCategory.OVERLAY -> {
                filterImageIntArray = ColorFilter(srcBitmapIntArray, imageWidth, filterMatrixArrayList ?: ArrayList()).applyFilter()
            }
            FilterCategory.MULTIPLY -> {
                filterImageIntArray = ColorFilter(srcBitmapIntArray, imageWidth, filterMatrixArrayList ?: ArrayList()).applyFilter()
            }
        }
        return convertPixelArrayIntoBitmap(filterImageIntArray!!, imageWidth, imageHeight)
    }

    /**
     * Builder used to build the effect
     */
    class Builder {
        private val filterCategory: FilterCategory
        private var srcBitmap: Bitmap? = null
        private var overlayBitmap: Bitmap? = null
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

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): Builder {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the olayver bitmap
         * @param destBitmap
         */
        fun setOverlayBitmap(destBitmap: Bitmap): Builder {
            this.overlayBitmap = destBitmap
            return this
        }

        /**
         * Method to set the arrayList of the filter matrix
         * @param filterMatrixArrayList
         */
        fun setFilterMatrixArrayList(filterMatrixArrayList: ArrayList<FilterMatrix>): Builder {
            this.filterMatrixArrayList = filterMatrixArrayList
            return this
        }

        /**
         * Method to set the Multiplier
         * @param multiplier
         */
        fun setMultiplier(multiplier: Float): Builder {
            this.multiplier = multiplier
            return this
        }

        /**
         * Method to set the OffSet
         * @param offSet
         */
        fun setOffSet(offSet: Float): Builder {
            this.offSet = offSet
            return this
        }

        /**
         * Method to create the instance to FilterCreator
         * @return The creator to create the effect
         * @exception EXCEPTION if something wrong happen
         */
        fun build(): FilterCreator? {

            // VALIDATE SRC BITMAP
            if (!isBitmapValid(srcBitmap, "SOURCE-BITMAP")) {
                return null
            }

            // VALIDATE OVERLAY BITMAP AND EFFECT MATRIX
            when (filterCategory) {
                FilterCategory.CONVENTIONAL, FilterCategory.COLOR -> {
                    if (filterMatrixArrayList == null || (filterMatrixArrayList?.size ?: 0) == 0) {
                        throw Exception("Plz set a valid array of FilterMatrix")
                        return null
                    }
                }
                FilterCategory.OVERLAY, FilterCategory.MULTIPLY -> {
                    if (!isBitmapValid(srcBitmap, "SOURCE-BITMAP") || !isBitmapValid(overlayBitmap, "OVERLAY-BITMAP")) {
                        return null
                    }
                }
            }

            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            var overlayBitmapIntArray: IntArray? = if (overlayBitmap != null) {
                convertBitmapIntoPixelArray(getARGB888Image(overlayBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            } else {
                null
            }
            return FilterCreator(filterCategory, srcBitmapIntArray, imageWidth, imageHeight, overlayBitmapIntArray, filterMatrixArrayList, multiplier, offSet)
        }

        /**
         * Method to check weather the Bitmap is valid or not
         * @param bitmap
         * @param bitmapName
         * @return TRUE if valid bitmap else through Exception
         */
        private fun isBitmapValid(bitmap: Bitmap?, bitmapName: String): Boolean {
            if (bitmap == null) {
                throw Exception("Plz set a $bitmap")
                return false
            }
            if (bitmap?.isRecycled == true) {
                throw Exception("Don not put a recycled $bitmap")
                return false
            }
            if ((bitmap?.width ?: 0) <= 0 || (bitmap?.height ?: 0) <= 0) {
                throw Exception("$bitmap atleast have 1px width and height")
                return false
            }
            return true
        }
    }
}