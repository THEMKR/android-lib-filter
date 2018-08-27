package com.mkrworld.libfilter.utils;

import com.mkrworld.libfilter.enums.FilterCategory;
import com.mkrworld.libfilter.enums.Filter;

public class Utils {

    /**
     * Method to get the Category Of Effect
     *
     * @param filter Effect to be set by user
     */
    public static FilterCategory getFilterCategory(Filter filter) {
        switch (filter) {
            case COLOR_RED:
            case COLOR_CONTRAS_BLUE:
            case COLOR_BLUE:
            case COLOR_CYAN:
            case COLOR_GRAY_SCALE:
            case COLOR_GREEN:
            case COLOR_INVERT:
            case COLOR_MAGENTA:
            case COLOR_PINK:
            case COLOR_SEPIA:
            case COLOR_VIOLET:
            case COLOR_YELLOW:
                return FilterCategory.COLOR;
            case CONVENTIONAL_SKETCH:
            case CONVENTIONAL_SOLID:
                return FilterCategory.CONVENTIONAL;
            case NON:
            default:
                return FilterCategory.NON;
        }
    }
}
