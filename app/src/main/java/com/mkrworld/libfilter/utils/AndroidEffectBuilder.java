package com.mkrworld.libfilter.utils;

import android.graphics.Bitmap;

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
public class AndroidEffectBuilder {

    private Effects mEffect = null;
    private int mImageWidth = 0;
    private float mOffSet = 0F;
    private float mMultiplier = 1F;
    private Bitmap mSrcImage;
    private Bitmap mOverlayImage;

    /**
     * Method to set the Effect to be Applied
     *
     * @param effect
     * @return
     */
    public AndroidEffectBuilder setEffect(Effects effect) {
        mEffect = effect;
        return this;
    }

    /**
     * Method to set the Image Width
     *
     * @param imageWidth
     * @return
     */
    public AndroidEffectBuilder setImageWidth(int imageWidth) {
        mImageWidth = imageWidth;
        return this;
    }

    /**
     * Method to set the Effect Multiplier
     *
     * @param multiplier
     * @return
     */
    public AndroidEffectBuilder setMultiplier(float multiplier) {
        mMultiplier = multiplier;
        return this;
    }

    /**
     * Method to set the Effect OffSet
     *
     * @param offSet
     * @return
     */
    public AndroidEffectBuilder setOffSet(float offSet) {
        mOffSet = offSet;
        return this;
    }

    /**
     * Method to set the Overlay Image
     *
     * @param overlayImage
     * @return
     */
    public AndroidEffectBuilder setOverlayImage(Bitmap overlayImage) {
        mOverlayImage = overlayImage;
        return this;
    }

    /**
     * Method to set the Source Image
     *
     * @param srcImage
     * @return
     */
    public AndroidEffectBuilder setSrcImage(Bitmap srcImage) {
        mSrcImage = srcImage;
        return this;
    }

    /**
     * Method to build the Effect.
     *
     * @return
     */
    public BaseEffect build() throws Exception {
        // VALIDATE BASIC RES USED BY EVERY EFFECT
    }
}
