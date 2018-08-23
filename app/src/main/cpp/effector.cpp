#include <jni.h>
#include <stdint.h>

/**
 * Method to get the color value in 0-255 range
 * @param value
 * @return
 */
jint getJavaColorValue(jfloat value) {
    if (value > 255.0) {
        return 255;
    } else if (value < 0.0) {
        return 0;
    }
    return (jint) value;
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
jint setColorEffect(jint pixel, jfloat *pointerMatrixItem, int offSet, jfloat multiplier) {
    jfloat a = (pixel & 0xFF000000) >> 24;
    jfloat r = (pixel & 0x00FF0000) >> 16;
    jfloat g = (pixel & 0x0000FF00) >> 8;
    jfloat b = (pixel & 0x000000FF);

    jfloat R = getJavaColorValue(
            (((pointerMatrixItem[offSet + 0] * r) +
              (pointerMatrixItem[offSet + 1] * g) +
              (pointerMatrixItem[offSet + 2] * b)) * multiplier) +
            pointerMatrixItem[offSet + 4]);
    jfloat G = getJavaColorValue(
            (((pointerMatrixItem[offSet + 5] * r) +
              (pointerMatrixItem[offSet + 6] * g) +
              (pointerMatrixItem[offSet + 7] * b)) * multiplier) +
            pointerMatrixItem[offSet + 9]);
    jfloat B = getJavaColorValue(
            (((pointerMatrixItem[offSet + 10] * r) +
              (pointerMatrixItem[offSet + 11] * g) +
              (pointerMatrixItem[offSet + 12] * b)) * multiplier) +
            pointerMatrixItem[offSet + 14]);
    return getJavaColor(a, R, G, B);
}

/**
 * Function to apply Color Effect on a Pixel
 * @param pixel
 * @param pointerMatrixItem
 * @param offSet 1st Index, and count 20 element as this effect element
 * @return New Effected Color
 */
jint setColorEffect(jint pixel, jfloat *pointerMatrixItem, int colorMatrixCount,
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

/**
 * Function to apply Conventional Effect on a Pixel
 * @param pixel
 * @param pointerMatrixItem
 * @param offSet 1st Index, and count 20 element as this effect element
 * @return
 */
jint setConventionalEffect(int index, jint *pointerPixel, jfloat *pointerMatrixItem, int offSet,
                           jfloat multiplier, int width) {
    jfloat A = 0, R = 0, G = 0, B = 0;

    jfloat matrixValue = pointerMatrixItem[offSet + 0];
    jint pixel00 = pointerPixel[index - width - 1];
    R = R + (((jfloat) ((pixel00 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel00 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel00 & 0x000000FF)) * matrixValue);

    matrixValue = pointerMatrixItem[offSet + 1];
    jint pixel01 = pointerPixel[index - width];
    R = R + (((jfloat) ((pixel01 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel01 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel01 & 0x000000FF)) * matrixValue);

    matrixValue = pointerMatrixItem[offSet + 2];
    jint pixel02 = pointerPixel[index - width + 1];
    R = R + (((jfloat) ((pixel02 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel02 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel02 & 0x000000FF)) * matrixValue);

    matrixValue = pointerMatrixItem[offSet + 3];
    jint pixel10 = pointerPixel[index - 1];
    R = R + (((jfloat) ((pixel10 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel10 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel10 & 0x000000FF)) * matrixValue);

    matrixValue = pointerMatrixItem[offSet + 4];
    jint pixel11 = pointerPixel[index];
    R = R + (((jfloat) ((pixel11 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel11 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel11 & 0x000000FF)) * matrixValue);
    A = (pixel11 & 0xFF000000) >> 24;

    matrixValue = pointerMatrixItem[offSet + 5];
    jint pixel12 = pointerPixel[index + 1];
    R = R + (((jfloat) ((pixel12 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel12 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel12 & 0x000000FF)) * matrixValue);

    matrixValue = pointerMatrixItem[offSet + 6];
    jint pixel20 = pointerPixel[index + width - 1];
    R = R + (((jfloat) ((pixel20 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel20 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel20 & 0x000000FF)) * matrixValue);

    matrixValue = pointerMatrixItem[offSet + 7];
    jint pixel21 = pointerPixel[index + width];
    R = R + (((jfloat) ((pixel21 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel21 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel21 & 0x000000FF)) * matrixValue);

    matrixValue = pointerMatrixItem[offSet + 8];
    jint pixel22 = pointerPixel[index + width + 1];
    R = R + (((jfloat) ((pixel22 & 0x00FF0000) >> 16)) * matrixValue);
    G = G + (((jfloat) ((pixel22 & 0x0000FF00) >> 8)) * matrixValue);
    B = B + (((jfloat) (pixel22 & 0x000000FF)) * matrixValue);

    R = R * multiplier;
    G = G * multiplier;
    B = B * multiplier;
    return getJavaColor(A, R, G, B);
}

extern "C" JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setColorEffect(JNIEnv *jEnv, jclass type,
                                                              jintArray imagePixelArray,
                                                              jfloat multiplier,
                                                              jfloatArray effectMatrix) {

    jfloat *pointerEffectMatrix = jEnv->GetFloatArrayElements(effectMatrix, 0);
    jint imagePixelCount = jEnv->GetArrayLength(imagePixelArray);
    jint *pointerImagePixel = jEnv->GetIntArrayElements(imagePixelArray, NULL);
    jintArray effectedPixelArray = jEnv->NewIntArray(imagePixelCount);
    jint *pointerEffectedPixel = jEnv->GetIntArrayElements(effectedPixelArray, NULL);

    for (jint index = 0; index < imagePixelCount; ++index) {
        pointerEffectedPixel[index] = setColorEffect(pointerImagePixel[index], pointerEffectMatrix,
                                                     0, multiplier);
    }

    jEnv->ReleaseIntArrayElements(effectedPixelArray, pointerEffectedPixel, 0);
    jEnv->ReleaseIntArrayElements(imagePixelArray, pointerImagePixel, 0);
    jEnv->ReleaseFloatArrayElements(effectMatrix, pointerEffectMatrix, 0);
    return effectedPixelArray;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setMultiColorEffect(JNIEnv *jEnv, jclass type,
                                                                   jintArray imagePixelArray,
                                                                   jint imageWidth,
                                                                   jfloatArray colorEffectMultiplierArray,
                                                                   jfloatArray colorEffectMatrixItemArray) {

    jint imagePixelCount = jEnv->GetArrayLength(imagePixelArray);
    jint colorMatrixCount = jEnv->GetArrayLength(colorEffectMultiplierArray);
    jintArray effectedPixelArray = jEnv->NewIntArray(imagePixelCount);

    jfloat *pointerMultiplier = jEnv->GetFloatArrayElements(colorEffectMultiplierArray, 0);
    jfloat *pointerMatrixItem = jEnv->GetFloatArrayElements(colorEffectMatrixItemArray, 0);
    jint *pointerPixel = jEnv->GetIntArrayElements(imagePixelArray, NULL);
    jint *effectedPointerPixel = jEnv->GetIntArrayElements(effectedPixelArray, NULL);

    for (jint index = 0; index < imagePixelCount; ++index) {
        effectedPointerPixel[index] = setColorEffect(pointerPixel[index], pointerMatrixItem,
                                                     colorMatrixCount,
                                                     pointerMultiplier);
    }

    jEnv->ReleaseFloatArrayElements(colorEffectMultiplierArray, pointerMultiplier, 0);
    jEnv->ReleaseFloatArrayElements(colorEffectMatrixItemArray, pointerMatrixItem, 0);
    jEnv->ReleaseIntArrayElements(imagePixelArray, pointerPixel, NULL);
    jEnv->ReleaseIntArrayElements(effectedPixelArray, effectedPointerPixel, NULL);
    return effectedPixelArray;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setConventionalEffect(JNIEnv *jEnv, jclass type,
                                                                     jintArray imagePixelArray,
                                                                     jint imageWidth,
                                                                     jfloat multiplier,
                                                                     jfloatArray effectMatrix) {

    jfloat *pointerEffectMatrix = jEnv->GetFloatArrayElements(effectMatrix, 0);
    jint pixelCount = jEnv->GetArrayLength(imagePixelArray);
    jint *pointerPixel = jEnv->GetIntArrayElements(imagePixelArray, NULL);
    jintArray effectedPixelArray = jEnv->NewIntArray(pixelCount);
    jint *pointerEffectedPixel = jEnv->GetIntArrayElements(effectedPixelArray, NULL);

    jint height = pixelCount / imageWidth;
    for (int imageY = 0; imageY < height; ++imageY) {
        for (int imageX = 0; imageX < imageWidth; ++imageX) {
            int index = (imageY * imageWidth) + imageX;
            if (imageY == 0 || imageY == (height - 1) || imageX == 0 ||
                imageX == (imageWidth - 1)) {
                pointerEffectedPixel[index] = pointerPixel[index];
            } else {
                pointerEffectedPixel[index] = setConventionalEffect(index, pointerPixel,
                                                                    pointerEffectMatrix, 0,
                                                                    multiplier, imageWidth);
            }
        }
    }
    jEnv->ReleaseIntArrayElements(effectedPixelArray, pointerEffectedPixel, 0);
    jEnv->ReleaseIntArrayElements(imagePixelArray, pointerPixel, 0);
    jEnv->ReleaseFloatArrayElements(effectMatrix, pointerEffectMatrix, 0);
    return effectedPixelArray;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setConventionalMultiColorEffect(JNIEnv *jEnv,
                                                                               jclass type,
                                                                               jintArray imagePixelArray,
                                                                               jint imageWidth,
                                                                               jfloat conventionalMultiplier,
                                                                               jfloatArray conventionalEffectMatrixElementArray,
                                                                               jfloatArray colorEffectMultiplierArray,
                                                                               jfloatArray colorEffectMatrixItemArray) {
    jint imagePixelCount = jEnv->GetArrayLength(imagePixelArray);
    jint colorMatrixCount = jEnv->GetArrayLength(colorEffectMultiplierArray);
    jintArray effectedPixelArray = jEnv->NewIntArray(imagePixelCount);

    jfloat *pointerConventionalMatrixItem = jEnv->GetFloatArrayElements(
            conventionalEffectMatrixElementArray, 0);
    jfloat *pointerMultiplier = jEnv->GetFloatArrayElements(colorEffectMultiplierArray, 0);
    jfloat *pointerMatrixItem = jEnv->GetFloatArrayElements(colorEffectMatrixItemArray, 0);
    jint *pointerPixel = jEnv->GetIntArrayElements(imagePixelArray, NULL);
    jint *effectedPointerPixel = jEnv->GetIntArrayElements(effectedPixelArray, NULL);
    jint height = imagePixelCount / imageWidth;

    for (int imageY = 0; imageY < height; ++imageY) {
        for (int imageX = 0; imageX < imageWidth; ++imageX) {
            int index = (imageY * imageWidth) + imageX;
            jint pixel;
            if (imageY == 0 || imageY == (height - 1) || imageX == 0 ||
                imageX == (imageWidth - 1)) {
                pixel = pointerPixel[index];
            } else {
                pixel = setConventionalEffect(index, pointerPixel, pointerConventionalMatrixItem, 0,
                                              conventionalMultiplier, imageWidth);
            }
            if (colorMatrixCount > 0) {
                effectedPointerPixel[index] = setColorEffect(pixel, pointerMatrixItem,
                                                             colorMatrixCount, pointerMultiplier);
            }
        }
    }

    jEnv->ReleaseFloatArrayElements(conventionalEffectMatrixElementArray,
                                    pointerConventionalMatrixItem, 0);
    jEnv->ReleaseFloatArrayElements(colorEffectMultiplierArray, pointerMultiplier, 0);
    jEnv->ReleaseFloatArrayElements(colorEffectMatrixItemArray, pointerMatrixItem, 0);
    jEnv->ReleaseIntArrayElements(imagePixelArray, pointerPixel, NULL);
    jEnv->ReleaseIntArrayElements(effectedPixelArray, effectedPointerPixel, NULL);
    return effectedPixelArray;
}