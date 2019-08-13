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
         * Method to get the Filter from the Given Predefine Matrix
         */
        fun getFilterMatrix(matrix: MATRIX): FilterMatrix {
            return FilterMatrix.Builder(matrix.filterCategory)
                    .setMatrix(matrix.matrix)
                    .setMultiplier(matrix.multiplier)
                    .setOffset(matrix.offset)
                    .build()
        }
    }

    /**
     * Make constructor private
     */
    private constructor() {

    }

    /**
     * Builder used to apply effect on single Image only Color/Conventional Both
     */
    class SingleImageEffectBuilder() {
        private var srcBitmap: Bitmap? = null
        private var filterMatrixArrayList: ArrayList<FilterMatrix>? = null

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): SingleImageEffectBuilder {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the arrayList of the filter matrix
         * @param filterMatrixArrayList
         */
        fun setFilterMatrixArrayList(filterMatrixArrayList: ArrayList<FilterMatrix>): SingleImageEffectBuilder {
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
            val destBitmapIntArray = SingleImageFilter(srcBitmapIntArray, imageWidth, filterMatrixArrayList!!).applyFilter()
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
     * Builder used to build the OverlayEffectBuilder effect
     */
    class OverlayEffectBuilder {
        private var srcBitmap: Bitmap? = null
        private var overlayBitmap: Bitmap? = null
        private var multiplier: Float = 1.0F

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): OverlayEffectBuilder {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the olayver bitmap
         * @param overlayBitmap
         */
        fun setOverlayBitmap(overlayBitmap: Bitmap): OverlayEffectBuilder {
            this.overlayBitmap = overlayBitmap
            return this
        }

        /**
         * Method to set the Multiplier
         * @param multiplier MultiplyEffectBuilder the color intensity
         */
        fun setMultiplier(multiplier: Float): OverlayEffectBuilder {
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
        fun buildEffect(): Bitmap? {
            if (!isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            var overlayBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(overlayBitmap!!, imageWidth, imageHeight))
            val destBitmapIntArray = OverlayImageFilter(srcBitmapIntArray, overlayBitmapIntArray, imageWidth, multiplier).applyFilter()
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
     * Builder used to build the MultiplyEffectBuilder effect
     */
    class MultiplyEffectBuilder {
        private var srcBitmap: Bitmap? = null
        private var overlayBitmap: Bitmap? = null
        private var multiplier: Float = 1.0F

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): MultiplyEffectBuilder {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the olayver bitmap
         * @param overlayBitmap
         */
        fun setOverlayBitmap(overlayBitmap: Bitmap): MultiplyEffectBuilder {
            this.overlayBitmap = overlayBitmap
            return this
        }

        /**
         * Method to set the Multiplier
         * @param multiplier MultiplyEffectBuilder the color intensity
         */
        fun setMultiplier(multiplier: Float): MultiplyEffectBuilder {
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
        fun buildEffect(): Bitmap? {
            if (!isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            var overlayBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(overlayBitmap!!, imageWidth, imageHeight))
            val destBitmapIntArray = MultiplyImageFilter(srcBitmapIntArray, overlayBitmapIntArray, imageWidth, multiplier).applyFilter()
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
     * Builder used to build the MergeEffectBuilder effect
     */
    class MergeEffectBuilder {
        private var srcBitmap: Bitmap? = null
        private var overlayBitmap: Bitmap? = null
        private var overlayImageOpacity: Float = 1.0F

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): MergeEffectBuilder {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the olayver bitmap
         * @param overlayBitmap
         */
        fun setOverlayBitmap(overlayBitmap: Bitmap): MergeEffectBuilder {
            this.overlayBitmap = overlayBitmap
            return this
        }

        /**
         * Method to set the OverlayImageOpacity
         * @param overlayImageOpacity [0-1] only
         */
        fun setMultiplier(overlayImageOpacity: Float): MergeEffectBuilder {
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
        fun buildEffect(): Bitmap? {
            if (!isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            var overlayBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(overlayBitmap!!, imageWidth, imageHeight))
            val destBitmapIntArray = MergeImageFilter(srcBitmapIntArray, overlayBitmapIntArray, imageWidth, overlayImageOpacity).applyFilter()
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
     * Builder used to build the DodgeEffectBuilder effect
     */
    class DodgeEffectBuilder {
        private var srcBitmap: Bitmap? = null
        private var overlayBitmap: Bitmap? = null

        /**
         * Method to set the source bitmap
         * @param srcBitmap
         */
        fun setSrcBitmap(srcBitmap: Bitmap): DodgeEffectBuilder {
            this.srcBitmap = srcBitmap
            return this
        }

        /**
         * Method to set the olayver bitmap
         * @param overlayBitmap
         */
        fun setOverlayBitmap(overlayBitmap: Bitmap): DodgeEffectBuilder {
            this.overlayBitmap = overlayBitmap
            return this
        }

        /**
         * Method to create the instance to FilterBuilder
         * @return The creator to create the effect
         * @exception EXCEPTION if something wrong happen
         */
        fun buildEffect(): Bitmap? {
            if (!isValid()) {
                return null
            }
            val srcBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(srcBitmap!!, srcBitmap!!.width, srcBitmap!!.height))
            val imageWidth: Int = srcBitmap!!.width
            val imageHeight: Int = srcBitmap!!.height
            var overlayBitmapIntArray: IntArray = convertBitmapIntoPixelArray(getARGB888Image(overlayBitmap!!, imageWidth, imageHeight))
            val destBitmapIntArray = DodgeImageFilter(srcBitmapIntArray, overlayBitmapIntArray, imageWidth).applyFilter()
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