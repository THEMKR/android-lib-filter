//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_COLOREFFECT_H
#define LIB_FILTER_COLOREFFECT_H

#include "BaseEffect.h"

class ColorEffect : public BaseEffect {

protected:
    jfloatArray srcMultiplierArray;
    jfloatArray effectMatrixArray;
    jint effectMatrixCount;
    jfloat *pointerMultiplier;
    jfloat *pointerEffectMatrixItem;



    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    ColorEffect(JNIEnv *jEnv,
                jintArray srcImageIntArray,
                jint imageWidth,
                jfloatArray srcMultiplierArray,
                jfloatArray effectMatrixArray) : BaseEffect(jEnv,
                                                            srcImageIntArray,
                                                            imageWidth) {
        this->srcMultiplierArray = srcMultiplierArray;
        this->effectMatrixArray = effectMatrixArray;
    }

    /**
     * Method to initialized the Res. (super) should be called if Override
     */
    void init() {
        BaseEffect::init();
        effectMatrixCount = jEnv->GetArrayLength(srcMultiplierArray);
        pointerMultiplier = jEnv->GetFloatArrayElements(srcMultiplierArray, NULL);
        pointerEffectMatrixItem = jEnv->GetFloatArrayElements(effectMatrixArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        BaseEffect::finish();
        jEnv->ReleaseFloatArrayElements(srcMultiplierArray, pointerMultiplier, 0);
        jEnv->ReleaseFloatArrayElements(effectMatrixArray, pointerEffectMatrixItem, 0);
    }
};

#endif //LIB_FILTER_COLOREFFECT_H
