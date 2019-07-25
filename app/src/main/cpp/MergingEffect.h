//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_MERGINHEFFECT_H
#define LIB_FILTER_MERGINHEFFECT_H

#include "BaseEffect.h"

class MergingEffect : public BaseEffect {

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
    jfloat overlayImageOpacity;

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

public:

    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    MergingEffect(JNIEnv *jEnv,
                  jintArray srcImageIntArray,
                  jint imageWidth,
                  jintArray overlayImageIntArray, jfloat overlayImageOpacity) : BaseEffect() {
        this->jEnv = jEnv;
        this->srcImageIntArray = srcImageIntArray;
        this->imageWidth = imageWidth;

        this->overlayImageIntArray = overlayImageIntArray;
        this->overlayImageOpacity = overlayImageOpacity;
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

        jfloat srcMult = 1.0 - overlayImageOpacity;

        for (int i = 0; i < srcImagePixelCount; ++i) {
            ARGB srcARGB = argbSrc[i];
            ARGB overlayARGB = argbOverlay[i];
            ARGB destARGB = argbDest[i];
            destARGB.alpha = getColorValue(srcARGB.alpha + getFloat(overlayARGB.alpha, overlayImageOpacity));
            destARGB.red = getColorValue(getFloat(srcARGB.red, srcMult) + getFloat(overlayARGB.red, overlayImageOpacity));
            destARGB.green = getColorValue(getFloat(srcARGB.green, srcMult) + getFloat(overlayARGB.green, overlayImageOpacity));
            destARGB.blue = getColorValue(getFloat(srcARGB.blue, srcMult) + getFloat(overlayARGB.blue, overlayImageOpacity));
            argbDest[i] = destARGB;
        }
        finish();
        return destImageIntArray;
    }
};

#endif //LIB_FILTER_ARGB8888MERGINHEFFECT_H
