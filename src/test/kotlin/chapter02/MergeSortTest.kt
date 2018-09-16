package chapter02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MergeSortTest {

    @Test
    fun `if already sorted then nothing changes`() {
        val input = mutableListOf(1, 2, 3, 4, 5, 6)

        mergeSort(input)

        assertThat(input).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `example can be merge sorted`() {
        val input = mutableListOf(5, 2, 4, 6, 1, 3)

        mergeSort(input)

        assertThat(input).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `example with odd number of elements can be merge sorted`() {
        val input = mutableListOf(5, 2, 4, 6, 7, 3, 1)

        mergeSort(input)

        assertThat(input).containsExactly(1, 2, 3, 4, 5, 6, 7)
    }

    @Test
    fun `empty just returns the same empty result`() {
        val input = emptyList<Int>().toMutableList()

        mergeSort(input)

        assertThat(input).isEmpty()
    }

}