package com.mkrworld.libfilter.utils;

import com.mkrworld.libfilter.effect.BaseEffect;
import com.mkrworld.libfilter.effect.coloreffect.ColorBlue;
import com.mkrworld.libfilter.effect.coloreffect.ColorContrasBlue;
import com.mkrworld.libfilter.effect.coloreffect.ColorCyan;
import com.mkrworld.libfilter.effect.coloreffect.ColorGrayScale;
import com.mkrworld.libfilter.effect.coloreffect.ColorGreen;
import com.mkrworld.libfilter.effect.coloreffect.ColorInvert;
import com.mkrworld.libfilter.effect.coloreffect.ColorMagenta;
import com.mkrworld.libfilter.effect.coloreffect.ColorPink;
import com.mkrworld.libfilter.effect.coloreffect.ColorRed;
import com.mkrworld.libfilter.effect.coloreffect.ColorSepia;
import com.mkrworld.libfilter.effect.coloreffect.ColorVoilet;
import com.mkrworld.libfilter.effect.coloreffect.ColorYellow;
import com.mkrworld.libfilter.effect.conventionaleffect.ConventionalSketch;
import com.mkrworld.libfilter.enums.EffectCategory;
import com.mkrworld.libfilter.enums.Effects;

/**
 * Class to Build Effect
 */
class EffectBuilder {

    private Effects mEffect = null;
    private int mImageWidth = 0;
    private float mOffSet = 0F;
    private float mMultiplier = 1F;
    private int[] mSrcImagePixelsArray;
    private int[] mOverlayImagePixelsArray;

    /**
     * Method to set the Effect to be Applied
     *
     * @param effect
     * @return
     */
    EffectBuilder setEffect(Effects effect) {
        mEffect = effect;
        return this;
    }

    /**
     * Method to set the Image Width
     *
     * @param imageWidth
     * @return
     */
    EffectBuilder setImageWidth(int imageWidth) {
        mImageWidth = imageWidth;
        return this;
    }

    /**
     * Method to set the Effect Multiplier
     *
     * @param multiplier
     * @return
     */
    EffectBuilder setMultiplier(float multiplier) {
        mMultiplier = multiplier;
        return this;
    }

    /**
     * Method to set the Effect OffSet
     *
     * @param offSet
     * @return
     */
    EffectBuilder setOffSet(float offSet) {
        mOffSet = offSet;
        return this;
    }

    /**
     * Method to set the Overlay Image Pixel Array in ARGB-8888 Format
     *
     * @param overlayImagePixelsArray
     * @return
     */
    EffectBuilder setOverlayImagePixelsArray(int[] overlayImagePixelsArray) {
        mOverlayImagePixelsArray = overlayImagePixelsArray;
        return this;
    }

    /**
     * Method to set the Source Image Pixel Array in ARGB-8888 Format
     *
     * @param srcImagePixelsArray
     * @return
     */
    EffectBuilder setSrcImagePixelsArray(int[] srcImagePixelsArray) {
        mSrcImagePixelsArray = srcImagePixelsArray;
        return this;
    }

    /**
     * Method to build the Effect.
     *
     * @return
     */
    BaseEffect build() throws Exception {
        // VALIDATE BASIC RES USED BY EVERY EFFECT
        if (mEffect == null) {
            throw new Exception("Effect should not be null");
        }
        if (mSrcImagePixelsArray == null || mSrcImagePixelsArray.length == 0) {
            throw new Exception("Plz set a valid Src-Image-Pixel-Array");
        }
        if (mImageWidth == 0 || mSrcImagePixelsArray.length % mImageWidth != 0) {
            throw new Exception("Plz set a valid Image Width");
        }

        // VALIDATE SECONDARY RES BASED ON EFFECT
        switch (getEffectCategory(mEffect)) {
            case COLOR:
                break;
            case CONVENTIONAL:
                break;
            case OVERLAY:
                if (mOverlayImagePixelsArray == null || mOverlayImagePixelsArray.length == 0 || mOverlayImagePixelsArray.length % mImageWidth != 0) {
                    throw new Exception("Plz set a valid Overlay-Image-Pixel-Array");
                }
                break;
            case NON:
            default:
                break;
        }

        // Find the Linked Effect Class
        return getEffect(mEffect);
    }

    /**
     * Method to get the Effect-Generator-Object correspond to the effect set by user
     *
     * @param effects Effect to be set by user
     */
    private BaseEffect getEffect(Effects effects) {
        switch (mEffect) {
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
            case CONVENTIONAL_SKETCH:
                return new ConventionalSketch(mSrcImagePixelsArray, mImageWidth);
            case CONVENTIONAL_SOLID:
            case NON:
            default:
                return new BaseEffect(mSrcImagePixelsArray, mImageWidth) {
                    @Override
                    public int[] applyEffect() throws Exception {
                        return mSrcImagePixelsArray;
                    }
                };
        }
    }

    /**
     * Method to get the Category Of Effect
     *
     * @param effects Effect to be set by user
     */
    private EffectCategory getEffectCategory(Effects effects) {
        switch (mEffect) {
            case COLOR_RED:
            case COLOR_CONTRAS_BLUE:
            case COLOR_BLUE:
            case COLOR_CYAN:
            case COLOR_GRAY_SCALE:
            case COLOR_GREEN:
            case COLOR_INVERT:
            case COLOR_MAGENTA:
            case COLOR_PINK:
            case COLOR_SEPIA:
            case COLOR_VIOLET:
            case COLOR_YELLOW:
                return EffectCategory.COLOR;
            case CONVENTIONAL_SKETCH:
            case CONVENTIONAL_SOLID:
                return EffectCategory.CONVENTIONAL;
            case NON:
            default:
                return EffectCategory.NON;
        }
    }

}
