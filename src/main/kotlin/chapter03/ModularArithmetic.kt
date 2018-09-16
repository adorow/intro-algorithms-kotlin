package chapter03

import kotlin.math.floor

// Kotlin already has a (deprecated) 'mod' function, that's why here the name is changed
infix fun Int.my_mod(quotient: Int): Int =
        this - (quotient * floor(this.toDouble() / quotient).toInt())
