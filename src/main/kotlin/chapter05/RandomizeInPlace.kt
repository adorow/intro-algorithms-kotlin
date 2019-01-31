package chapter05

import ext.nextInt
import ext.swap
import java.util.*

fun randomizeInPlace(A: MutableList<Int>, rng: Random = Random()) {
    val n = A.size

    (0 until n - 1).forEach { i ->
        A.swap(i, rng.nextInt(i, n - 1))
    }

}