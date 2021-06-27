package chapter15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MemoizedCutRodTest {

    val P: Array<Int> = arrayOf(1, 5, 8, 9, 10, 17, 17, 20, 24, 30)

    @Test
    fun `n = 0 gets a revenue of 0`() {
        assertThat(memoizedCutRod(P, 0)).isEqualTo(0)
    }

    @Test
    fun `n = 1 gets a revenue of 1`() {
        assertThat(memoizedCutRod(P, 1)).isEqualTo(1)
    }

    @Test
    fun `n = 2 gets a revenue of 5`() {
        assertThat(memoizedCutRod(P, 2)).isEqualTo(5)
    }

    @Test
    fun `n = 3 gets a revenue of 8`() {
        assertThat(memoizedCutRod(P, 3)).isEqualTo(8)
    }

    @Test
    fun `n = 4 gets a revenue of 10`() {
        assertThat(memoizedCutRod(P, 4)).isEqualTo(10)
    }

    @Test
    fun `n = 5 gets a revenue of 13`() {
        assertThat(memoizedCutRod(P, 5)).isEqualTo(13)
    }

    @Test
    fun `n = 6 gets a revenue of 17`() {
        assertThat(memoizedCutRod(P, 6)).isEqualTo(17)
    }

    @Test
    fun `n = 7 gets a revenue of 18`() {
        assertThat(memoizedCutRod(P, 7)).isEqualTo(18)
    }

    @Test
    fun `n = 8 gets a revenue of 22`() {
        assertThat(memoizedCutRod(P, 8)).isEqualTo(22)
    }

    @Test
    fun `n = 9 gets a revenue of 25`() {
        assertThat(memoizedCutRod(P, 9)).isEqualTo(25)
    }

    @Test
    fun `n = 10 gets a revenue of 30`() {
        assertThat(memoizedCutRod(P, 10)).isEqualTo(30)
    }

}