package chapter15

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MatrixMultiplyTest {

    @Test
    fun `cannot multiply incompatible matrices`() {
        val by1 = Matrix.ofSize(1, 1)
        val by2 = Matrix.ofSize(2, 2)

        assertThrows<IllegalArgumentException> {
            matrixMultiply(by1, by2)
        }
    }

    @Test
    fun `should do a matrix multiplication`() {
        val a = Matrix.from(
            arrayOf(
                arrayOf(4, 3, 1),
                arrayOf(-5, 7, 2),
                arrayOf(3, 2, 6),
            )
        )
        val b = Matrix.from(
            arrayOf(
                arrayOf(1, 2, 2),
                arrayOf(-4, 3, -7),
                arrayOf(4, 6, -8),
            )
        )

        val result = matrixMultiply(a, b)

        assertThat(result.rows).isEqualTo(3)
        assertThat(result.columns).isEqualTo(3)

        assertThat(result[0, 0]).isEqualTo(-4)
        assertThat(result[0, 1]).isEqualTo(23)
        assertThat(result[0, 2]).isEqualTo(-21)
        assertThat(result[1, 0]).isEqualTo(-25)
        assertThat(result[1, 1]).isEqualTo(23)
        assertThat(result[1, 2]).isEqualTo(-75)
        assertThat(result[2, 0]).isEqualTo(19)
        assertThat(result[2, 1]).isEqualTo(48)
        assertThat(result[2, 2]).isEqualTo(-56)
    }
}
