//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_ARGB8888COLOREFFECT_H
#define LIB_FILTER_ARGB8888COLOREFFECT_H

#include "ColorEffect.h"

/**
 * Config for ARGB_8888 Pixel Structure
 */
//typedef struct {
//    uint8_t alpha;
//    uint8_t red;
//    uint8_t green;
//    uint8_t blue;
//} ARGB;

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
        for (int i = 0; i < srcImagePixelCount; ++i) {
            pointerDestImagePixel[i] = setColorEffect(pointerSrcImagePixel[i], effectMatrixCount, pointerEffectMatrixItem, pointerMultiplier);
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
    int getJavaColorValue(float value) {
        if (value > 255.0) {
            return 255;
        } else if (value < 0.0) {
            return 0;
        }
        return (int) value;
    }

    /**
     * Method to get the color value in 0-255 range
    */
    jint getJavaColor(jfloat alpha, jfloat red, jfloat green, jfloat blue) {
        jint a = (getJavaColorValue(alpha) << 24);
        jint r = (getJavaColorValue(red) << 16);
        jint g = (getJavaColorValue(green) << 8);
        jint b = getJavaColorValue(blue);
        return a | r | g | b;
    }

    /**
    * Function to apply Color Effect on a Pixel
    * @param pixel
    * @param pointerMatrixItem
    * @param offSet 1st Index, and count 20 element as this effect element
    * @return New Effected Color
    */
    jint setColorEffect(jint pixel,
                        jint colorMatrixCount,
                        jfloat *pointerMatrixItem,
                        jfloat *pointerMultiplier) {
        jfloat a = (pixel & 0xFF000000) >> 24;
        jfloat R = (pixel & 0x00FF0000) >> 16;
        jfloat G = (pixel & 0x0000FF00) >> 8;
        jfloat B = (pixel & 0x000000FF);

        for (jint matrixIndex = 0; matrixIndex < colorMatrixCount; ++matrixIndex) {
            int offSet = matrixIndex * 20;
            jfloat multiplier = pointerMultiplier[matrixIndex];

            jfloat tempR = getJavaColorValue(
                    (((pointerMatrixItem[offSet + 0] * R) +
                      (pointerMatrixItem[offSet + 1] * G) +
                      (pointerMatrixItem[offSet + 2] * B)) * multiplier) +
                    pointerMatrixItem[offSet + 4]);

            jfloat tempG = getJavaColorValue(
                    (((pointerMatrixItem[offSet + 5] * R) +
                      (pointerMatrixItem[offSet + 6] * G) +
                      (pointerMatrixItem[offSet + 7] * B)) * multiplier) +
                    pointerMatrixItem[offSet + 9]);

            jfloat tempB = getJavaColorValue(
                    (((pointerMatrixItem[offSet + 10] * R) +
                      (pointerMatrixItem[offSet + 11] * G) +
                      (pointerMatrixItem[offSet + 12] * B)) * multiplier) +
                    pointerMatrixItem[offSet + 14]);
            R = tempR;
            G = tempG;
            B = tempB;
        }
        return getJavaColor(a, R, G, B);
    }
};

#endif //LIB_FILTER_ARGB8888COLOREFFECT_H
