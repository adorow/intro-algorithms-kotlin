package chapter08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RadixSortTest {

    @Nested
    @DisplayName("Single-digit numbers")
    inner class SingleDigitNumbers {

        @Test
        fun `if already sorted then nothing changes`() {
            val input = arrayOf(1, 2, 3, 4, 5, 6)

            val output = radixSort(input)

            assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
        }

        @Test
        fun `reversed input will be sorted`() {
            val input = arrayOf(6, 5, 4, 3, 2, 1)

            val output = radixSort(input)

            assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
        }

        @Test
        fun `simple shuffled example`() {
            val input = arrayOf(5, 2, 4, 6, 1, 3)

            val output = radixSort(input)

            assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
        }

        @Test
        fun `empty just returns the same empty result`() {
            val input = emptyArray<Int>()

            val output = radixSort(input)

            assertThat(output).isEmpty()
        }

        @Test
        fun `book example number 1`() {
            val input = arrayOf(2, 5, 3, 0, 2, 3, 0, 3)

            val output = radixSort(input)

            assertThat(output).containsExactly(0, 0, 2, 2, 3, 3, 3, 5)
        }

        @Test
        fun `book example from exercises`() {
            val input = arrayOf(6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2)

            val output = radixSort(input)

            assertThat(output).containsExactly(0, 0, 1, 1, 2, 2, 3, 3, 4, 6, 6)
        }

    }

    @Nested
    @DisplayName("Three-digit numbers")
    inner class ThreeDigitNumbers {

        @Test
        fun `if already sorted then nothing changes`() {
            val input = arrayOf(100, 234, 312, 478, 597, 643)

            val output = radixSort(input)

            assertThat(output).containsExactly(100, 234, 312, 478, 597, 643)
        }

        @Test
        fun `simple shuffled example`() {
            val input = arrayOf(597, 234, 643, 478, 100, 312)

            val output = radixSort(input)

            assertThat(output).containsExactly(100, 234, 312, 478, 597, 643)
        }

        @Test
        fun `with repeated digits`() {
            val input = arrayOf(156, 155, 157, 257, 556, 334, 294, 196, 356, 534)

            val output = radixSort(input)

            assertThat(output).containsExactly(155, 156, 157, 196, 257, 294, 334, 356, 534, 556)
        }

    }

    @Nested
    @DisplayName("Mixed numbers")
    inner class MixedNumbers {

        @Test
        fun `if already sorted then nothing changes`() {
            val input = arrayOf(7, 13, 56, 209, 872, 5492, 9098)

            val output = radixSort(input)

            assertThat(output).containsExactly(7, 13, 56, 209, 872, 5492, 9098)
        }

        @Test
        fun `simple shuffled example`() {
            val input = arrayOf(872, 5492, 13, 209, 7, 9098, 56)

            val output = radixSort(input)

            assertThat(output).containsExactly(7, 13, 56, 209, 872, 5492, 9098)
        }

    }

}
