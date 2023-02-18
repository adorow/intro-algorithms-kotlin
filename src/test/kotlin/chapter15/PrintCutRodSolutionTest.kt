package chapter15

import org.junit.jupiter.api.Test

class PrintCutRodSolutionTest {

    val P: Array<Int> = arrayOf(1, 5, 8, 9, 10, 17, 17, 20, 24, 30)

    @Test
    fun `n = 10 prints 10`() {
        printCutRodSolution(P, 10)
    }

    @Test
    fun `n = 7 prints 1 and 6`() {
        printCutRodSolution(P, 7)
    }

}