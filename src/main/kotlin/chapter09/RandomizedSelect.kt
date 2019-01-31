package chapter09

import ext.nextInt
import ext.swap
import java.util.*

fun randomizedSelect(A: Array<Int>, p: Int = 0, r: Int = A.lastIndex, i: Int, rng: Random = Random()): Int {
    if (p == r) {
        return A[p]
    }
    val q = randomizedPartition(A, p, r, rng)
    val k = q - p + 1

    // the pivot value is the answer
    if (i == k) {
        return A[q]
    }
    if (i < k) {
        return randomizedSelect(A, p, q - 1, i)
    }
    return randomizedSelect(A, q + 1, r, i - k)
}

private fun randomizedPartition(A: Array<Int>, p: Int, r: Int, rng: Random = Random()): Int {
    val i = rng.nextInt(p, r)
    A.swap(r, i)
    return partition(A, p, r)
}

private fun partition(A: Array<Int>, p: Int, r: Int): Int {
    val x = A[r]
    var i = p - 1
    for (j in p until r) {
        if (A[j] <= x) {
            i++
            A.swap(i, j)
        }
    }
    A.swap(i + 1, r)
    return i + 1
}
