package chapter04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class SquareMatrixTest {

    @Nested
    @DisplayName("Standard multiplication of square matrices O(n^3)")
    inner class StandardMultiplication {

        @Test
        fun `standard square matrix multiplication`() {
            val n = 4

            val a = SquareMatrix.ofSize(n) { i, _ -> i + 1 }
            val b = SquareMatrix.ofSize(n) { _, j -> j + 1 }

            val c = squareMatrixMultiply(a, b)

            assertThat(c.rows).isEqualTo(n)
            assertThat(c[0, 0]).isEqualTo(4)
            assertThat(c[0, 1]).isEqualTo(8)
            assertThat(c[0, 2]).isEqualTo(12)
            assertThat(c[0, 3]).isEqualTo(16)
            assertThat(c[1, 0]).isEqualTo(8)
            assertThat(c[1, 1]).isEqualTo(16)
            assertThat(c[1, 2]).isEqualTo(24)
            assertThat(c[1, 3]).isEqualTo(32)
            assertThat(c[2, 0]).isEqualTo(12)
            assertThat(c[2, 1]).isEqualTo(24)
            assertThat(c[2, 2]).isEqualTo(36)
            assertThat(c[2, 3]).isEqualTo(48)
            assertThat(c[3, 0]).isEqualTo(16)
            assertThat(c[3, 1]).isEqualTo(32)
            assertThat(c[3, 2]).isEqualTo(48)
            assertThat(c[3, 3]).isEqualTo(64)
        }

        @Test
        fun `standard square matrix multiplication, from exercise 4 2-1`() {
            val a = SquareMatrix.from(
                    arrayOf(
                        arrayOf(1, 3),
                        arrayOf(7, 5)
                    )
            )

            val b = SquareMatrix.from(
                    arrayOf(
                            arrayOf(6, 8),
                            arrayOf(4, 2)
                    )
            )

            val c = squareMatrixMultiply(a, b)

            assertThat(c.rows).isEqualTo(2)
            assertThat(c[0, 0]).isEqualTo(18)
            assertThat(c[0, 1]).isEqualTo(14)
            assertThat(c[1, 0]).isEqualTo(62)
            assertThat(c[1, 1]).isEqualTo(66)
        }

        @Test
        @Tag("performance")
        fun `standard square matrix multiplication (large input)`() {
            val n = 512

            val a = SquareMatrix.ofSize(n) { i, _ -> i + 1 }
            val b = SquareMatrix.ofSize(n) { _, j -> j + 1 }

           /*val c =*/ squareMatrixMultiply(a, b)
        }

    }

}