package ext

import kotlin.math.pow

fun Int.pow(exponent: Int): Int = this.toDouble().pow(exponent).toInt()