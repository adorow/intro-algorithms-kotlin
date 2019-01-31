package ext

import java.util.*

fun Random.nextInt(lowerBound: Int, upperBound: Int): Int =
        this.nextInt(upperBound - lowerBound) + lowerBound