//
// Created by delhivery on 7/9/18.
//

#ifndef LIB_FILTER_CONVENTIONALCOLOREFFECT_H
#define LIB_FILTER_CONVENTIONALCOLOREFFECT_H

#include "BaseEffect.h"

class ConventionalColorEffect : public BaseEffect {

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
    jfloat *pointerEffectMatrixItem;
    jfloatArray srcColorMultiplierArray;
    jfloatArray colorEffectMatrixArray;
    jint colorEffectMatrixCount;
    jfloat *pointerColorMultiplier;
    jfloat *pointerColorEffectMatrixItem;

    /**
     * Method to initialized the Res. (super) should be called if Override
     */
    void init() {
        srcImagePixelCount = jEnv->GetArrayLength(srcImageIntArray);
        destImageIntArray = jEnv->NewIntArray(srcImagePixelCount);
        pointerSrcImagePixel = jEnv->GetIntArrayElements(srcImageIntArray, NULL);
        pointerDestImagePixel = jEnv->GetIntArrayElements(destImageIntArray, NULL);

        pointerEffectMatrixItem = jEnv->GetFloatArrayElements(effectMatrixArray, NULL);
        colorEffectMatrixCount = jEnv->GetArrayLength(srcColorMultiplierArray);
        pointerColorMultiplier = jEnv->GetFloatArrayElements(srcColorMultiplierArray, NULL);
        pointerColorEffectMatrixItem = jEnv->GetFloatArrayElements(colorEffectMatrixArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        jEnv->ReleaseIntArrayElements(srcImageIntArray, pointerSrcImagePixel, 0);
        jEnv->ReleaseIntArrayElements(destImageIntArray, pointerDestImagePixel, 0);

        jEnv->ReleaseFloatArrayElements(effectMatrixArray, pointerEffectMatrixItem, 0);
        jEnv->ReleaseFloatArrayElements(srcColorMultiplierArray, pointerColorMultiplier, 0);
        jEnv->ReleaseFloatArrayElements(colorEffectMatrixArray, pointerColorEffectMatrixItem, 0);
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
    ConventionalColorEffect(JNIEnv *jEnv,
                            jintArray srcImageIntArray,
                            jint imageWidth,
                            jfloat multiplier,
                            jfloatArray effectMatrixArray,
                            jfloatArray srcColorMultiplierArray,
                            jfloatArray colorEffectMatrixArray) : BaseEffect() {
        this->jEnv = jEnv;
        this->srcImageIntArray = srcImageIntArray;
        this->imageWidth = imageWidth;

        this->multiplier = multiplier;
        this->effectMatrixArray = effectMatrixArray;
        this->srcColorMultiplierArray = srcColorMultiplierArray;
        this->colorEffectMatrixArray = colorEffectMatrixArray;
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

        int rightPixIndex = imageWidth - 1;
        int lastRowPixIndex = srcImagePixelCount - imageWidth;

        for (jint index = 0; index < srcImagePixelCount; ++index) {
            ARGB srcARGB = argbSrc[index];
            ARGB destARGB = argbDest[index];
            destARGB.alpha = srcARGB.alpha;
            int mode = (index % imageWidth);
            float R = 0, G = 0, B = 0;
            if (index < imageWidth || mode == 0 || mode == rightPixIndex || index > lastRowPixIndex) {
                R = srcARGB.red;
                G = srcARGB.green;
                B = srcARGB.blue;
            } else {
                float matrixValue = pointerEffectMatrixItem[0];
                ARGB srcARGB00 = argbSrc[index - imageWidth - 1];
                R = R + srcARGB00.red * matrixValue;
                G = G + srcARGB00.green * matrixValue;
                B = B + srcARGB00.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[1];
                ARGB srcARGB01 = argbSrc[index - imageWidth];
                R = R + srcARGB01.red * matrixValue;
                G = G + srcARGB01.green * matrixValue;
                B = B + srcARGB01.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[2];
                ARGB srcARGB02 = argbSrc[index - imageWidth + 1];
                R = R + srcARGB02.red * matrixValue;
                G = G + srcARGB02.green * matrixValue;
                B = B + srcARGB02.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[3];
                ARGB srcARGB10 = argbSrc[index - 1];
                R = R + srcARGB10.red * matrixValue;
                G = G + srcARGB10.green * matrixValue;
                B = B + srcARGB10.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[4];
                R = R + srcARGB.red * matrixValue;
                G = G + srcARGB.green * matrixValue;
                B = B + srcARGB.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[5];
                ARGB srcARGB12 = argbSrc[index + 1];
                R = R + srcARGB12.red * matrixValue;
                G = G + srcARGB12.green * matrixValue;
                B = B + srcARGB12.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[6];
                ARGB srcARGB20 = argbSrc[index + imageWidth - 1];
                R = R + srcARGB20.red * matrixValue;
                G = G + srcARGB20.green * matrixValue;
                B = B + srcARGB20.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[7];
                ARGB srcARGB21 = argbSrc[index + imageWidth];
                R = R + srcARGB21.red * matrixValue;
                G = G + srcARGB21.green * matrixValue;
                B = B + srcARGB21.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[8];
                ARGB srcARGB22 = argbSrc[index + imageWidth + 1];
                R = R + srcARGB22.red * matrixValue;
                G = G + srcARGB22.green * matrixValue;
                B = B + srcARGB22.blue * matrixValue;

                R = R * multiplier;
                G = G * multiplier;
                B = B * multiplier;
            }

            // APPLY COLOR EFFECT
            for (jint matrixIndex = 0; matrixIndex < colorEffectMatrixCount; ++matrixIndex) {
                int offSet = matrixIndex * 20;
                float multiplier = pointerColorMultiplier[matrixIndex];

                float tR = (((pointerColorEffectMatrixItem[offSet + 0] * R) +
                             (pointerColorEffectMatrixItem[offSet + 1] * G) +
                             (pointerColorEffectMatrixItem[offSet + 2] * B)) * multiplier) +
                           pointerColorEffectMatrixItem[offSet + 4];

                float tG = (((pointerColorEffectMatrixItem[offSet + 5] * R) +
                             (pointerColorEffectMatrixItem[offSet + 6] * G) +
                             (pointerColorEffectMatrixItem[offSet + 7] * B)) * multiplier) +
                           pointerColorEffectMatrixItem[offSet + 9];

                float tB = (((pointerColorEffectMatrixItem[offSet + 10] * R) +
                             (pointerColorEffectMatrixItem[offSet + 11] * G) +
                             (pointerColorEffectMatrixItem[offSet + 12] * B)) * multiplier) +
                           pointerColorEffectMatrixItem[offSet + 14];

                R = tR;
                G = tG;
                B = tB;
            }
            destARGB.red = getColorValue(R);
            destARGB.green = getColorValue(G);
            destARGB.blue = getColorValue(B);
            argbDest[index] = destARGB;
        }
        finish();
        return destImageIntArray;
    }
};

#endif //LIB_FILTER_CONVENTIONALCOLOREFFECT_H
