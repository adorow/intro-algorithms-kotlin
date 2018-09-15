package chapter02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InsertionSortTest {

    @Test
    fun `if already sorted then nothing changes`() {
        val input = mutableListOf(1, 2, 3, 4, 5, 6)

        InsertionSort(input)

        assertThat(input).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `InsertionSort using the book example`() {
        val input = mutableListOf(5, 2, 4, 6, 1, 3)

        InsertionSort(input)

        assertThat(input).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `empty just returns the same empty result`() {
        val input = emptyList<Int>().toMutableList()

        InsertionSort(input)

        assertThat(input).isEmpty()
    }

}