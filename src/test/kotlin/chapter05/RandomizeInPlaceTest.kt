package chapter05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class RandomizeInPlaceTest {

    @Test
    fun `all values are still present`() {
        val input = mutableListOf(1, 2, 3, 4, 5, 6)

        randomizeInPlace(input)

        // just make sure that all numbers are there
        assertThat(input).hasSize(6)
        assertThat(input).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `permute sequential input with specific RNG`() {
        val input = mutableListOf(1, 2, 3, 4, 5, 6)

        randomizeInPlace(input, Random(1337))

        assertThat(input).containsExactly(2, 1, 5, 3, 4, 6)
    }

}