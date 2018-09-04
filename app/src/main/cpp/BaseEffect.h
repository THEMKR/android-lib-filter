//
// Created by THE-MKR on 3/9/18.
//

#ifndef LIB_FILTER_BASEEFFECT_H
#define LIB_FILTER_BASEEFFECT_H

#include <jni.h>


class BaseEffect {

private:
    JNIEnv *jEnv;
    jintArray srcImageByteArray;
    jintArray destImageByteArray;
    jint imageWidth;
    jint srcImagePixelCount;
    jint *pointerSrcImagePixel;
    jint *pointerDestImagePixel;

protected:

    /**
    * Constructor
    * @param jEnv
    * @param srcImageByteArray
    * @param imageWidth
    */
    BaseEffect(JNIEnv *jEnv, jintArray srcImageByteArray, jint imageWidth) {
        this->jEnv = jEnv;
        this->srcImageByteArray = srcImageByteArray;
        this->imageWidth = imageWidth;
    }

public:

    /**
    * Method to get the JAVA Env
    * @return
    */
    JNIEnv *getJNIEnv() { return jEnv; }

    /**
     * Method to get the Width of the Image
     * @return
     */
    jint getImageWidth() { return imageWidth; }

    /**
     * Method to get the Count of the Pixel
     * @return
     */
    jint getPixelCount() { return srcImagePixelCount; }

    /**
     * Method to get the Pixel Pointer Of the SRC Image
     * @return
     */
    jint *getPointerSrcImage() { return pointerSrcImagePixel; }

    /**
     * Method to get the Pointer of the Dest Image
     * @return
     */
    jint *getPointerDestImage() { return pointerDestImagePixel; }

    /**
    * Method to initialized the Res. (super) should be called if Override
    */
    void init() {
        srcImagePixelCount = jEnv->GetArrayLength(srcImageByteArray);
        destImageByteArray = jEnv->NewIntArray(srcImagePixelCount);
        pointerSrcImagePixel = jEnv->GetIntArrayElements(srcImageByteArray, NULL);
        pointerDestImagePixel = jEnv->GetIntArrayElements(destImageByteArray, NULL);
    }

    /**
     * Method to  release all the Res that was initialized before
     */
    void finish() {
        jEnv->ReleaseIntArrayElements(srcImageByteArray, pointerSrcImagePixel, 0);
        jEnv->ReleaseIntArrayElements(destImageByteArray, pointerDestImagePixel, 0);
    }

    /**
     * Method to apply Effect
     * @return
     */
    void applyEffect();

    /**
     * Method to get the Byte Array of the Effected Pixel
     * @return
     */
    jintArray getEffectedImageIntArray() {
        init();
        applyEffect();
        finish();
        return destImageByteArray;
    }

};

#endif //LIB_FILTER_BASEEFFECT_H
