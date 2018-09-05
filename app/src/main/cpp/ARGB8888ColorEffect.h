//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_ARGB8888COLOREFFECT_H
#define LIB_FILTER_ARGB8888COLOREFFECT_H

#include "ColorEffect.h"

/**
 * Config for ARGB_8888 Pixel Structure
 */
typedef struct {
    uint8_t blue;
    uint8_t green;
    uint8_t red;
    uint8_t alpha;
} ARGB;

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
        ARGB* argbSrc = reinterpret_cast<ARGB *>(pointerSrcImagePixel);
        ARGB* argbDest = reinterpret_cast<ARGB *>(pointerDestImagePixel);

        for (int i = 0; i < srcImagePixelCount; ++i) {
            ARGB srcARGB = argbSrc[i];
            ARGB destARGB = argbDest[i];
            destARGB.alpha = srcARGB.alpha;
            destARGB.red = srcARGB.red;
            destARGB.green = srcARGB.green;
            destARGB.blue = srcARGB.blue;

            for (jint matrixIndex = 0; matrixIndex < effectMatrixCount; ++matrixIndex) {
                int offSet = matrixIndex * 20;
                float multiplier = pointerMultiplier[matrixIndex];

                int8_t R = getColorValue(
                        (((pointerEffectMatrixItem[offSet + 0] * destARGB.red) +
                          (pointerEffectMatrixItem[offSet + 1] * destARGB.green) +
                          (pointerEffectMatrixItem[offSet + 2] * destARGB.blue)) * multiplier) +
                        pointerEffectMatrixItem[offSet + 4]);

                int8_t G = getColorValue(
                        (((pointerEffectMatrixItem[offSet + 5] * destARGB.red) +
                          (pointerEffectMatrixItem[offSet + 6] * destARGB.green) +
                          (pointerEffectMatrixItem[offSet + 7] * destARGB.blue)) * multiplier) +
                        pointerEffectMatrixItem[offSet + 9]);

                int8_t B = getColorValue(
                        (((pointerEffectMatrixItem[offSet + 10] * destARGB.red) +
                          (pointerEffectMatrixItem[offSet + 11] * destARGB.green) +
                          (pointerEffectMatrixItem[offSet + 12] * destARGB.blue)) * multiplier) +
                        pointerEffectMatrixItem[offSet + 14]);

                destARGB.red = R;//getColorValue(R);
                destARGB.green = G;//getColorValue(G);
                destARGB.blue = B;//getColorValue(B);
            }
            argbDest[i]=destARGB;
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
    int getColorValue(float value) {
        if (value > 255.0) {
            return 255;
        } else if (value < 0.0) {
            return 0;
        }
        return (int) value;
    }
};

#endif //LIB_FILTER_ARGB8888COLOREFFECT_H
