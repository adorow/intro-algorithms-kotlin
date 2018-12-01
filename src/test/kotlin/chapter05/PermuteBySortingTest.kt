package chapter05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class PermuteBySortingTest {

    @Test
    fun `all values are still present`() {
        val input = listOf(1, 2, 3, 4, 5, 6)

        val result = permuteBySorting(input)

        // just make sure that all numbers are there
        assertThat(result).hasSize(6)
        assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `permute sequential input with specific RNG`() {
        val input = listOf(1, 2, 3, 4, 5, 6)

        val result = permuteBySorting(input, Random(1337))

        assertThat(result).containsExactly(6, 2, 5, 1, 4, 3)
    }

}