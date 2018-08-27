package com.mkrworld.libfilter.effect;

public abstract class BaseEffect {

    private int[] mPixelArray;
    private int mImageWidth;

    /**
     * Constructor
     *
     * @param pixelArray Array of Pixel
     * @param imageWidth
     */
    public BaseEffect(int[] pixelArray, int imageWidth) {
        mPixelArray = pixelArray;
        mImageWidth = imageWidth;
    }

    /**
     * Method to apply Effect and Get final Value
     *
     * @return Effected PixelArray
     */
    public abstract int[] applyEffect() throws Exception;

    /**
     * Method to get the Image Width
     *
     * @return
     */
    public int getImageWidth() {
        return mImageWidth;
    }

    /**
     * Method to get the Image Pixel Array
     *
     * @return
     */
    public int[] getPixelArray() {
        return mPixelArray;
    }
}
