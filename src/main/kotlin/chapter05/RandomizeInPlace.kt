package chapter05

import ext.swap
import java.util.*

fun Random.nextInt(lowerBound: Int, upperBound: Int): Int =
        this.nextInt(upperBound - lowerBound) + lowerBound

fun randomizeInPlace(A: MutableList<Int>, rng: Random = Random()): Unit {
    val n = A.size

    (0 until n - 1).forEach { i ->
        A.swap(i, rng.nextInt(i, n - 1))
    }

}