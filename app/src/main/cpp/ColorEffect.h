//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_COLOREFFECT_H
#define LIB_FILTER_COLOREFFECT_H

#include "BaseEffect.h"

class ColorEffect : public BaseEffect {

protected:
    JNIEnv *jEnv;
    jintArray srcImageIntArray;
    jintArray destImageIntArray;
    jint imageWidth;
    jint srcImagePixelCount;
    jint *pointerSrcImagePixel;
    jint *pointerDestImagePixel;

    jfloatArray srcMultiplierArray;
    jfloatArray effectMatrixArray;
    jint effectMatrixCount;
    jfloat *pointerMultiplier;
    jfloat *pointerEffectMatrixItem;

    /**
     * Method to initialized the Res. (super) should be called if Override
     */
    void init() {
        srcImagePixelCount = jEnv->GetArrayLength(srcImageIntArray);
        destImageIntArray = jEnv->NewIntArray(srcImagePixelCount);
        pointerSrcImagePixel = jEnv->GetIntArrayElements(srcImageIntArray, NULL);
        pointerDestImagePixel = jEnv->GetIntArrayElements(destImageIntArray, NULL);

        effectMatrixCount = jEnv->GetArrayLength(srcMultiplierArray);
        pointerMultiplier = jEnv->GetFloatArrayElements(srcMultiplierArray, NULL);
        pointerEffectMatrixItem = jEnv->GetFloatArrayElements(effectMatrixArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        jEnv->ReleaseIntArrayElements(srcImageIntArray, pointerSrcImagePixel, 0);
        jEnv->ReleaseIntArrayElements(destImageIntArray, pointerDestImagePixel, 0);

        jEnv->ReleaseFloatArrayElements(srcMultiplierArray, pointerMultiplier, 0);
        jEnv->ReleaseFloatArrayElements(effectMatrixArray, pointerEffectMatrixItem, 0);
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
    ColorEffect(JNIEnv *jEnv,
                jintArray srcImageIntArray,
                jint imageWidth,
                jfloatArray srcMultiplierArray,
                jfloatArray effectMatrixArray) : BaseEffect() {

        this->jEnv = jEnv;
        this->srcImageIntArray = srcImageIntArray;
        this->imageWidth = imageWidth;
        this->srcMultiplierArray = srcMultiplierArray;
        this->effectMatrixArray = effectMatrixArray;
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
            destARGB.alpha = srcARGB.alpha;

            float R = srcARGB.red;
            float G = srcARGB.green;
            float B = srcARGB.blue;

            for (jint matrixIndex = 0; matrixIndex < effectMatrixCount; ++matrixIndex) {
                int offSet = matrixIndex * 20;
                float multiplier = pointerMultiplier[matrixIndex];

                float tR = (((pointerEffectMatrixItem[offSet + 0] * R) +
                             (pointerEffectMatrixItem[offSet + 1] * G) +
                             (pointerEffectMatrixItem[offSet + 2] * B)) * multiplier) +
                           pointerEffectMatrixItem[offSet + 4];

                float tG = (((pointerEffectMatrixItem[offSet + 5] * R) +
                             (pointerEffectMatrixItem[offSet + 6] * G) +
                             (pointerEffectMatrixItem[offSet + 7] * B)) * multiplier) +
                           pointerEffectMatrixItem[offSet + 9];

                float tB = (((pointerEffectMatrixItem[offSet + 10] * R) +
                             (pointerEffectMatrixItem[offSet + 11] * G) +
                             (pointerEffectMatrixItem[offSet + 12] * B)) * multiplier) +
                           pointerEffectMatrixItem[offSet + 14];

                R = tR;
                G = tG;
                B = tB;
            }

            destARGB.red = getColorValue(R);
            destARGB.green = getColorValue(G);
            destARGB.blue = getColorValue(B);
            argbDest[i] = destARGB;
        }
        finish();
        return destImageIntArray;
    }
};

#endif //LIB_FILTER_COLOREFFECT_H
