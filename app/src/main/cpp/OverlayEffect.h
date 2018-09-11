#ifndef LIB_FILTER_OVERLAYEFFECT_H
#define LIB_FILTER_OVERLAYEFFECT_H

#include <jni.h>
#include "BaseEffect.h"


class OverlayEffect : public BaseEffect {

protected:
    jintArray overlayImageIntArray;
    jintArray destImageIntArray;
    jint *pointerOverlayImagePixel;
    jint *pointerDestImagePixel;
    jfloat multiplier;

    /**
    * Constructor
    * @param jEnv
    * @param srcImageByteArray
    * @param imageWidth
    * @param overlayImageIntArray
    */
    OverlayEffect(JNIEnv *jEnv, jintArray srcImageIntArray, jint imageWidth, jintArray overlayImageIntArray, jfloat multiplier) : BaseEffect(jEnv, srcImageIntArray, imageWidth) {
        this->overlayImageIntArray = srcImageIntArray;
        this->multiplier=multiplier;
    }

    /**
    * Method to initialized the Res. (super) should be called if Override
    */
    void init() {
        BaseEffect::init();
        destImageIntArray = jEnv->NewIntArray(srcImagePixelCount);
        pointerOverlayImagePixel = jEnv->GetIntArrayElements(overlayImageIntArray, NULL);
        pointerDestImagePixel = jEnv->GetIntArrayElements(destImageIntArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        BaseEffect::finish();
        jEnv->ReleaseIntArrayElements(overlayImageIntArray, pointerOverlayImagePixel, 0);
        jEnv->ReleaseIntArrayElements(destImageIntArray, pointerDestImagePixel, 0);
    }
};

#endif //LIB_FILTER_OVERLAYEFFECT_H
