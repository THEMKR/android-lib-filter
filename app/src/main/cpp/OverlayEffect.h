#ifndef LIB_FILTER_OVERLAYEFFECT_H
#define LIB_FILTER_OVERLAYEFFECT_H

#include <jni.h>
#include "BaseEffect.h"


class OverlayEffect : public BaseEffect {

protected:
    jintArray overlayImageIntArray;
    jint *pointerOverlayImagePixel;
    jfloat multiplier;

    /**
    * Constructor
    * @param jEnv
    * @param srcImageByteArray
    * @param imageWidth
    * @param overlayImageIntArray
    */
    OverlayEffect(JNIEnv *jEnv, jintArray srcImageIntArray, jint imageWidth, jintArray overlayImageIntArray, jfloat multiplier) : BaseEffect(jEnv, srcImageIntArray, imageWidth) {
        this->overlayImageIntArray = overlayImageIntArray;
        this->multiplier=multiplier;
    }

    /**
    * Method to initialized the Res. (super) should be called if Override
    */
    void init() {
        BaseEffect::init();
        pointerOverlayImagePixel = jEnv->GetIntArrayElements(overlayImageIntArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        BaseEffect::finish();
        jEnv->ReleaseIntArrayElements(overlayImageIntArray, pointerOverlayImagePixel, 0);
    }
};

#endif //LIB_FILTER_OVERLAYEFFECT_H
