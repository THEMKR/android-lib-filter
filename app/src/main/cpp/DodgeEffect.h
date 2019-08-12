//
// Created by administrator on 15/7/19.
//

#ifndef LIB_FILTER_DODGEEFFECT_H
#define LIB_FILTER_DODGEEFFECT_H

#include "BaseEffect.h"

class DodgeEffect : public BaseEffect {

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
     * Method to get the Multiply COlor
     * @param front
     * @param back
     * @return
     */
    uint8_t getDodgeColor(uint8_t front, uint8_t back) {
        float f = toFloat(front);
        float b = 1.0 - toFloatBy225(back);
        float result = f / b;
        if (result >= 255.0) {
            return getColorValue(255.0);
        } else if (result < 0.0) {
            return getColorValue(0.0);
        }
        return getColorValue(result);
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
    DodgeEffect(JNIEnv *jEnv,
                jintArray srcImageIntArray,
                jint imageWidth,
                jintArray overlayImageIntArray) : BaseEffect() {
        this->jEnv = jEnv;
        this->srcImageIntArray = srcImageIntArray;
        this->imageWidth = imageWidth;

        this->overlayImageIntArray = overlayImageIntArray;
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
            destARGB.alpha = getDodgeColor(srcARGB.alpha, overlayARGB.alpha);
            destARGB.red = getDodgeColor(srcARGB.red, overlayARGB.red);
            destARGB.green = getDodgeColor(srcARGB.green, overlayARGB.green);
            destARGB.blue = getDodgeColor(srcARGB.blue, overlayARGB.blue);
            argbDest[i] = destARGB;
        }
        finish();
        return destImageIntArray;
    }
};

#endif //LIB_FILTER_DODGEEFFECT_H
