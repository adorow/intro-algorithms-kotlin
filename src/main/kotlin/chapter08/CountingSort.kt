package chapter08


fun countingSort(A: Array<Int>, B: Array<Int> = Array(A.size) { 0 }, k: Int = A.max()!!): Array<Int> {
    if (A.isEmpty()) return B

    val C = Array(k + 1) { 0 }

    // !! here we have to start the loop at 0 - since we have 0-based arrays
    for (j in 0 until A.size) {
        C[A[j]]++
    }
    // C[i] now contains the number of elements equal to i.
    for (i in 1..k) {
        C[i] = C[i] + C[i - 1]
    }
    // C[i] now contains the number of elements less than or equal to i.

    for (j in A.lastIndex downTo 0) {
        // !! in the line of code below, we added the -1
        // in Kotlin we have indexes starting at 0, if we had indexes starting at 1 that would not be necessary;
        // since the "number of elements less than or equal" to this one would mean exactly their final index in the array
        B[C[A[j]] - 1] = A[j]
        C[A[j]]--
    }

    return B
}



