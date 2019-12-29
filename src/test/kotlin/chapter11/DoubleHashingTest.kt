package chapter11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DoubleHashingTest {

    @Nested
    @DisplayName("Given a double hashing probing with size=5 and a hash function h1 that always returns the given key and h2 that always returns its multiple of 7")
    inner class Init {

        private val h = doubleHashing(m = 5, h1 = { it }, h2 = { it * 7 })

        @Nested
        @DisplayName("Probing using small keys")
        inner class SmallKeys {

            @Test
            fun `probing with i=0`() {
                assertThat(h(0, 0)).isEqualTo(0)
                assertThat(h(1, 0)).isEqualTo(1)
                assertThat(h(2, 0)).isEqualTo(2)
                assertThat(h(3, 0)).isEqualTo(3)
                assertThat(h(4, 0)).isEqualTo(4)
            }

            @Test
            fun `probing with i=4`() {
                assertThat(h(0, 4)).isEqualTo(0)
                assertThat(h(1, 4)).isEqualTo(4)
                assertThat(h(2, 4)).isEqualTo(3)
                assertThat(h(3, 4)).isEqualTo(2)
                assertThat(h(4, 4)).isEqualTo(1)
            }

            @Test
            fun `probing with i=m`() {
                assertThat(h(0, 5)).isEqualTo(0)
                assertThat(h(1, 5)).isEqualTo(1)
                assertThat(h(2, 5)).isEqualTo(2)
                assertThat(h(3, 5)).isEqualTo(3)
                assertThat(h(4, 5)).isEqualTo(4)
            }

            @Test
            fun `probing with i greater than m`() {
                assertThat(h(0, 6)).isEqualTo(0)
                assertThat(h(1, 9)).isEqualTo(4)
                assertThat(h(2, 13)).isEqualTo(4)
                assertThat(h(3, 23)).isEqualTo(1)
                assertThat(h(4, 31)).isEqualTo(2)
            }

        }

        @Nested
        @DisplayName("Probing using big keys")
        inner class LargeKeys {

            @Test
            fun `probing with i=0`() {
                assertThat(h(11, 0)).isEqualTo(1)
                assertThat(h(17, 0)).isEqualTo(2)
                assertThat(h(37, 0)).isEqualTo(2)
                assertThat(h(54, 0)).isEqualTo(4)
                assertThat(h(71, 0)).isEqualTo(1)
            }

            @Test
            fun `probing with i=4`() {
                assertThat(h(11, 4)).isEqualTo(4)
                assertThat(h(17, 4)).isEqualTo(3)
                assertThat(h(37, 4)).isEqualTo(3)
                assertThat(h(54, 4)).isEqualTo(1)
                assertThat(h(71, 4)).isEqualTo(4)
            }

            @Test
            fun `probing with i=m`() {
                assertThat(h(11, 5)).isEqualTo(1)
                assertThat(h(17, 5)).isEqualTo(2)
                assertThat(h(37, 5)).isEqualTo(2)
                assertThat(h(54, 5)).isEqualTo(4)
                assertThat(h(71, 5)).isEqualTo(1)
            }

            @Test
            fun `probing with i greater than m`() {
                assertThat(h(11, 6)).isEqualTo(3)
                assertThat(h(17, 9)).isEqualTo(3)
                assertThat(h(37, 13)).isEqualTo(4)
                assertThat(h(54, 23)).isEqualTo(3)
                assertThat(h(71, 31)).isEqualTo(3)
            }

        }

    }

}