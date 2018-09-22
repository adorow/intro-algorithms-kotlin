package chapter04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MaximumSubarrayTest {

    @Nested
    @DisplayName("Divide and Conquer O(n lg n)")
    inner class DivideAndConquerAlgorithm {

        @Test
        @DisplayName("book example")
        fun `book example`() {
            val input = listOf(13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7)

            val maxSubarray = findMaximumSubarray(input)

            assertThat(maxSubarray).isNotNull
            assertThat(maxSubarray.maxLeft).isEqualTo(7)
            assertThat(maxSubarray.maxRight).isEqualTo(10)
            assertThat(maxSubarray.sum).isEqualTo(43)
        }

        @Test
        fun `when all are positive, the entire list is the maximum subarray`() {
            val input = listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)

            val maxSubarray = findMaximumSubarray(input)

            assertThat(maxSubarray).isNotNull
            assertThat(maxSubarray.maxLeft).isEqualTo(0)
            assertThat(maxSubarray.maxRight).isEqualTo(9)
            assertThat(maxSubarray.sum).isEqualTo(10)
        }

        @Test
        fun `when all are negative, the value closer to zero is the single maximum subarray`() {
            val input = listOf(-7, -2, -1, -6, -8, -10, -3, -4, -5, -9)

            val maxSubarray = findMaximumSubarray(input)

            assertThat(maxSubarray).isNotNull
            assertThat(maxSubarray.maxLeft).isEqualTo(2)
            assertThat(maxSubarray.maxRight).isEqualTo(2)
            assertThat(maxSubarray.sum).isEqualTo(-1)
        }

    }

    @Nested
    @DisplayName("Linear O(n)")
    inner class LinearTimeAlgorithm {

        @Test
        fun `book example`() {
            val input = listOf(13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7)

            val maxSubarray = findMaximumSubarrayLinearTime(input)

            assertThat(maxSubarray).isNotNull
            assertThat(maxSubarray.maxLeft).isEqualTo(7)
            assertThat(maxSubarray.maxRight).isEqualTo(10)
            assertThat(maxSubarray.sum).isEqualTo(43)
        }

        @Test
        fun `when all are positive, the entire list is the maximum subarray`() {
            val input = listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)

            val maxSubarray = findMaximumSubarrayLinearTime(input)

            assertThat(maxSubarray).isNotNull
            assertThat(maxSubarray.maxLeft).isEqualTo(0)
            assertThat(maxSubarray.maxRight).isEqualTo(9)
            assertThat(maxSubarray.sum).isEqualTo(10)
        }

        @Test
        fun `when all are negative, the value closer to zero is the single maximum subarray`() {
            val input = listOf(-7, -2, -1, -6, -8, -10, -3, -4, -5, -9)

            val maxSubarray = findMaximumSubarrayLinearTime(input)

            assertThat(maxSubarray).isNotNull
            assertThat(maxSubarray.maxLeft).isEqualTo(2)
            assertThat(maxSubarray.maxRight).isEqualTo(2)
            assertThat(maxSubarray.sum).isEqualTo(-1)
        }

    }


}