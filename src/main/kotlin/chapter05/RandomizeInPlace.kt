package chapter05

import java.util.*

fun <T> MutableList<T>.swap(i1: Int, i2: Int) {
    val v1 = this[i1]
    this[i1] = this[i2]
    this[i2] = v1
}

fun Random.nextInt(lowerBound: Int, upperBound: Int): Int =
        this.nextInt(upperBound - lowerBound) + lowerBound

fun randomizeInPlace(A: MutableList<Int>, rng: Random = Random()): Unit {
    val n = A.size

    (0 until n - 1).forEach { i ->
        A.swap(i, rng.nextInt(i, n - 1))
    }

}