#ifndef LIB_FILTER_BASEEFFECT_H
#define LIB_FILTER_BASEEFFECT_H

#include <jni.h>


class BaseEffect {

protected:
    JNIEnv *jEnv;
    jintArray srcImageIntArray;
    jintArray destImageIntArray;
    jint imageWidth;
    jint srcImagePixelCount;
    jint *pointerSrcImagePixel;
    jint *pointerDestImagePixel;

    /**
    * Constructor
    * @param jEnv
    * @param srcImageByteArray
    * @param imageWidth
    */
    BaseEffect(JNIEnv *jEnv,
               jintArray srcImageIntArray,
               jint imageWidth) {
        this->jEnv = jEnv;
        this->srcImageIntArray = srcImageIntArray;
        this->imageWidth = imageWidth;
    }

    /**
    * Method to initialized the Res. (super) should be called if Override
    */
    void init() {
        srcImagePixelCount = jEnv->GetArrayLength(srcImageIntArray);
        destImageIntArray = jEnv->NewIntArray(srcImagePixelCount);
        pointerSrcImagePixel = jEnv->GetIntArrayElements(srcImageIntArray, NULL);
        pointerDestImagePixel = jEnv->GetIntArrayElements(destImageIntArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        jEnv->ReleaseIntArrayElements(srcImageIntArray, pointerSrcImagePixel, 0);
        jEnv->ReleaseIntArrayElements(destImageIntArray, pointerDestImagePixel, 0);
    }
};

#endif //LIB_FILTER_BASEEFFECT_H
