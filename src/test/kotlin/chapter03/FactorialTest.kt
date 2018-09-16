package chapter03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FactorialTest {

    @Test
    fun `0!`() {
        assertThat(0.factorial()).isEqualTo(1)
    }

    @Test
    fun `1!`() {
        assertThat(1.factorial()).isEqualTo(1)
    }

    @Test
    fun `3!`() {
        assertThat(3.factorial()).isEqualTo(6)
    }

    @Test
    fun `7!`() {
        assertThat(7.factorial()).isEqualTo(5040)
    }

    @Test
    fun `10!`() {
        assertThat(10.factorial()).isEqualTo(3628800)
    }

}