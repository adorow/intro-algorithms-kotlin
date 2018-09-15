package chapter02


// Based on the "in-place" sort defined by the book's pseudo-code
private fun <T : Comparable<T>> Merge(A: MutableList<T>, p: Int, q: Int, r: Int): Unit {
    val n1 = q - p + 1
    val n2 = r - q

    val L = MutableList(n1, init = { i -> A[p + i] })
    val R = MutableList(n2, init = { j -> A[q + j + 1] })

    var i = 0
    var j = 0

    for (k in p..r) {
        // copy from L if there are more elements to L, and the next element to L is less than or equal to the next element to R
        if (j == R.size || (i < L.size && L[i] <= R[j])) {
            A[k] = L[i]
            i++
        } else {
            A[k] = R[j]
            j++
        }
    }
}

fun <T : Comparable<T>> MergeSort(A: MutableList<T>, p: Int = 0, r: Int = A.size - 1) {
    if (p < r) {
        val q = (p + r) / 2
        MergeSort(A, p, q)
        MergeSort(A, q + 1, r)
        Merge(A, p, q, r)
    }
}
