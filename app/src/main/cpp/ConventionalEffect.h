//
// Created by delhivery on 7/9/18.
//

#ifndef LIB_FILTER_CONVENTIONALEFFECT_H
#define LIB_FILTER_CONVENTIONALEFFECT_H

#include "BaseEffect.h"

class ConventionalEffect : public BaseEffect {

protected:

    JNIEnv *jEnv;
    jintArray srcImageIntArray;
    jintArray destImageIntArray;
    jint imageWidth;
    jint srcImagePixelCount;
    jint *pointerSrcImagePixel;
    jint *pointerDestImagePixel;

    jfloatArray effectMatrixArray;
    jfloat multiplier;
    jfloat thrashHold;
    jfloat *pointerEffectMatrixItem;

    /**
     * Method to initialized the Res. (super) should be called if Override
     */
    void init() {
        srcImagePixelCount = jEnv->GetArrayLength(srcImageIntArray);
        destImageIntArray = jEnv->NewIntArray(srcImagePixelCount);
        pointerSrcImagePixel = jEnv->GetIntArrayElements(srcImageIntArray, NULL);
        pointerDestImagePixel = jEnv->GetIntArrayElements(destImageIntArray, NULL);

        pointerEffectMatrixItem = jEnv->GetFloatArrayElements(effectMatrixArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        jEnv->ReleaseIntArrayElements(srcImageIntArray, pointerSrcImagePixel, 0);
        jEnv->ReleaseIntArrayElements(destImageIntArray, pointerDestImagePixel, 0);

        jEnv->ReleaseFloatArrayElements(effectMatrixArray, pointerEffectMatrixItem, 0);
    }

public:

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
                       jfloat thrashHold,
                       jfloatArray effectMatrixArray) : BaseEffect() {
        this->jEnv = jEnv;
        this->srcImageIntArray = srcImageIntArray;
        this->imageWidth = imageWidth;
        this->thrashHold = thrashHold;
        this->multiplier = multiplier;
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
        jint height = srcImagePixelCount / imageWidth;
        int r = imageWidth - 1;
        int f = imageWidth;
        int l = srcImagePixelCount - imageWidth;
        for (jint index = 0; index < srcImagePixelCount; ++index) {
            ARGB srcARGB11 = argbSrc[index];
            ARGB destARGB = argbDest[index];
            destARGB.alpha = srcARGB11.alpha;
            int mode = (index % imageWidth);

            if (index < f || mode == 0 || mode == r || index > l) {
                argbDest[index] = destARGB;
            } else {
                jfloat matrixValue0 = pointerEffectMatrixItem[0];
                jfloat matrixValue1 = pointerEffectMatrixItem[1];
                jfloat matrixValue2 = pointerEffectMatrixItem[2];
                jfloat matrixValue3 = pointerEffectMatrixItem[3];
                jfloat matrixValue4 = pointerEffectMatrixItem[4];
                jfloat matrixValue5 = pointerEffectMatrixItem[5];
                jfloat matrixValue6 = pointerEffectMatrixItem[6];
                jfloat matrixValue7 = pointerEffectMatrixItem[7];
                jfloat matrixValue8 = pointerEffectMatrixItem[8];

                ARGB srcARGB00 = argbSrc[index - imageWidth - 1];
                ARGB srcARGB01 = argbSrc[index - imageWidth];
                ARGB srcARGB02 = argbSrc[index - imageWidth + 1];
                ARGB srcARGB10 = argbSrc[index - 1];
                ARGB srcARGB12 = argbSrc[index + 1];
                ARGB srcARGB20 = argbSrc[index + imageWidth - 1];
                ARGB srcARGB21 = argbSrc[index + imageWidth];
                ARGB srcARGB22 = argbSrc[index + imageWidth + 1];

                jfloat R = (((srcARGB00.red * matrixValue0) +
                             (srcARGB01.red * matrixValue1) +
                             (srcARGB02.red * matrixValue2) +
                             (srcARGB10.red * matrixValue3) +
                             (srcARGB11.red * matrixValue4) +
                             (srcARGB12.red * matrixValue5) +
                             (srcARGB20.red * matrixValue6) +
                             (srcARGB21.red * matrixValue7) +
                             (srcARGB22.red * matrixValue8)) *
                            multiplier) + thrashHold;

                jfloat G = (((srcARGB00.green * matrixValue0) +
                             (srcARGB01.green * matrixValue1) +
                             (srcARGB02.green * matrixValue2) +
                             (srcARGB10.green * matrixValue3) +
                             (srcARGB11.green * matrixValue4) +
                             (srcARGB12.green * matrixValue5) +
                             (srcARGB20.green * matrixValue6) +
                             (srcARGB21.green * matrixValue7) +
                             (srcARGB22.green * matrixValue8)) *
                            multiplier) + thrashHold;

                jfloat B = (((srcARGB00.blue * matrixValue0) +
                             (srcARGB01.blue * matrixValue1) +
                             (srcARGB02.blue * matrixValue2) +
                             (srcARGB10.blue * matrixValue3) +
                             (srcARGB11.blue * matrixValue4) +
                             (srcARGB12.blue * matrixValue5) +
                             (srcARGB20.blue * matrixValue6) +
                             (srcARGB21.blue * matrixValue7) +
                             (srcARGB22.blue * matrixValue8)) *
                            multiplier) + thrashHold;

                destARGB.red = getColorValue(R);
                destARGB.green = getColorValue(G);
                destARGB.blue = getColorValue(B);
                argbDest[index] = destARGB;
            }
        }
        finish();
        return destImageIntArray;
    }
};

#endif //LIB_FILTER_CONVENTIONALEFFECT_H
