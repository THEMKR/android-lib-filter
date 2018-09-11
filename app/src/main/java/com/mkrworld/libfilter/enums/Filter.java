package com.mkrworld.libfilter.enums;

public enum Filter {
    NON(FilterCategory.NON),
    //==============================================================================================
    //==============================================================================================
    //COLOR_EFFECT
    //==============================================================================================
    //==============================================================================================
    COLOR_GRAY_SCALE(FilterCategory.COLOR),
    COLOR_RED(FilterCategory.COLOR),
    COLOR_INVERT(FilterCategory.COLOR),
    COLOR_BLUE(FilterCategory.COLOR),
    COLOR_CONTRAS_BLUE(FilterCategory.COLOR),
    COLOR_GREEN(FilterCategory.COLOR),
    COLOR_CYAN(FilterCategory.COLOR),
    COLOR_MAGENTA(FilterCategory.COLOR),
    COLOR_PINK(FilterCategory.COLOR),
    COLOR_SEPIA(FilterCategory.COLOR),
    COLOR_VIOLET(FilterCategory.COLOR),
    COLOR_YELLOW(FilterCategory.COLOR),
    //==============================================================================================
    //==============================================================================================
    //CONVENTIONAL_EFFECT
    //==============================================================================================
    //==============================================================================================
    CONVENTIONAL_SKETCH(FilterCategory.CONVENTIONAL),
    CONVENTIONAL_SOLID(FilterCategory.CONVENTIONAL),
    //==============================================================================================
    //==============================================================================================
    //OVERLAY_EFFECT
    //==============================================================================================
    //==============================================================================================
    OVERLAY_MULTIPLY(FilterCategory.OVERLAY),
    OVERLAY(FilterCategory.OVERLAY);

    private FilterCategory mFilterCategory;

    /**
     * Constructor
     *
     * @param filterCategory
     */
    Filter(FilterCategory filterCategory) {
        mFilterCategory = filterCategory;
    }

    /**
     * Method to get the Category of the filter
     *
     * @return
     */
    public FilterCategory getFilterCategory() {
        return mFilterCategory;
    }
}
