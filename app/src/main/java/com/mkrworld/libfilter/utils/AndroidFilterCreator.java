package com.mkrworld.libfilter.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.mkrworld.libfilter.enums.Filter;

/**
 * Class to Build Filter
 */
public class AndroidFilterCreator {

    private Filter mFilter = null;
    private float mOffSet = 0F;
    private float mMultiplier = 1F;
    private Bitmap mSrcImage;
    private Bitmap mOverlayImage;

    /**
     * Constructor
     *
     * @param builder
     */
    private AndroidFilterCreator(Builder builder) {
        mFilter = builder.mFilter;
        mOffSet = builder.mOffSet;
        mMultiplier = builder.mMultiplier;
        mSrcImage = builder.mSrcImage;
        mOverlayImage = builder.mOverlayImage;
    }

    /**
     * Method to Create Filtered Bitmap.<br>Method to Run on caller Thread.
     *
     * @return Filtered Bitmap
     */
    public Bitmap createFilteredBitmap() throws Exception {
        FilterCreator.Builder filterBuilder = new FilterCreator.Builder();
        // VALIDATE RES
        if (mFilter == null) {
            throw new Exception("Filter should not be null");
        }
        // SET SRC BITMAP
        if (mSrcImage == null || mSrcImage.isRecycled()) {
            throw new Exception("Plz set a valid Src-Image");
        }
        // VALIDATE SECONDARY RES BASED ON FILTER
        switch (Utils.getFilterCategory(mFilter)) {
            case OVERLAY:
                // SET OVERLAY BITMAP
                if (mOverlayImage == null || mOverlayImage.isRecycled()) {
                    throw new Exception("Plz set a valid Overlay-Image");
                }
                filterBuilder.setOverlayImagePixelsArray(convertBitmapIntoPixelArray(getARGB888Image(mOverlayImage, mSrcImage.getWidth(), mSrcImage.getHeight())));
                break;
        }
        filterBuilder.setFilter(mFilter);
        filterBuilder.setImageWidth(mSrcImage.getWidth());
        filterBuilder.setSrcImagePixelsArray(convertBitmapIntoPixelArray(getARGB888Image(mSrcImage, mSrcImage.getWidth(), mSrcImage.getHeight())));
        // SET OFFSET
        filterBuilder.setOffSet(mOffSet);
        // SET MULTIPLIER
        filterBuilder.setMultiplier(mMultiplier);
        // Apply Filter and Create a New Filtered Bitmap
        return convertPixelArrayIntoBitmap(filterBuilder.build().getFilterGenerator().applyFilter(), mSrcImage.getWidth(), mSrcImage.getHeight());
    }

    /**
     * Method to convert Bitmap into ARGB_8888 format.
     *
     * @param bitmap
     * @param destWidth   New dest width of the Bitmap
     * @param destHeighgt New dest height of the Bitmap
     */
    private static Bitmap getARGB888Image(Bitmap bitmap, int destWidth, int destHeighgt) {
        if (bitmap.getConfig().equals(Bitmap.Config.ARGB_8888) && bitmap.getWidth() == destWidth && bitmap.getHeight() == destHeighgt) {
            return bitmap;
        }
        Bitmap newARGBBitmap = Bitmap.createBitmap(destWidth, destHeighgt, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newARGBBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, null, new RectF(0, 0, destWidth, destHeighgt), paint);
        return newARGBBitmap;
    }

    /**
     * Method ot convert the Bitmap into INT[] pixel Array
     *
     * @param bitmap
     * @return
     */
    private int[] convertBitmapIntoPixelArray(Bitmap bitmap) {
        int[] bitmapPixelArray = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(bitmapPixelArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return bitmapPixelArray;
    }

    /**
     * Method to convert the Pixel Array Into the Bitmap
     *
     * @param bitmapPixelArray
     * @param width            Width of the Bitmap
     * @param height           Height of the Bitmap
     * @return
     */
    private Bitmap convertPixelArrayIntoBitmap(int[] bitmapPixelArray, int width, int height) {
        Bitmap filteredBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        filteredBitmap.setPixels(bitmapPixelArray, 0, width, 0, 0, width, height);
        return filteredBitmap;
    }

    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================

    /**
     * Class Object used to build the AndroidFilterCreator
     */
    public static class Builder {

        private Filter mFilter = null;
        private float mOffSet = 0F;
        private float mMultiplier = 1F;
        private Bitmap mSrcImage;
        private Bitmap mOverlayImage;

        /**
         * Method to set the Filter to be Applied
         *
         * @param filter
         * @return
         */
        public Builder setFilter(Filter filter) {
            mFilter = filter;
            return this;
        }

        /**
         * Method to set the Filter Multiplier
         *
         * @param multiplier
         * @return
         */
        public Builder setMultiplier(float multiplier) {
            mMultiplier = multiplier;
            return this;
        }

        /**
         * Method to set the Filter OffSet
         *
         * @param offSet
         * @return
         */
        public Builder setOffSet(float offSet) {
            mOffSet = offSet;
            return this;
        }

        /**
         * Method to set the Overlay Image
         *
         * @param overlayImage
         * @return
         */
        public Builder setOverlayImage(Bitmap overlayImage) {
            mOverlayImage = overlayImage;
            return this;
        }

        /**
         * Method to set the Source Image
         *
         * @param srcImage
         * @return
         */
        public Builder setSrcImage(Bitmap srcImage) {
            mSrcImage = srcImage;
            return this;
        }

        /**
         * Method to build the Filter Bitmap.<BR>
         * Method is called on Caller Thread
         *
         * @return Bitmap after apply Filter set by User
         */
        public AndroidFilterCreator build() {
            return new AndroidFilterCreator(this);
        }
    }
}
