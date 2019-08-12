//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_OVERLAYEFFECT_H
#define LIB_FILTER_OVERLAYEFFECT_H

#include "BaseEffect.h"

class OverlayEffect : public BaseEffect {

protected:

    JNIEnv *jEnv;
    jintArray srcImageIntArray;
    jintArray destImageIntArray;
    jint imageWidth;
    jint srcImagePixelCount;
    jint *pointerSrcImagePixel;
    jint *pointerDestImagePixel;

    jintArray overlayImageIntArray;
    jint *pointerOverlayImagePixel;
    jfloat multiplier;

    /**
    * Method to initialized the Res. (super) should be called if Override
    */
    void init() {
        srcImagePixelCount = jEnv->GetArrayLength(srcImageIntArray);
        destImageIntArray = jEnv->NewIntArray(srcImagePixelCount);
        pointerSrcImagePixel = jEnv->GetIntArrayElements(srcImageIntArray, NULL);
        pointerDestImagePixel = jEnv->GetIntArrayElements(destImageIntArray, NULL);

        pointerOverlayImagePixel = jEnv->GetIntArrayElements(overlayImageIntArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        jEnv->ReleaseIntArrayElements(srcImageIntArray, pointerSrcImagePixel, 0);
        jEnv->ReleaseIntArrayElements(destImageIntArray, pointerDestImagePixel, 0);

        jEnv->ReleaseIntArrayElements(overlayImageIntArray, pointerOverlayImagePixel, 0);
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
            return getColorValue(2.0 * 255.0 * toFloatBy225(srcColor) * toFloatBy225(overlayColor) * multiplier);
        } else {
            return getColorValue(255.0 * (1.0 - (2.0 * (1.0 - toFloatBy225(srcColor)) * (1.0 - toFloatBy225(overlayColor)))) * multiplier);
        }
    }

public:

    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    OverlayEffect(JNIEnv *jEnv,
                          jintArray srcImageIntArray,
                          jint imageWidth,
                          jintArray overlayImageIntArray,
                          jfloat multiplier) : BaseEffect() {
        this->jEnv = jEnv;
        this->srcImageIntArray = srcImageIntArray;
        this->imageWidth = imageWidth;

        this->overlayImageIntArray = overlayImageIntArray;
        this->multiplier = multiplier;
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
};

#endif //LIB_FILTER_ARGB8888OVERLAYEFFECT_H
