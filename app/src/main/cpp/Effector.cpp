#include <jni.h>
#include "ColorEffect.h"
#include "ConventionalEffect.h"
#include "ConventionalColorEffect.h"
#include "OverlayEffect.h"
#include "MultiplyEffect.h"
#include "MergingEffect.h"

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setColorEffect(JNIEnv *jEnv, jclass type,
                                                              jintArray srcImageIntArray,
                                                              jint imageWidth,
                                                              jfloatArray srcMultiplierArray,
                                                              jfloatArray effectMatrixArray) {

    ColorEffect effect = ColorEffect(jEnv,
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

    ConventionalEffect effect = ConventionalEffect(jEnv,
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

    ConventionalColorEffect effect = ConventionalColorEffect(jEnv,
                                                             imagePixelArray,
                                                             imageWidth,
                                                             multiplier,
                                                             effectMatrixArray,
                                                             srcColorMultiplierArray,
                                                             colorEffectMatrixArray);
    return effect.applyEffect();
};

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setOverlayEffect(JNIEnv *jEnv,
                                                                jclass type,
                                                                jintArray imagePixelArray,
                                                                jint imageWidth,
                                                                jintArray overlayPixelArray,
                                                                jfloat multiplier) {

    OverlayEffect effect = OverlayEffect(jEnv,
                                                         imagePixelArray,
                                                         imageWidth,
                                                         overlayPixelArray,
                                                         multiplier);
    return effect.applyEffect();
};

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setMultiplyEffect(JNIEnv *jEnv,
                                                                 jclass type,
                                                                 jintArray imagePixelArray,
                                                                 jint imageWidth,
                                                                 jintArray overlayPixelArray,
                                                                 jfloat multiplier) {

    MultiplyEffect effect = MultiplyEffect(jEnv,
                                           imagePixelArray,
                                           imageWidth,
                                           overlayPixelArray,
                                           multiplier);
    return effect.applyEffect();
};

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_Effector_setMergingEffect(JNIEnv *jEnv,
                                                                jclass type,
                                                                jintArray imagePixelArray,
                                                                jint imageWidth,
                                                                jintArray overlayPixelArray,
                                                                jfloat multiplier) {

    MergingEffect effect = MergingEffect(jEnv,
                                         imagePixelArray,
                                         imageWidth,
                                         overlayPixelArray,
                                         multiplier);
    return effect.applyEffect();
};