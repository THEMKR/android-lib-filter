package com.mkrworld.libfilter.utils;

import com.mkrworld.libfilter.filter.BaseFilter;
import com.mkrworld.libfilter.filter.MultiplierEffect;
import com.mkrworld.libfilter.filter.OverlayEffect;

/**
 * Class To Create the Filter Generator
 */
class FilterCreator {

    private int mImageWidth = 0;
    private float mOffSet = 0F;
    private float mMultiplier = 1F;
    private int[] mSrcImagePixelsArray;
    private int[] mOverlayImagePixelsArray;

    /**
     * Constructor
     *
     * @param builder
     */
    private FilterCreator(Builder builder) {
        mFilter = builder.mFilter;
        mImageWidth = builder.mImageWidth;
        mOffSet = builder.mOffSet;
        mMultiplier = builder.mMultiplier;
        mSrcImagePixelsArray = builder.mSrcImagePixelsArray;
        mOverlayImagePixelsArray = builder.mOverlayImagePixelsArray;
    }

    /**
     * Method to get the Filter Generator
     *
     * @return
     * @throws Exception
     */
    BaseFilter getFilter() throws Exception {
        // VALIDATE BASIC RES USED BY EVERY EFFECT
        if (mFilter == null) {
            throw new Exception("Effect should not be null");
        }
        if (mSrcImagePixelsArray == null || mSrcImagePixelsArray.length == 0) {
            throw new Exception("Plz set a valid Src-Image-Pixel-Array");
        }
        if (mImageWidth == 0 || mSrcImagePixelsArray.length % mImageWidth != 0) {
            throw new Exception("Plz set a valid Image Width");
        }

        // VALIDATE SECONDARY RES BASED ON EFFECT
        switch (mFilter.getFilterCategory()) {
            case COLOR:
                break;
            case CONVENTIONAL:
                break;
            case OVERLAY:
                if (mOverlayImagePixelsArray == null || mOverlayImagePixelsArray.length == 0 || mOverlayImagePixelsArray.length % mImageWidth != 0) {
                    throw new Exception("Plz set a valid Overlay-Image-Pixel-Array");
                }
                break;
            default:
                break;
        }

        // Find the Linked Effect Class
        return getBaseFilter(mFilter);
    }

    /**
     * Method to get the Filter-Generator-Object correspond to the filter set by user
     *
     * @param filter Filter to be set by user
     */
    private BaseFilter getBaseFilter(Filter filter) {
        switch (filter.getFilterCategory()) {
            case COLOR:
                return getColorFilter(filter);
            case CONVENTIONAL:
                return getConventionalFilter(filter);
            case OVERLAY:
                return getOverlayFilter(filter);
            default:
                return new BaseFilter(mSrcImagePixelsArray, mImageWidth) {
                    @Override
                    public int[] applyFilter() throws Exception {
                        return mSrcImagePixelsArray;
                    }
                };
        }
    }

    /**
     * Method to get the Color-Filter-Generator-Object correspond to the filter set by user
     *
     * @param filter Filter to be set by user
     */
    private BaseFilter getColorFilter(Filter filter) {
        switch (filter) {
            case COLOR_RED:
                return new ColorRed(mSrcImagePixelsArray, mImageWidth);
            case COLOR_CONTRAS_BLUE:
                return new ColorContrasBlue(mSrcImagePixelsArray, mImageWidth);
            case COLOR_BLUE:
                return new ColorBlue(mSrcImagePixelsArray, mImageWidth);
            case COLOR_CYAN:
                return new ColorCyan(mSrcImagePixelsArray, mImageWidth);
            case COLOR_GRAY_SCALE:
                return new ColorGrayScale(mSrcImagePixelsArray, mImageWidth);
            case COLOR_GREEN:
                return new ColorGreen(mSrcImagePixelsArray, mImageWidth);
            case COLOR_INVERT:
                return new ColorInvert(mSrcImagePixelsArray, mImageWidth);
            case COLOR_MAGENTA:
                return new ColorMagenta(mSrcImagePixelsArray, mImageWidth);
            case COLOR_PINK:
                return new ColorPink(mSrcImagePixelsArray, mImageWidth);
            case COLOR_SEPIA:
                return new ColorSepia(mSrcImagePixelsArray, mImageWidth);
            case COLOR_VIOLET:
                return new ColorVoilet(mSrcImagePixelsArray, mImageWidth);
            case COLOR_YELLOW:
                return new ColorYellow(mSrcImagePixelsArray, mImageWidth);
            default:
                return new BaseFilter(mSrcImagePixelsArray, mImageWidth) {
                    @Override
                    public int[] applyFilter() throws Exception {
                        return mSrcImagePixelsArray;
                    }
                };
        }
    }

