//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_COLOREFFECT_H
#define LIB_FILTER_COLOREFFECT_H

#include "BaseEffect.h"

class ColorEffect : BaseEffect {

private:
    jfloatArray srcMultiplierArray;
    jfloatArray effectMatrixArray;
    jint effectMatrixCount;
    jfloat *pointerMultiplier;
    jfloat *pointerEffectMatrixItem;

protected:
    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    ColorEffect(JNIEnv *jEnv, jintArray srcImageByteArray, jint imageWidth,
                jfloatArray srcMultiplierArray, jfloatArray effectMatrixArray) : BaseEffect(jEnv,
                                                                                            srcImageByteArray,
                                                                                            imageWidth) {
        this->srcMultiplierArray = srcMultiplierArray;
        this->effectMatrixArray = effectMatrixArray;
    }

public:

    /**
     * Method to initialized the Res. (super) should be called if Override
     */
    void init() {
        BaseEffect::init();
        effectMatrixCount = getJNIEnv()->GetArrayLength(srcMultiplierArray);
        pointerMultiplier = getJNIEnv()->GetFloatArrayElements(srcMultiplierArray, NULL);
        pointerEffectMatrixItem = getJNIEnv()->GetFloatArrayElements(effectMatrixArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        BaseEffect::finish();
        getJNIEnv()->ReleaseFloatArrayElements(srcMultiplierArray, pointerMultiplier, 0);
        getJNIEnv()->ReleaseFloatArrayElements(effectMatrixArray, pointerEffectMatrixItem, 0);
    }

    /**
     * Method to get the Number of effect Applied on the Image
     * @return
     */
    jint getEffectCount() { return effectMatrixCount; }

    /**
     * Method to get the Pointer of Multiplier List
     * @return
     */
    jfloat *getPointerMultiplier() { return pointerMultiplier; }

    /**
     * Method to get the Pointer of Effect Matrix
     * @return
     */
    jfloat *getPointerEffectMatrixItem() { return pointerEffectMatrixItem; }
};

#endif //LIB_FILTER_COLOREFFECT_H
