package chapter07

import ext.swap

fun quicksort(A: Array<Int>, p: Int = 0, r: Int = A.lastIndex) {
    if (p < r) {
        val q = partition(A, p, r)
        quicksort(A, p, q - 1)
        quicksort(A, q + 1, r)
    }
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
