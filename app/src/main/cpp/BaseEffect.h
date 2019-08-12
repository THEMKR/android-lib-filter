#ifndef LIB_FILTER_BASEEFFECT_H
#define LIB_FILTER_BASEEFFECT_H

#include <jni.h>


/**
 * Config for ARGB_8888 Pixel Structure
 */
typedef struct {
    uint8_t blue;
    uint8_t green;
    uint8_t red;
    uint8_t alpha;
} ARGB;


class BaseEffect {

public:

    /**
    * Method to get the color value in 0-255 range
    * @param value
    * @return
    */
    uint8_t getColorValue(float value) {
        if (value > 255.0) {
            return (uint8_t) 255;
        } else if (value < 0.0) {
            return 0;
        }
        return (uint8_t) value;
    }

    float getFloat(uint8_t value, float mult) {
        return (float) value * mult;
    }

    float toFloatBy225(uint8_t value) {
        return (float) value / 255.0;
    }

    float toFloat(uint8_t value) {
        return (float) value;
    }
};

#endif //LIB_FILTER_BASEEFFECT_H
