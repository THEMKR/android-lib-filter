package com.mkrworld.libfilter

/**
 * @author THEMKR
 */
class Offset {

    /**
     * OFFSET RED COLOR
     */
    val r: Float

    /**
     * OFFSET GREEN COLOR
     */
    val g: Float

    /**
     * OFFSET BLUE COLOR
     */
    val b: Float

    /**
     * Metho dto get the RGB/3 Color Offset
     *
     * @return
     */
    val all: Float
        get() {
            return (r + g + b) / 3f
        }

    /**
     * Constructor
     *
     * @param offSet OffSet of All color
     */
    constructor(offSet: Float) : this(offSet, offSet, offSet)

    /**
     * Constructor
     *
     * @param r Offset Red-Color
     * @param g Offset Green-Color
     * @param b Offset Blue-Color
     */
    constructor(r: Float, g: Float, b: Float) {
        this.r = r
        this.g = g
        this.b = b
    }
}