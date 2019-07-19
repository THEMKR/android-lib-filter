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
    jint effectMatrixSize;
    jint srcImagePixelCount;
    jint *pointerSrcImagePixel;
    jint *pointerDestImagePixel;

    jfloatArray effectMatrixArray;
    jfloat multiplier;
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
        effectMatrixSize = jEnv->GetArrayLength(effectMatrixArray);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        jEnv->ReleaseIntArrayElements(srcImageIntArray, pointerSrcImagePixel, 0);
        jEnv->ReleaseIntArrayElements(destImageIntArray, pointerDestImagePixel, 0);

        jEnv->ReleaseFloatArrayElements(effectMatrixArray, pointerEffectMatrixItem, 0);
    }

    /**
     * Method to apply Effect having 3X3 Matrix
     * @return
     */
    void applyEffect3() {
        ARGB *argbSrc = reinterpret_cast<ARGB *>(pointerSrcImagePixel);
        ARGB *argbDest = reinterpret_cast<ARGB *>(pointerDestImagePixel);
        jint height = srcImagePixelCount / imageWidth;
        int r = imageWidth - 1;
        int f = imageWidth;
        int l = srcImagePixelCount - imageWidth;
        for (jint index = 0; index < srcImagePixelCount; ++index) {
            ARGB srcARGB = argbSrc[index];
            ARGB destARGB = argbDest[index];
            destARGB.alpha = srcARGB.alpha;
            int mode = (index % imageWidth);

            if (index < f || mode == 0 || mode == r || index > l) {
                argbDest[index] = destARGB;
            } else {
                jfloat R = 0, G = 0, B = 0;

                jfloat matrixValue = pointerEffectMatrixItem[0];
                ARGB src = argbSrc[index - imageWidth - 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[1];
                src = argbSrc[index - imageWidth];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[2];
                src = argbSrc[index - imageWidth + 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[3];
                src = argbSrc[index - 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[4];
                R = R + srcARGB.red * matrixValue;
                G = G + srcARGB.green * matrixValue;
                B = B + srcARGB.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[5];
                src = argbSrc[index + 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[6];
                src = argbSrc[index + imageWidth - 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[7];
                src = argbSrc[index + imageWidth];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[8];
                src = argbSrc[index + imageWidth + 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                R = R * multiplier;
                G = G * multiplier;
                B = B * multiplier;

                destARGB.red = getColorValue(R);
                destARGB.green = getColorValue(G);
                destARGB.blue = getColorValue(B);
                argbDest[index] = destARGB;
            }
        }
    }

    /**
     * Method to apply Effect having 3X3 Matrix
     * @return
     */
    void applyEffect5() {
        ARGB *argbSrc = reinterpret_cast<ARGB *>(pointerSrcImagePixel);
        ARGB *argbDest = reinterpret_cast<ARGB *>(pointerDestImagePixel);
        jint height = srcImagePixelCount / imageWidth;
        int r = imageWidth - 2;
        int f = 2 * imageWidth;
        int l = srcImagePixelCount - (2 * imageWidth);

        int wX2 = 2 * imageWidth;

        for (jint index = 0; index < srcImagePixelCount; ++index) {
            ARGB srcARGB = argbSrc[index];
            ARGB destARGB = argbDest[index];
            destARGB.alpha = srcARGB.alpha;
            int mode = (index % imageWidth);

            if (index < f || mode <= 1 || mode == r || index > l) {
                argbDest[index] = destARGB;
            } else {
                jfloat R = 0, G = 0, B = 0;

                // R1
                int rowOffset = 0;
                jfloat matrixValue = pointerEffectMatrixItem[rowOffset];
                ARGB src = argbSrc[index - wX2 - 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 1];
                src = argbSrc[index - wX2 - 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 2];
                src = argbSrc[index - wX2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 3];
                src = argbSrc[index - wX2 + 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 4];
                src = argbSrc[index - wX2 + 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                // R2
                rowOffset = 5;
                matrixValue = pointerEffectMatrixItem[rowOffset];
                src = argbSrc[index - imageWidth - 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 1];
                src = argbSrc[index - imageWidth - 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 2];
                src = argbSrc[index - imageWidth];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 3];
                src = argbSrc[index - imageWidth + 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 4];
                src = argbSrc[index - imageWidth + 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                // R3
                rowOffset = 10;
                matrixValue = pointerEffectMatrixItem[rowOffset];
                src = argbSrc[index - 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 1];
                src = argbSrc[index - 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 2];
                src = argbSrc[index];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 3];
                src = argbSrc[index + 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 4];
                src = argbSrc[index + 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                // R4
                rowOffset = 15;
                matrixValue = pointerEffectMatrixItem[rowOffset];
                src = argbSrc[index + imageWidth - 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 1];
                src = argbSrc[index + imageWidth - 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 2];
                src = argbSrc[index + imageWidth];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 3];
                src = argbSrc[index + imageWidth + 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 4];
                src = argbSrc[index + imageWidth + 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                // R5
                rowOffset = 20;
                matrixValue = pointerEffectMatrixItem[rowOffset];
                src = argbSrc[index + wX2 - 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 1];
                src = argbSrc[index + wX2 - 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 2];
                src = argbSrc[index + wX2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 3];
                src = argbSrc[index + wX2 + 1];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                matrixValue = pointerEffectMatrixItem[rowOffset + 4];
                src = argbSrc[index + wX2 + 2];
                R = R + src.red * matrixValue;
                G = G + src.green * matrixValue;
                B = B + src.blue * matrixValue;

                R = R * multiplier;
                G = G * multiplier;
                B = B * multiplier;

                destARGB.red = getColorValue(R);
                destARGB.green = getColorValue(G);
                destARGB.blue = getColorValue(B);
                argbDest[index] = destARGB;
            }
        }
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
                       jfloatArray effectMatrixArray) : BaseEffect() {
        this->jEnv = jEnv;
        this->srcImageIntArray = srcImageIntArray;
        this->imageWidth = imageWidth;

        this->multiplier = multiplier;
        this->effectMatrixArray = effectMatrixArray;
    }


    /**
     * Method to apply Effect
     * @return
     */
    jintArray applyEffect() {
        init();
        if (effectMatrixSize == 9) {
            applyEffect3();
        } else if (effectMatrixSize == 25) {
            applyEffect5();
        } else {
            applyEffect3();
        }
        finish();
        return destImageIntArray;
    }
};

#endif //LIB_FILTER_CONVENTIONALEFFECT_H
