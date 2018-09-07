//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_ARGB8888COLOREFFECT_H
#define LIB_FILTER_ARGB8888COLOREFFECT_H

#include "ColorEffect.h"

class ARGB8888ColorEffect : public ColorEffect {

public:

    /**
     * Constructor
     * @param jEnv
     * @param srcImageByteArray
     * @param imageWidth
     * @param srcMultiplierArray
     * @param effectMatrixArray
     */
    ARGB8888ColorEffect(JNIEnv *jEnv,
                        jintArray srcImageIntArray,
                        jint imageWidth,
                        jfloatArray srcMultiplierArray,
                        jfloatArray effectMatrixArray) : ColorEffect(jEnv,
                                                                     srcImageIntArray,
                                                                     imageWidth,
                                                                     srcMultiplierArray,
                                                                     effectMatrixArray) {

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

private:

    /**
    * Method to get the color value in 0-255 range
    * @param value
    * @return
    */
    int8_t getColorValue(float value) {
        if (value > 255.0) {
            return (int8_t) 255;
        } else if (value < 0.0) {
            return 0;
        }
        return (int8_t) value;
    }
};

#endif //LIB_FILTER_ARGB8888COLOREFFECT_H
