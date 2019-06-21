package com.mkrworld.libfilter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

/**
 * Class used to build the FilteredImage. User can pass its custom image for that
 * @author THEMKR
 */
class FilterBuilder {
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
    }

    /**
     * Make constructor private
     */
    private constructor() {

    }

    /**
     * BuilderColor used to build the Color effect
     */
    class BuilderColor {
        private var srcBitmap: Bitmap? = null
        private var filterMatrixArrayList: ArrayList<FilterMatrix>? = null

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): BuilderColor {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the arrayList of the filter matrix
         * @param filterMatrixArrayList
         */
        fun setFilterMatrixArrayList(filterMatrixArrayList: ArrayList<FilterMatrix>): BuilderColor {
            this.filterMatrixArrayList = filterMatrixArrayList
            return this
        }

        /**
         * Method to build the effect and return the new filtered bitmap
         * @return New Filtered Bitmap
         * @exception EXCEPTION if something wrong happen
         */
        fun buildEffect(): Bitmap? {
            if (!isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            val destBitmapIntArray = FilterColorEffect(srcBitmapIntArray, imageWidth, filterMatrixArrayList!!).applyFilter()
            return convertPixelArrayIntoBitmap(destBitmapIntArray!!, imageWidth, imageHeight)
        }

        /**
         * Method to check weather the Effect data is valid or not
         * @return TRUE if data is valid else through Exception
         */
        private fun isValid(): Boolean {
            if (srcBitmap == null) {
                throw Exception("SOURCE BITMAP NOT FOUND")
                return false
            }
            if (srcBitmap?.isRecycled ?: true) {
                throw Exception("SOURCE BITMAP IS RECYCLED")
                return false
            }
            if ((srcBitmap?.width ?: 0) <= 0 || (srcBitmap?.height ?: 0) <= 0) {
                throw Exception("SOURCE BITMAP SHOULD NOT BE EMPTY")
                return false
            }
            if (filterMatrixArrayList == null) {
                throw Exception("FILTER MATRIX NOT FOUND")
                return false
            }
            if ((filterMatrixArrayList?.size ?: 0) == 0) {
                throw Exception("FILTER MATRIX SHOULD NOT BE EMPTY")
                return false
            }
            return true
        }
    }

    /**
     * BuilderConventional used to build the Conventional effect
     */
    class BuilderConventional {
        private var srcBitmap: Bitmap? = null
        private var filterMatrixArrayList: ArrayList<FilterMatrix>? = null

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): BuilderConventional {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the arrayList of the filter matrix
         * @param filterMatrixArrayList
         */
        fun setFilterMatrixArrayList(filterMatrixArrayList: ArrayList<FilterMatrix>): BuilderConventional {
            this.filterMatrixArrayList = filterMatrixArrayList
            return this
        }

        /**
         * Method to build the effect and return the new filtered bitmap
         * @return New Filtered Bitmap
         * @exception EXCEPTION if something wrong happen
         */
        fun buildEffect(): Bitmap? {
            if (!isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            val destBitmapIntArray = FilterConventionalEffect(srcBitmapIntArray, imageWidth, filterMatrixArrayList!!).applyFilter()
            return convertPixelArrayIntoBitmap(destBitmapIntArray!!, imageWidth, imageHeight)
        }

        /**
         * Method to check weather the Effect data is valid or not
         * @return TRUE if data is valid else through Exception
         */
        private fun isValid(): Boolean {
            if (srcBitmap == null) {
                throw Exception("SOURCE BITMAP NOT FOUND")
                return false
            }
            if (srcBitmap?.isRecycled ?: true) {
                throw Exception("SOURCE BITMAP IS RECYCLED")
                return false
            }
            if ((srcBitmap?.width ?: 0) <= 0 || (srcBitmap?.height ?: 0) <= 0) {
                throw Exception("SOURCE BITMAP SHOULD NOT BE EMPTY")
                return false
            }
            if (filterMatrixArrayList == null) {
                throw Exception("FILTER MATRIX NOT FOUND")
                return false
            }
            if ((filterMatrixArrayList?.size ?: 0) == 0) {
                throw Exception("FILTER MATRIX SHOULD NOT BE EMPTY")
                return false
            }
            return true
        }
    }

    /**
     * BuilderOverlay used to build the Overlay effect
     */
    class BuilderOverlay {
        private var srcBitmap: Bitmap? = null
        private var overlayBitmap: Bitmap? = null
        private var multiplier: Float = 1.0F

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): BuilderOverlay {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the olayver bitmap
         * @param overlayBitmap
         */
        fun setOverlayBitmap(overlayBitmap: Bitmap): BuilderOverlay {
            this.overlayBitmap = overlayBitmap
            return this
        }

        /**
         * Method to set the Multiplier
         * @param multiplier Multiply the color intensity
         */
        fun setMultiplier(multiplier: Float): BuilderOverlay {
            this.multiplier = multiplier
            if (this.multiplier < 0F) {
                this.multiplier = 0F
            }
            return this
        }

        /**
         * Method to create the instance to FilterBuilder
         * @return The creator to create the effect
         * @exception EXCEPTION if something wrong happen
         */
        fun build(): Bitmap? {
            if (isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            var overlayBitmapIntArray: IntArray? = convertBitmapIntoPixelArray(getARGB888Image(overlayBitmap!!, imageWidth, imageHeight))
            val destBitmapIntArray = FilterOverlayEffect(srcBitmapIntArray, imageWidth, overlayBitmapIntArray!!, multiplier).applyFilter()
            return return convertPixelArrayIntoBitmap(destBitmapIntArray!!, imageWidth, imageHeight)
        }

        /**
         * Method to check weather the Effect data is valid or not
         * @return TRUE if data is valid else through Exception
         */
        private fun isValid(): Boolean {
            if (srcBitmap == null) {
                throw Exception("SOURCE BITMAP NOT FOUND")
                return false
            }
            if (srcBitmap?.isRecycled ?: true) {
                throw Exception("SOURCE BITMAP IS RECYCLED")
                return false
            }
            if ((srcBitmap?.width ?: 0) <= 0 || (srcBitmap?.height ?: 0) <= 0) {
                throw Exception("SOURCE BITMAP SHOULD NOT BE EMPTY")
                return false
            }

            if (overlayBitmap == null) {
                throw Exception("OVERLAY BITMAP NOT FOUND")
                return false
            }
            if (overlayBitmap?.isRecycled ?: true) {
                throw Exception("OVERLAY BITMAP IS RECYCLED")
                return false
            }
            if ((overlayBitmap?.width ?: 0) <= 0 || (srcBitmap?.height ?: 0) <= 0) {
                throw Exception("OVERLAY BITMAP SHOULD NOT BE EMPTY")
                return false
            }
            return true
        }
    }

    /**
     * BuilderMultiply used to build the Multiply effect
     */
    class BuilderMultiply {
        private var srcBitmap: Bitmap? = null
        private var overlayBitmap: Bitmap? = null
        private var multiplier: Float = 1.0F

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): BuilderMultiply {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the olayver bitmap
         * @param overlayBitmap
         */
        fun setOverlayBitmap(overlayBitmap: Bitmap): BuilderMultiply {
            this.overlayBitmap = overlayBitmap
            return this
        }

        /**
         * Method to set the Multiplier
         * @param multiplier Multiply the color intensity
         */
        fun setMultiplier(multiplier: Float): BuilderMultiply {
            this.multiplier = multiplier
            if (this.multiplier < 0F) {
                this.multiplier = 0F
            }
            return this
        }

        /**
         * Method to create the instance to FilterBuilder
         * @return The creator to create the effect
         * @exception EXCEPTION if something wrong happen
         */
        fun build(): Bitmap? {
            if (isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            var overlayBitmapIntArray: IntArray? = convertBitmapIntoPixelArray(getARGB888Image(overlayBitmap!!, imageWidth, imageHeight))
            val destBitmapIntArray = FilterMultiplierOverlayEffect(srcBitmapIntArray, imageWidth, overlayBitmapIntArray!!, multiplier).applyFilter()
            return return convertPixelArrayIntoBitmap(destBitmapIntArray!!, imageWidth, imageHeight)
        }

        /**
         * Method to check weather the Effect data is valid or not
         * @return TRUE if data is valid else through Exception
         */
        private fun isValid(): Boolean {
            if (srcBitmap == null) {
                throw Exception("SOURCE BITMAP NOT FOUND")
                return false
            }
            if (srcBitmap?.isRecycled ?: true) {
                throw Exception("SOURCE BITMAP IS RECYCLED")
                return false
            }
            if ((srcBitmap?.width ?: 0) <= 0 || (srcBitmap?.height ?: 0) <= 0) {
                throw Exception("SOURCE BITMAP SHOULD NOT BE EMPTY")
                return false
            }

            if (overlayBitmap == null) {
                throw Exception("OVERLAY BITMAP NOT FOUND")
                return false
            }
            if (overlayBitmap?.isRecycled ?: true) {
                throw Exception("OVERLAY BITMAP IS RECYCLED")
                return false
            }
            if ((overlayBitmap?.width ?: 0) <= 0 || (srcBitmap?.height ?: 0) <= 0) {
                throw Exception("OVERLAY BITMAP SHOULD NOT BE EMPTY")
                return false
            }
            return true
        }
    }

    /**
     * BuilderMerge used to build the Merge effect
     */
    class BuilderMerge {
        private var srcBitmap: Bitmap? = null
        private var overlayBitmap: Bitmap? = null
        private var overlayImageOpacity: Float = 1.0F

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): BuilderMerge {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the olayver bitmap
         * @param overlayBitmap
         */
        fun setOverlayBitmap(overlayBitmap: Bitmap): BuilderMerge {
            this.overlayBitmap = overlayBitmap
            return this
        }

        /**
         * Method to set the OverlayImageOpacity
         * @param overlayImageOpacity [0-1] only
         */
        fun setMultiplier(overlayImageOpacity: Float): BuilderMerge {
            this.overlayImageOpacity = overlayImageOpacity
            if (this.overlayImageOpacity < 0F) {
                this.overlayImageOpacity = 0F
            }
            if (this.overlayImageOpacity > 1F) {
                this.overlayImageOpacity = 1F
            }
            return this
        }

        /**
         * Method to create the instance to FilterBuilder
         * @return The creator to create the effect
         * @exception EXCEPTION if something wrong happen
         */
        fun build(): Bitmap? {
            if (isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            var overlayBitmapIntArray: IntArray? = convertBitmapIntoPixelArray(getARGB888Image(overlayBitmap!!, imageWidth, imageHeight))
            val destBitmapIntArray = FilterMergerOverlayEffect(srcBitmapIntArray, imageWidth, overlayBitmapIntArray!!, overlayImageOpacity).applyFilter()
            return return convertPixelArrayIntoBitmap(destBitmapIntArray!!, imageWidth, imageHeight)
        }

        /**
         * Method to check weather the Effect data is valid or not
         * @return TRUE if data is valid else through Exception
         */
        private fun isValid(): Boolean {
            if (srcBitmap == null) {
                throw Exception("SOURCE BITMAP NOT FOUND")
                return false
            }
            if (srcBitmap?.isRecycled ?: true) {
                throw Exception("SOURCE BITMAP IS RECYCLED")
                return false
            }
            if ((srcBitmap?.width ?: 0) <= 0 || (srcBitmap?.height ?: 0) <= 0) {
                throw Exception("SOURCE BITMAP SHOULD NOT BE EMPTY")
                return false
            }

            if (overlayBitmap == null) {
                throw Exception("OVERLAY BITMAP NOT FOUND")
                return false
            }
            if (overlayBitmap?.isRecycled ?: true) {
                throw Exception("OVERLAY BITMAP IS RECYCLED")
                return false
            }
            if ((overlayBitmap?.width ?: 0) <= 0 || (srcBitmap?.height ?: 0) <= 0) {
                throw Exception("OVERLAY BITMAP SHOULD NOT BE EMPTY")
                return false
            }
            return true
        }
    }
}