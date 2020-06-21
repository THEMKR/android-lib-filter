package com.mkrworld.libfilter

interface OnBaseFilter {

    /**
     * Method to apply Filter and Get final Value
     *
     * @return Filtered PixelArray
     */
    @Throws(Exception::class)
    abstract fun applyFilter(): IntArray
}