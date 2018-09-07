//
// Created by delhivery on 7/9/18.
//

#ifndef LIB_FILTER_CONVENTIONALCOLOREFFECT_H
#define LIB_FILTER_CONVENTIONALCOLOREFFECT_H

#include "BaseEffect.h"

class ConventionalColorEffect : public BaseEffect {

protected:
    jfloatArray effectMatrixArray;
    jfloat multiplier;
    jfloat *pointerEffectMatrixItem;
    jfloatArray srcColorMultiplierArray;
    jfloatArray colorEffectMatrixArray;
    jint colorEffectMatrixCount;
    jfloat *pointerColorMultiplier;
    jfloat *pointerColorEffectMatrixItem;


    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    ConventionalColorEffect(JNIEnv *jEnv,
                            jintArray srcImageIntArray,
                            jint imageWidth,
                            jfloat multiplier,
                            jfloatArray effectMatrixArray,
                            jfloatArray srcColorMultiplierArray,
                            jfloatArray colorEffectMatrixArray) : BaseEffect(jEnv,
                                                                             srcImageIntArray,
                                                                             imageWidth) {
        this->multiplier = multiplier;
        this->effectMatrixArray = effectMatrixArray;
        this->srcColorMultiplierArray = srcColorMultiplierArray;
        this->colorEffectMatrixArray = colorEffectMatrixArray;
    }

    /**
     * Method to initialized the Res. (super) should be called if Override
     */
    void init() {
        BaseEffect::init();
        pointerEffectMatrixItem = jEnv->GetFloatArrayElements(effectMatrixArray, NULL);
        colorEffectMatrixCount = jEnv->GetArrayLength(srcColorMultiplierArray);
        pointerColorMultiplier = jEnv->GetFloatArrayElements(srcColorMultiplierArray, NULL);
        pointerColorEffectMatrixItem = jEnv->GetFloatArrayElements(colorEffectMatrixArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        BaseEffect::finish();
        jEnv->ReleaseFloatArrayElements(effectMatrixArray, pointerEffectMatrixItem, 0);
        jEnv->ReleaseFloatArrayElements(srcColorMultiplierArray, pointerColorMultiplier, 0);
        jEnv->ReleaseFloatArrayElements(colorEffectMatrixArray, pointerColorEffectMatrixItem, 0);
    }
};

#endif //LIB_FILTER_CONVENTIONALCOLOREFFECT_H
