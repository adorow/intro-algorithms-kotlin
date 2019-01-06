package chapter08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CountingSortTest {

    @Test
    fun `if already sorted then nothing changes`() {
        val input = arrayOf(1, 2, 3, 4, 5, 6)

        val output = countingSort(input, k = 6)

        assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `reversed input will be sorted`() {
        val input = arrayOf(6, 5, 4, 3, 2, 1)

        val output = countingSort(input, k = 6)

        assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `simple shuffled example`() {
        val input = arrayOf(5, 2, 4, 6, 1, 3)

        val output = countingSort(input, k = 6)

        assertThat(output).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `empty just returns the same empty result`() {
        val input = emptyArray<Int>()

        val output = countingSort(input, k = 0)

        assertThat(output).isEmpty()
    }

    @Test
    fun `book example number 1`() {
        val input = arrayOf(2, 5, 3, 0, 2, 3, 0, 3)

        val output = countingSort(input, k = 5)

        assertThat(output).containsExactly(0, 0, 2, 2, 3, 3, 3, 5)
    }

    @Test
    fun `book example from exercises`() {
        val input = arrayOf(6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2)

        val output = countingSort(input, k = 6)

        assertThat(output).containsExactly(0, 0, 1, 1, 2, 2, 3, 3, 4, 6, 6)
    }


}
