package chapter03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ModularArithmeticTest {

    @Test
    fun `5 mod 2`() {
        assertThat(5 my_mod 2).isEqualTo(1)
    }

    @Test
    fun `11 mod 4`() {
        assertThat(11 my_mod 4).isEqualTo(3)
    }

    @Test
    fun `5 mod 1`() {
        assertThat(5 my_mod 1).isEqualTo(0)
    }

}