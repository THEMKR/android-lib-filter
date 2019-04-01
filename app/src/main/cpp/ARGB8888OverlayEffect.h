//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_ARGB8888OVERLAYEFFECT_H
#define LIB_FILTER_ARGB8888OVERLAYEFFECT_H

#include "OverlayEffect.h"
#include "BaseEffect.h"

class ARGB8888OverlayEffect : public OverlayEffect {

public:

    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    ARGB8888OverlayEffect(JNIEnv *jEnv,
                          jintArray srcImageIntArray,
                          jint imageWidth,
                          jintArray overlayImageIntArray,
                          jfloat multiplier) : OverlayEffect(jEnv,
                                                             srcImageIntArray,
                                                             imageWidth,
                                                             overlayImageIntArray,
                                                             multiplier) {

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

        for (int i = 0; i < srcImagePixelCount; ++i) {
            ARGB srcARGB = argbSrc[i];
            ARGB overlayARGB = argbOverlay[i];
            ARGB destARGB = argbDest[i];
            destARGB.alpha = getOverlayColor(srcARGB.alpha, overlayARGB.alpha);
            destARGB.red = getOverlayColor(srcARGB.red, overlayARGB.red);
            destARGB.green = getOverlayColor(srcARGB.green, overlayARGB.green);
            destARGB.blue = getOverlayColor(srcARGB.blue, overlayARGB.blue);
            argbDest[i] = destARGB;
        }
        finish();
        return destImageIntArray;
    }

private:

    /**
     * Method to get the Overlay COlor
     * @param srcColor
     * @param overlayColor
     * @return
     */
    uint8_t getOverlayColor(uint8_t srcColor, uint8_t overlayColor) {
        if (srcColor < 128) {
            return getColorValue(2.0 * 255.0 * getFloat1(srcColor) * getFloat1(overlayColor) * multiplier);
        } else {
            return getColorValue(255.0 * (1.0 - (2.0 * (1.0 - getFloat1(srcColor)) * (1.0 - getFloat1(overlayColor)))) * multiplier);
        }
    }

    /**
    * Method to get the color value in 0-1 range
    */
    float getFloat1(uint8_t value) {
        return (float) value / 255.0;
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

#endif //LIB_FILTER_ARGB8888OVERLAYEFFECT_H
