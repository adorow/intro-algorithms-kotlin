package chapter02

// Based on the "in-place" implementation defined in the book's pseudo-code
fun <T : Comparable<T>> insertionSort(A: MutableList<T>): Unit {
    for (j in 1 until A.size) {
        val key = A[j]

        // Insert A[j]  into the sorted sequence A[1..j - 1].
        var i = j - 1
        while (i >= 0 && A[i] > key) {
            A[i + 1] = A[i]
            i--
        }
        A[i + 1] = key
    }
}
