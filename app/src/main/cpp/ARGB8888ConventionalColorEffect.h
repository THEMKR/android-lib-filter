//
// Created by delhivery on 7/9/18.
//

#ifndef LIB_FILTER_ARGB8888CONVENTIONALCOLOREFFECT_H
#define LIB_FILTER_ARGB8888CONVENTIONALCOLOREFFECT_H

#include "ConventionalColorEffect.h"

class ARGB8888ConventionalColorEffect : public ConventionalColorEffect {

public:

    /**
     * Constructor
    * @param jEnv
    * @param srcImageByteArray
    * @param imageWidth
    * @param multiplier
    * @param effectMatrixArray
    */
    ARGB8888ConventionalColorEffect(JNIEnv *jEnv,
                                    jintArray srcImageIntArray,
                                    jint imageWidth,
                                    jfloat multiplier,
                                    jfloatArray effectMatrixArray,
                                    jfloatArray srcColorMultiplierArray,
                                    jfloatArray colorEffectMatrixArray) : ConventionalColorEffect(jEnv,
                                                                                                  srcImageIntArray,
                                                                                                  imageWidth,
                                                                                                  multiplier,
                                                                                                  effectMatrixArray,
                                                                                                  srcColorMultiplierArray,
                                                                                                  colorEffectMatrixArray) {
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


private:

    /**
    * Method to get the color value in 0-255 range
    * @param value
    * @return
    */
    uint8_t getColorValue(float value) {
        if (value > 255.0) {
            return (uint8_t)255;
        } else if (value < 0.0) {
            return 0;
        }
        return (uint8_t) value;
    }
};
#endif //LIB_FILTER_ARGB8888CONVENTIONALCOLOREFFECT_H
