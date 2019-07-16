package com.mkrworld.libfilter

import android.graphics.Bitmap

/**
 * Class to hold the Per Define Filters or IF You create Some New One then You Should use the [FilterBuilder] for that
 */
class LibFilter {
    enum class FILTER {
        GRAYSCALE(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE)
        )),

        INVERT(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_INVERT)
        )),

        RED(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                getFilterMatrix(MATRIX.COLOR_RED)
        )),

        BLUE(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_BLUE)
        )),

        CONTRASBLUE(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_CONTRAS_BLUE)
        )),

        GREEN(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                getFilterMatrix(MATRIX.COLOR_GREEN)
        )),

        CYAN(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                getFilterMatrix(MATRIX.COLOR_CYAN)
        )),

        MAGENTA(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                getFilterMatrix(MATRIX.COLOR_MAGENTA)
        )),

        PINK(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                getFilterMatrix(MATRIX.COLOR_PINK)
        )),

        SEPI(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                getFilterMatrix(MATRIX.COLOR_SEPIA)
        )),

        VIOLET(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                getFilterMatrix(MATRIX.COLOR_VIOLET)
        )),

        YELLOW(FilterCategory.COLOR, arrayListOf(
                getFilterMatrix(MATRIX.COLOR_GRAY_SCALE),
                getFilterMatrix(MATRIX.COLOR_NEUTRALIZER_B),
                getFilterMatrix(MATRIX.COLOR_YELLOW)
        ));

        val filterCategory: FilterCategory
        val filterMatrixArrayList: ArrayList<FilterMatrix>

        constructor(filterCategory: FilterCategory, filterMatrixArrayList: ArrayList<FilterMatrix>) {
            this.filterCategory = filterCategory
            this.filterMatrixArrayList = filterMatrixArrayList
        }
    }

    companion object {

        /**
         * Method to apply the filter
         * @param bitmap
         * @param filter
         */
        fun applyFilter(bitmap: Bitmap, filter: FILTER): Bitmap? {
            return FilterBuilder.SingleImage().setSrcBitmap(bitmap).setFilterMatrixArrayList(filter.filterMatrixArrayList).buildEffect()
        }

        /**
         * Method to get the Filter
         */
        private fun getFilterMatrix(matrix: MATRIX): FilterMatrix {
            return FilterMatrix.Builder(matrix.filterCategory)
                    .setMatrix(matrix.matrix)
                    .setMultiplier(matrix.multiplier)
                    .setOffset(matrix.offset)
                    .build()
        }
    }
}