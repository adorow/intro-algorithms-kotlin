package chapter04

import kotlin.math.floor

data class MaxSubarray(val maxLeft: Int, val maxRight: Int, val sum: Int)

private fun findMaxCrossingSubarray(A: List<Int>, low: Int = 0, mid: Int = A.size / 2, high: Int = A.size - 1): MaxSubarray {
    val infinity_ = Int.MIN_VALUE / 2

    var maxLeft = -1

    var leftSum = infinity_
    var sum = 0

    for (i in mid downTo low) {
        sum += A[i]
        if (sum > leftSum) {
            leftSum = sum
            maxLeft = i
        }
    }

    var maxRight = -1

    var rightSum = infinity_
    sum = 0

    for (j in mid + 1..high) {
        sum += A[j]
        if (sum > rightSum) {
            rightSum = sum
            maxRight = j
        }
    }

    return MaxSubarray(maxLeft, maxRight, leftSum + rightSum)
}

// This is based in the main implementation defined in the book
fun findMaximumSubarray(A: List<Int>, low: Int = 0, high: Int = A.size - 1): MaxSubarray {
    if (high == low) {
        return MaxSubarray(low, high, A[low])
    }

    val mid = floor((low + high) / 2.0).toInt()

    val (leftLow, leftHigh, leftSum) = findMaximumSubarray(A, low, mid)
    val (rightLow, rightHigh, rightSum) = findMaximumSubarray(A, mid + 1, high)
    val (crossLow, crossHigh, crossSum) = findMaxCrossingSubarray(A, low, mid, high)

    if (leftSum >= rightSum && leftSum >= crossSum) {
        return MaxSubarray(leftLow, leftHigh, leftSum)
    }
    if (rightSum >= leftSum && rightSum >= crossSum) {
        return MaxSubarray(rightLow, rightHigh, rightSum)
    }

    return MaxSubarray(crossLow, crossHigh, crossSum)
}

// This is my solution for the problem 4.1-5: "to develop a nonrecursive, linear-time algorithm for the maximum-subarray problem"
fun findMaximumSubarrayLinearTime(A: List<Int>, low: Int = 0, high: Int = A.size - 1): MaxSubarray {
    var maxLeft = low
    var maxRight = low
    var maxSum = A[low]

    var i = low
    var sum = A[low]

    for (j in low + 1..high) {
        // find max subarray A[i..j]
        if (A[j] > sum + A[j]) {
            i = j
            sum = A[j]
        } else {
            sum += A[j]
        }

        // now find the max subarray between subarray(A[i..j]) and subarray(A[1..j-1])
        if (sum > maxSum) {
            maxSum = sum
            maxLeft = i
            maxRight = j
        }

    }

    return MaxSubarray(maxLeft, maxRight, maxSum)
}




