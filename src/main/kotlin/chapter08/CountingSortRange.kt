package chapter08

import kotlin.math.min

// This algorithm is a solution for problem 8.2-4 of the book.

fun createRangeUsingCountingSort(A: Array<Int>, k: Int = A.max()!!): CountingSortRange {
    val C = Array(k + 1) { 0 }

    // !! here we have to start the loop at 0 - since we have 0-based arrays
    for (j in 0 until A.size) {
        C[A[j]]++
    }
    // C[i] now contains the number of elements equal to i.
    for (i in 1..k) {
        C[i] = C[i] + C[i - 1]
    }

    return CountingSortRange(C, k)

}

class CountingSortRange(private val C: Array<Int>, private val k: Int) {

    fun countWithinRange(range: Pair<Int, Int>): Int {
        if (range.first > range.second) throw IllegalArgumentException("First number must be less than or equal to the second")
        if (range.second < 0) return 0

        val countUpTo = C[min(range.second, k)]
        val countBelowToRemove = if (range.first < 1) 0 else C[range.first - 1]

        return countUpTo - countBelowToRemove
    }

}



