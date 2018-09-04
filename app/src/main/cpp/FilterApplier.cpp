#include <jni.h>
#include <stdint.h>
#include "ColorEffect.h"

extern "C" JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_FilterApplier_setColorEffect(JNIEnv *jEnv, jclass type,
                                                              jintArray imagePixelArray,
                                                              jfloat multiplier,
                                                              jfloatArray effectMatrix) {

}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_FilterApplier_setMultiColorEffect(JNIEnv *jEnv, jclass type,
                                                                   jintArray imagePixelArray,
                                                                   jint imageWidth,
                                                                   jfloatArray colorEffectMultiplierArray,
                                                                   jfloatArray colorEffectMatrixItemArray) {

}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_FilterApplier_setConventionalEffect(JNIEnv *jEnv, jclass type,
                                                                     jintArray imagePixelArray,
                                                                     jint imageWidth,
                                                                     jfloat multiplier,
                                                                     jfloatArray effectMatrix) {

}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_mkrworld_libfilter_jnicaller_FilterApplier_setConventionalMultiColorEffect(JNIEnv *jEnv,
                                                                               jclass type,
                                                                               jintArray imagePixelArray,
                                                                               jint imageWidth,
                                                                               jfloat conventionalMultiplier,
                                                                               jfloatArray conventionalEffectMatrixElementArray,
                                                                               jfloatArray colorEffectMultiplierArray,
                                                                               jfloatArray colorEffectMatrixItemArray) {
}