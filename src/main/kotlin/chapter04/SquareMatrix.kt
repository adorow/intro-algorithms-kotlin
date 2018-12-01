package chapter04


interface SquareMatrix {
    companion object {

        fun from(m: Array<Array<Int>>): SquareMatrix =
                ArrayBasedSquareMatrix(m)

        /**
         * Initializes a square matrix of size n x n, and initializing it.
         *
         * @param n the size of the square matrix.
         * @param init an optional initialization param. If absent will initialize the matrix with zeros.
         */
        fun ofSize(n: Int, init: (Int, Int) -> Int = { _, _ -> 0 }): SquareMatrix =
                ArrayBasedSquareMatrix(
                        Array(n) { i ->
                            Array(n) { j ->
                                init(i, j)
                            }
                        }
                )

    }

    val rows: Int
    operator fun get(i: Int, j: Int): Int
    operator fun set(i: Int, j: Int, value: Int)

    operator fun plus(other: SquareMatrix) =
            SquareMatrix.ofSize(other.rows) { i, j ->
                this[i, j] + other[i, j]
            }

}

private class ArrayBasedSquareMatrix(private val m: Array<Array<Int>>) : SquareMatrix {

    init {
        val size = m.size
        for ((i, row) in m.withIndex()) {
            if (row.size != size) {
                throw IllegalArgumentException("Given Matrix is not square. Row with index $i has size ${row.size}, outer array has size $size")
            }
        }
    }

    override val rows = m.size

    override operator fun get(i: Int, j: Int): Int =
            m[i][j]

    override operator fun set(i: Int, j: Int, value: Int) {
        m[i][j] = value
    }

}

fun squareMatrixMultiply(A: SquareMatrix, B: SquareMatrix): SquareMatrix {
    val n = A.rows
    val C = SquareMatrix.ofSize(n)

    for (i in 0 until n) {
        for (j in 0 until n) {
            C[i, j] = 0
            for (k in 0 until n) {
                C[i, j] = C[i, j] + (A[i, k] * B[k, j])
            }
        }
    }
    return C
}

