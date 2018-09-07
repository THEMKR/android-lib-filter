#include <jni.h>
#include <stdint.h>
#include "ARGB8888ColorEffect.h"
#include "ARGB8888ConventionalEffect.h"
#include "ARGB8888ConventionalColorEffect.h"

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setColorEffect(JNIEnv *jEnv, jclass type,
                                                              jintArray srcImageIntArray,
                                                              jint imageWidth,
                                                              jfloatArray srcMultiplierArray,
                                                              jfloatArray effectMatrixArray) {

    ARGB8888ColorEffect effect = ARGB8888ColorEffect(jEnv,
                                                     srcImageIntArray,
                                                     imageWidth,
                                                     srcMultiplierArray,
                                                     effectMatrixArray);
    return effect.applyEffect();
};

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setConventionalEffect(JNIEnv *jEnv, jclass type,
                                                                     jintArray imagePixelArray,
                                                                     jint imageWidth,
                                                                     jfloat multiplier,
                                                                     jfloatArray effectMatrixArray) {

    ARGB8888ConventionalEffect effect = ARGB8888ConventionalEffect(jEnv,
                                                                   imagePixelArray,
                                                                   imageWidth,
                                                                   multiplier,
                                                                   effectMatrixArray);
    return effect.applyEffect();
};

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setConventionalMultiColorEffect(JNIEnv *jEnv,
                                                                               jclass type,
                                                                               jintArray imagePixelArray,
                                                                               jint imageWidth,
                                                                               jfloat multiplier,
                                                                               jfloatArray effectMatrixArray,
                                                                               jfloatArray srcColorMultiplierArray,
                                                                               jfloatArray colorEffectMatrixArray) {

    ARGB8888ConventionalColorEffect effect = ARGB8888ConventionalColorEffect(jEnv,
                                                                             imagePixelArray,
                                                                             imageWidth,
                                                                             multiplier,
                                                                             effectMatrixArray,
                                                                             srcColorMultiplierArray,
                                                                             colorEffectMatrixArray);
    return effect.applyEffect();
};