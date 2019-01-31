package chapter09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinimumTest {

    @Test
    fun `if empty then no result`() {
        val input = emptyList<Int>()

        val min = input.minimum()

        assertThat(min).isNull()
    }

    @Test
    fun `single value returns itself as result`() {
        val input = listOf(524613)

        val min = input.minimum()

        assertThat(min).isEqualTo(524613)
    }

    @Test
    fun `normal list of distinct numbers`() {
        val input = listOf(5, 2, 4, 6, 1, 3)

        val min = input.minimum()

        assertThat(min).isEqualTo(1)
    }

}