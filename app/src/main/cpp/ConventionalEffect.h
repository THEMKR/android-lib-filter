//
// Created by delhivery on 7/9/18.
//

#ifndef LIB_FILTER_CONVENTIONALEFFECT_H
#define LIB_FILTER_CONVENTIONALEFFECT_H

#include "BaseEffect.h"

class ConventionalEffect : public BaseEffect {

protected:
    jfloatArray effectMatrixArray;
    jfloat multiplier;
    jfloat *pointerEffectMatrixItem;



    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param multiplier
     * @param effectMatrixArray
     */
    ConventionalEffect(JNIEnv *jEnv,
                jintArray srcImageIntArray,
                jint imageWidth,
                jfloat multiplier,
                jfloatArray effectMatrixArray) : BaseEffect(jEnv,
                                                            srcImageIntArray,
                                                            imageWidth) {
        this->multiplier = multiplier;
        this->effectMatrixArray = effectMatrixArray;
    }

    /**
     * Method to initialized the Res. (super) should be called if Override
     */
    void init() {
        BaseEffect::init();
        pointerEffectMatrixItem = jEnv->GetFloatArrayElements(effectMatrixArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        BaseEffect::finish();
        jEnv->ReleaseFloatArrayElements(effectMatrixArray, pointerEffectMatrixItem, 0);
    }
};

#endif //LIB_FILTER_CONVENTIONALEFFECT_H
