package chapter08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BucketSortTest {

    @Nested
    @DisplayName("Single-digit numbers")
    inner class SingleDigitNumbers {

        @Test
        fun `if already sorted then nothing changes`() {
            val input = arrayOf(1, 2, 3, 4, 5, 6)

            val output = bucketSort(input)

            assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
        }

        @Test
        fun `reversed input will be sorted`() {
            val input = arrayOf(6, 5, 4, 3, 2, 1)

            val output = bucketSort(input)

            assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
        }

        @Test
        fun `simple shuffled example`() {
            val input = arrayOf(5, 2, 4, 6, 1, 3)

            val output = bucketSort(input)

            assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
        }

        @Test
        fun `empty just returns the same empty result`() {
            val input = emptyArray<Int>()

            val output = bucketSort(input)

            assertThat(output).isEmpty()
        }

    }

    @Nested
    @DisplayName("Three-digit numbers")
    inner class ThreeDigitNumbers {

        @Test
        fun `if already sorted then nothing changes`() {
            val input = arrayOf(100, 234, 312, 478, 597, 643)

            val output = bucketSort(input)

            assertThat(output).containsExactly(100, 234, 312, 478, 597, 643)
        }

        @Test
        fun `simple shuffled example`() {
            val input = arrayOf(597, 234, 643, 478, 100, 312)

            val output = bucketSort(input)

            assertThat(output).containsExactly(100, 234, 312, 478, 597, 643)
        }

    }

    @Nested
    @DisplayName("Mixed numbers")
    inner class MixedNumbers {

        @Test
        fun `if already sorted then nothing changes`() {
            val input = arrayOf(7, 13, 56, 209, 872, 5492, 9098)

            val output = bucketSort(input)

            assertThat(output).containsExactly(7, 13, 56, 209, 872, 5492, 9098)
        }

        @Test
        fun `simple shuffled example`() {
            val input = arrayOf(872, 5492, 13, 209, 7, 9098, 56)

            val output = bucketSort(input)

            assertThat(output).containsExactly(7, 13, 56, 209, 872, 5492, 9098)
        }

    }

    @Nested
    @DisplayName("Book examples")
    inner class BookExamples {

        @Test
        fun `book example using integers instead of decimals`() {
            val input = arrayOf(78, 17, 39, 26, 72, 94, 21, 12, 23, 68)

            val output = bucketSort(input)

            assertThat(output).containsExactly(12, 17, 21, 23, 26, 39, 68, 72, 78, 94)
        }

    }

    @Nested
    @DisplayName("Unbalanced cases")
    inner class UnbalancedCases {

        @Test
        fun `one large number at the end with multiple very small numbers`() {
            val input = arrayOf(3, 6, 4, 7, 1, 1949, 5, 2)

            val output = bucketSort(input)

            assertThat(output).containsExactly(1, 2, 3, 4, 5, 6, 7, 1949)
        }

    }

}
