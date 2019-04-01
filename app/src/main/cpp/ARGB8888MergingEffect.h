//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_ARGB8888MERGINHEFFECT_H
#define LIB_FILTER_ARGB8888MERGINHEFFECT_H

#include "OverlayEffect.h"
#include "BaseEffect.h"

class ARGB8888MergingEffect : public OverlayEffect {

public:

    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    ARGB8888MergingEffect(JNIEnv *jEnv,
                           jintArray srcImageIntArray,
                           jint imageWidth,
                           jintArray overlayImageIntArray, jfloat overlayImageOpacity) : OverlayEffect(jEnv,
                                                                                                       srcImageIntArray,
                                                                                                       imageWidth,
                                                                                                       overlayImageIntArray,
                                                                                                       overlayImageOpacity) {

    }

    /**
     * Method to apply Effect
     * @return
     */
    jintArray applyEffect() {
        init();
        ARGB *argbSrc = reinterpret_cast<ARGB *>(pointerSrcImagePixel);
        ARGB *argbOverlay = reinterpret_cast<ARGB *>(pointerOverlayImagePixel);
        ARGB *argbDest = reinterpret_cast<ARGB *>(pointerDestImagePixel);

        jfloat srcMult = 1.0 - multiplier;

        for (int i = 0; i < srcImagePixelCount; ++i) {
            ARGB srcARGB = argbSrc[i];
            ARGB overlayARGB = argbOverlay[i];
            ARGB destARGB = argbDest[i];
            destARGB.alpha = getColorValue(srcARGB.alpha + getFloat(overlayARGB.alpha, multiplier));
            destARGB.red = getColorValue(getFloat(srcARGB.red, srcMult) + getFloat(overlayARGB.red, multiplier));
            destARGB.green = getColorValue(getFloat(srcARGB.green, srcMult) + getFloat(overlayARGB.green, multiplier));
            destARGB.blue = getColorValue(getFloat(srcARGB.blue, srcMult) + getFloat(overlayARGB.blue, multiplier));
            argbDest[i] = destARGB;
        }
        finish();
        return destImageIntArray;
    }

private:

    /**
    * Method to get the color value in 0-1 range
    */
    float getFloat(uint8_t value, float mult) {
        return (float) value * mult;
    }


    /**
    * Method to get the color value in 0-255 range
    * @param value
    * @return
    */
    uint8_t getColorValue(float value) {
        if (value > 255.0) {
            return (uint8_t) 255;
        } else if (value < 0.0) {
            return 0;
        }
        return (uint8_t) value;
    }
};

#endif //LIB_FILTER_ARGB8888MERGINHEFFECT_H
