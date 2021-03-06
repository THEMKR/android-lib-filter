package com.mkrworld.libfilter

/**
 * @author THEMKR
 */
class FilterMatrix {

    /**
     * Method to get the Filter Matrix.<br></br>[<UL><LI>4X5 for Color</LI><LI>3X3 for Conventional</LI><LI>1X1 for Rest Other</LI></UL>]
     *
     * @return
     */
    val matrix: FloatArray

    /**
     * Method to get the Filter Multiplier
     *
     * @return
     */
    val multiplier: Float

    /**
     * Method to get the Filter Category
     *
     * @return
     */
    val filterCategory: FilterCategory

    /**
     * Method to get the OffSet
     *
     * @return
     */
    val offSet: Offset

    /**
     * Constructor
     *
     * @param filterCategory Category of effect
     * @param offSet Effect Value offset
     * @param multiplier Effect Value Multiplier
     * @param matrix [<UL><LI>4X5 for Color</LI><LI>3X3 for Conventional</LI><LI>1X1 for Rest Other</LI></UL>]
     */
    private constructor(filterCategory: FilterCategory, offSet: Offset, multiplier: Float?, matrix: FloatArray) {
        this.filterCategory = filterCategory
        this.multiplier = multiplier!!
        this.offSet = offSet
        this.matrix = getMatrix(filterCategory, offSet, matrix)
    }

    /**
     * Method to create Filter mMatrix from Input mMatrix
     * @param filterCategory
     * @param offSet
     * @param matrix
     */
    private fun getMatrix(filterCategory: FilterCategory, offSet: Offset, matrix: FloatArray): FloatArray {
        var matrix = matrix
        if (matrix.isEmpty()) {
            when (filterCategory) {
                FilterCategory.COLOR -> matrix = floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
                FilterCategory.CONVENTIONAL -> matrix = floatArrayOf(0f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 10f)
                else -> matrix = floatArrayOf(0f)
            }
        }
        when (filterCategory) {
            FilterCategory.COLOR -> return floatArrayOf(matrix[0], matrix[1], matrix[2], 0f, offSet.r, matrix[3], matrix[4], matrix[5], 0f, offSet.g, matrix[6], matrix[7], matrix[8], 0f, offSet.b, 0f, 0f, 0f, 1f, 0f)
            FilterCategory.CONVENTIONAL -> return matrix
            else -> return floatArrayOf(0f)
        }
    }

    /**
     * Class to build the Filter Matrix
     */
    class Builder {

        /**
         * Method to get the Filter Matrix
         *
         * @return
         */
        private var matrix: FloatArray = floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)

        /**
         * Method to get the Filter Multiplier
         *
         * @return
         */
        private var multiplier: Float = 1F

        /**
         * Method to get the Filter Category
         *
         * @return
         */
        private val filterCategory: FilterCategory

        /**
         * Method to get the OffSet
         *
         * @return
         */
        private var offSet: Offset = Offset(0F)

        constructor(filterCategory: FilterCategory) {
            this.filterCategory = filterCategory
        }

        /**
         * Method to set the effect matrix
         */
        fun setMatrix(matrix: FloatArray): Builder {
            this.matrix = matrix
            return this
        }

        /**
         * Method to set the multiplier
         */
        fun setMultiplier(multiplier: Float): Builder {
            this.multiplier = multiplier
            return this
        }

        /**
         * Method to set the offset
         */
        fun setOffset(offSet: Offset): Builder {
            this.offSet = offSet
            return this
        }

        fun build(): FilterMatrix {
            return FilterMatrix(filterCategory, offSet, multiplier, matrix)
        }
    }
}