    /**
     * Method to get the Conventional-Filter-Generator-Object correspond to the filter set by user
     *
     * @param filter Filter to be set by user
     */
    private BaseFilter getConventionalFilter(Filter filter) {
        switch (filter) {
            case CONVENTIONAL_SKETCH:
                return new ConventionalSketch(mSrcImagePixelsArray, mImageWidth);
            case CONVENTIONAL_SOLID:
                return new ConventionalSolid(mSrcImagePixelsArray, mImageWidth);
            default:
                return new BaseFilter(mSrcImagePixelsArray, mImageWidth) {
                    @Override
                    public int[] applyFilter() throws Exception {
                        return mSrcImagePixelsArray;
                    }
                };
        }
    }


    /**
     * Method to get the Overlay-Filter-Generator-Object correspond to the filter set by user
     *
     * @param filter Filter to be set by user
     */
    private BaseFilter getOverlayFilter(Filter filter) {
        switch (filter) {
            case OVERLAY:
                return new OverlayEffect(mSrcImagePixelsArray, mImageWidth, mOverlayImagePixelsArray, mMultiplier);
            case OVERLAY_MULTIPLY:
                return new MultiplierEffect(mSrcImagePixelsArray, mImageWidth, mOverlayImagePixelsArray, mMultiplier);
            default:
                return new BaseFilter(mSrcImagePixelsArray, mImageWidth) {
                    @Override
                    public int[] applyFilter() throws Exception {
                        return mSrcImagePixelsArray;
                    }
                };
        }
    }

    /**
     * Class Object used to build the FilterCreator
     */
    public static class Builder {

        private Filter mFilter = null;
        private int mImageWidth = 0;
        private float mOffSet = 0F;
        private float mMultiplier = 1F;
        private int[] mSrcImagePixelsArray;
        private int[] mOverlayImagePixelsArray;

        /**
         * Method to set the Filter to be Applied
         *
         * @param filter
         * @return
         */
        Builder setFilter(Filter filter) {
            mFilter = filter;
            return this;
        }

        /**
         * Method to set the Image Width
         *
         * @param imageWidth
         * @return
         */
        Builder setImageWidth(int imageWidth) {
            mImageWidth = imageWidth;
            return this;
        }

        /**
         * Method to set the Filter Multiplier
         *
         * @param multiplier
         * @return
         */
        Builder setMultiplier(float multiplier) {
            mMultiplier = multiplier;
            return this;
        }

        /**
         * Method to set the Filter OffSet
         *
         * @param offSet
         * @return
         */
        Builder setOffSet(float offSet) {
            mOffSet = offSet;
            return this;
        }

        /**
         * Method to set the Overlay Image Pixel Array in ARGB-8888 Format
         *
         * @param overlayImagePixelsArray
         * @return
         */
        Builder setOverlayImagePixelsArray(int[] overlayImagePixelsArray) {
            mOverlayImagePixelsArray = overlayImagePixelsArray;
            return this;
        }

        /**
         * Method to set the Source Image Pixel Array in ARGB-8888 Format
         *
         * @param srcImagePixelsArray
         * @return
         */
        Builder setSrcImagePixelsArray(int[] srcImagePixelsArray) {
            mSrcImagePixelsArray = srcImagePixelsArray;
            return this;
        }

        /**
         * Method to build the Filter.
         *
         * @return
         */
        FilterCreator build() {
            return new FilterCreator(this);
        }
    }
}
