//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_ARGB8888MULTIPLYEFFECT_H
#define LIB_FILTER_ARGB8888MULTIPLYEFFECT_H

#include "OverlayEffect.h"
#include "BaseEffect.h"

class ARGB8888MultiplyEffect : public OverlayEffect {

public:

    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    ARGB8888MultiplyEffect(JNIEnv *jEnv,
                           jintArray srcImageIntArray,
                           jint imageWidth,
                           jintArray overlayImageIntArray, jfloat multiplier) : OverlayEffect(jEnv,
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
        ARGB *argbDest = reinterpret_cast<ARGB *>(pointerDestImagePixel);

        for (int i = 0; i < srcImagePixelCount; ++i) {
            ARGB srcARGB = argbSrc[i];
            ARGB destARGB = argbDest[i];
            destARGB.alpha = getMultiplyColor(srcARGB.alpha, destARGB.alpha);
            destARGB.red = getMultiplyColor(srcARGB.red, destARGB.red);
            destARGB.green = getMultiplyColor(srcARGB.green, destARGB.green);
            destARGB.blue = getMultiplyColor(srcARGB.blue, destARGB.blue);
            argbDest[i] = destARGB;
        }
        finish();
        return destImageIntArray;
    }

private:

    /**
     * Method to get the Multiply COlor
     * @param srcColor
     * @param overlayColor
     * @return
     */
    int8_t getMultiplyColor(int8_t srcColor, int8_t overlayColor) {
        return getColorValue(255 * getFloat1(srcColor) * getFloat1(overlayColor) * multiplier);
    }

    /**
    * Method to get the color value in 0-1 range
    */
    float getFloat1(int8_t value) {
        return (float) value / 255.0;
    }


    /**
    * Method to get the color value in 0-255 range
    * @param value
    * @return
    */
    int8_t getColorValue(float value) {
        if (value > 255.0) {
            return (int8_t) 255;
        } else if (value < 0.0) {
            return 0;
        }
        return (int8_t) value;
    }
};

#endif //LIB_FILTER_ARGB8888MULTIPLYEFFECT_H
