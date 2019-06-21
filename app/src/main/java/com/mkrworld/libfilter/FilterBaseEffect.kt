package com.mkrworld.libfilter

import java.util.*

/**
 * @author THEMKR
 */
internal abstract class FilterBaseEffect {

    protected val pixelArray: IntArray
    protected val imageWidth: Int
    protected val filterMatrixArrayList: ArrayList<FilterMatrix>

    /**
     * Constructor
     *
     * @param pixelArray            Array of Pixel
     * @param imageWidth
     * @param filterMatrixArrayList
     */
    constructor(pixelArray: IntArray, imageWidth: Int, filterMatrixArrayList: ArrayList<FilterMatrix>) {
        this.pixelArray = pixelArray
        this.imageWidth = imageWidth
        this.filterMatrixArrayList = filterMatrixArrayList
    }

    /**
     * Method to apply Filter and Get final Value
     *
     * @return Filtered PixelArray
     */
    @Throws(Exception::class)
    abstract fun applyFilter(): IntArray
}
