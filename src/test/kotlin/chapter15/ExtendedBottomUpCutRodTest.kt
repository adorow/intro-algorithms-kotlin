package chapter15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ExtendedBottomUpCutRodTest {

    val P: Array<Int> = arrayOf(1, 5, 8, 9, 10, 17, 17, 20, 24, 30)

    @Test
    fun `n = 10 gets all the results`() {
        assertThat(extendedBottomUpCutRod(P, 10))
            .isEqualTo(
                CutRodSolution(
                    r = arrayOf(0, 1, 5, 8, 10, 13, 17, 18, 22, 25, 30),
                    s = arrayOf(0, 1, 2, 3, 2, 2, 6, 1, 2, 3, 10)
                )
            )
    }

}