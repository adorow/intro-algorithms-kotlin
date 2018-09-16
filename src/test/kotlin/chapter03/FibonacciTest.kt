package chapter03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FibonacciTest {

    @Test
    fun `10 first numbers in fibonacci`() {
        assertThat(fibonacci().take(10).toList()).containsExactly(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
    }

    @Test
    fun `47th number in the fibonacci sequence (starting with 0 as 1st)`() {
        assertThat(fibonacci().drop(46).first()).isEqualTo(1836311903)
    }

}