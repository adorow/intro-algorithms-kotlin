package chapter06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HeapsortTest {

    @Test
    fun `if already sorted then nothing changes`() {
        val input = arrayOf(1, 2, 3, 4, 5, 6)

        heapsort(input)

        assertThat(input).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `reversed input will be sorted`() {
        val input = arrayOf(6, 5, 4, 3, 2, 1)

        heapsort(input)

        assertThat(input).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `simple shuffled example`() {
        val input = arrayOf(5, 2, 4, 6, 1, 3)

        heapsort(input)

        assertThat(input).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `empty just returns the same empty result`() {
        val input = emptyArray<Int>()

        heapsort(input)

        assertThat(input).isEmpty()
    }

}