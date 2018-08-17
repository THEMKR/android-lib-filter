#include <jni.h>
#include <stdint.h>

/**
 * Method to get the color value in 0-255 range
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

extern "C" JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setColorEffect(JNIEnv *jEnv, jclass type,
                                                              jintArray pixelArray,
                                                              jfloat multiplier,
                                                              jfloatArray effectMatrix) {

    jfloat *array = jEnv->GetFloatArrayElements(effectMatrix, 0);
    jfloat colorMatrix[4][5] = {{array[0],  array[1],  array[2],  array[3],  array[4]},
                                {array[5],  array[6],  array[7],  array[8],  array[9]},
                                {array[10], array[11], array[12], array[13], array[14]},
                                {array[15], array[16], array[17], array[18], array[19]}};

    jint pixelCount = jEnv->GetArrayLength(pixelArray);
    jint *pixelPointer = jEnv->GetIntArrayElements(pixelArray, NULL);

    for (jint index = 0; index < pixelCount; ++index) {
        jint pixel = pixelPointer[index];
        jfloat alpha = (pixel & 0xFF000000) >> 24;
        jfloat red = (pixel & 0x00FF0000) >> 16;
        jfloat green = (pixel & 0x0000FF00) >> 8;
        jfloat blue = (pixel & 0x000000FF);

        jfloat newRed = ((colorMatrix[0][0] * red) + (colorMatrix[0][1] * green) +
                         (colorMatrix[0][2] * blue)) * multiplier;
        jfloat newGreen = ((colorMatrix[1][0] * red) + (colorMatrix[1][1] * green) +
                           (colorMatrix[1][2] * blue)) * multiplier;
        jfloat newBlue = ((colorMatrix[2][0] * red) + (colorMatrix[2][1] * green) +
                          (colorMatrix[2][2] * blue)) * multiplier;

        pixelPointer[index] = getJavaColor(alpha, newRed, newGreen, newBlue);
    }

    jEnv->ReleaseIntArrayElements(pixelArray, pixelPointer, 0);
    return pixelArray;
}