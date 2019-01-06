package chapter08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CountingSortRangeTest {

    lateinit var range: CountingSortRange

    @Nested
    @DisplayName("Example 1")
    inner class Example1 {

        @Test
        fun `creates a range from the set (6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2)`() {
            val input = arrayOf(6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2)

            range = createRangeUsingCountingSort(input, k=6)
        }

        @Nested
        @DisplayName("Checks the contents")
        inner class Checks1 {

            @Test
            fun `range 0 to 6 contains 11 elements`() {
                assertThat(range.countWithinRange(0 to 6)).isEqualTo(11)
            }

            @Test
            fun `range -10 to -7 contains 0 elements`() {
                assertThat(range.countWithinRange(-10 to -7)).isEqualTo(0)
            }

            @Test
            fun `range 7 to 10 contains 0 elements`() {
                assertThat(range.countWithinRange(7 to 10)).isEqualTo(0)
            }

            @Test
            fun `range 2 to 2 contains 2 elements`() {
                assertThat(range.countWithinRange(2 to 2)).isEqualTo(2)
            }

            @Test
            fun `range 5 to 5 contains 0 elements`() {
                assertThat(range.countWithinRange(5 to 5)).isEqualTo(0)
            }

            @Test
            fun `range 4 to 4 contains 1 element`() {
                assertThat(range.countWithinRange(4 to 4)).isEqualTo(1)
            }

            @Test
            fun `range -10 to 10 contains 11 element`() {
                assertThat(range.countWithinRange(-10 to 10)).isEqualTo(11)
            }


            @Test
            fun `range 3 to 6 contains 5 elements`() {
                assertThat(range.countWithinRange(3 to 6)).isEqualTo(5)
            }

        }

    }

}
