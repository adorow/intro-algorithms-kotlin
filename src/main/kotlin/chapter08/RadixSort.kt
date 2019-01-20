package chapter08

import ext.pow


fun radixSort(A: Array<Int>, d: Int = getMaxExponent(A)): Array<Int> {
    if (A.isEmpty())
        return A

    var array = A

    for (i in 0 until d) {
        array = radixCountingSort(array, exp = i)
    }

    return array
}

private fun getMaxExponent(A: Array<Int>): Int {
    return A.map { getExponent(it) }.max() ?: 0
}

private fun getExponent(a: Int): Int {
    var numDigits = 1
    var reference = 10

    while (a >= reference) {
        reference *= 10
        numDigits++
    }

    return numDigits
}

private fun valueFromExp(v: Int, exp: Int): Int =
        (v / 10.pow(exp)).rem(10)


private fun radixCountingSort(A: Array<Int>, exp: Int): Array<Int> {
    val k = 9

    val B = Array(A.size) { 0 }
    val C = Array(k + 1) { 0 }

    // !! here we have to start the loop at 0 - since we have 0-based arrays
    for (j in 0 until A.size) {
        C[valueFromExp(A[j], exp)]++
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
        B[C[valueFromExp(A[j], exp)] - 1] = A[j]
        C[valueFromExp(A[j], exp)]--
    }

    return B
}



