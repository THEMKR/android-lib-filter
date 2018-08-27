package com.mkrworld.libfilter.filter;

public abstract class BaseFilter {

    private int[] mPixelArray;
    private int mImageWidth;

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     */
    public BaseFilter(int[] pixelArray, int imageWidth) {
        mPixelArray = pixelArray;
        mImageWidth = imageWidth;
    }

    /**
     * Method to apply Filter and Get final Value
     *
     * @return Filtered PixelArray
     */
    public abstract int[] applyFilter() throws Exception;

    /**
     * Method to get the Image Width
     *
     * @return
     */
    protected int getImageWidth() {
        return mImageWidth;
    }

    /**
     * Method to get the Image Pixel Array
     *
     * @return
     */
    protected int[] getPixelArray() {
        return mPixelArray;
    }
}
