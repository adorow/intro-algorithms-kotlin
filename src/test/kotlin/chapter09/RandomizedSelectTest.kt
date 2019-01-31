package chapter09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RandomizedSelectTest {

    @Nested
    @DisplayName("With array of (6, 3, 8, 4, 1, 7, 2, 5)")
    inner class WithArrayExample1 {

        val A = arrayOf(6, 3, 8, 4, 1, 7, 2, 5)

        @Test
        fun `1st element is 1`() {
            val output = randomizedSelect(A, i = 1)

            assertThat(output).isEqualTo(1)
        }

        @Test
        fun `2nd element is 2`() {
            val output = randomizedSelect(A, i = 2)

            assertThat(output).isEqualTo(2)
        }

        @Test
        fun `4th element is 4`() {
            val output = randomizedSelect(A, i = 4)

            assertThat(output).isEqualTo(4)
        }

        @Test
        fun `7th element is 7`() {
            val output = randomizedSelect(A, i = 7)

            assertThat(output).isEqualTo(7)
        }

        @Test
        fun `8th element is 8`() {
            val output = randomizedSelect(A, i = 8)

            assertThat(output).isEqualTo(8)
        }

    }

}