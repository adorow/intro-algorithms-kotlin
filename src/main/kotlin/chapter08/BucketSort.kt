package chapter08

import chapter02.insertionSort


/**
 * This solution uses the in-place implementation of InsertionSort from Chapter 2.
 */
fun bucketSort(A: Array<Int>): Array<Int> {
    if (A.isEmpty())
        return A

    // buckets
    val B = Array(A.size) { MutableList(0) { 0 } }
    val n = A.size

    val upperBound = 1 + A.max()!!

    for (i in 0 until n) {
        // insert A[i] into list B[floor(n A[i])]
        B[ (A[i] * n) / upperBound ].add(A[i])
    }

    for (i in 0 until n) {
        // sort list B[i] with insertion sort
        insertionSort(B[i])
    }

    // concatenate the lists B[0], B[1], ... , B[n - 1] together in order
    return B.flatMap { it }.toTypedArray()
}
