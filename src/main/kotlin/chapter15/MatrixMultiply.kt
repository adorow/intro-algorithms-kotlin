package chapter15

interface Matrix {

    companion object {
        fun from(m: Array<Array<Int>>): Matrix =
            ArrayBasedMatrix(m)

        /**
         * Initializes a matrix of size rows x columns, and initializing it.
         *
         * @param rows the number of rows.
         * @param columns the number of columns.
         * @param init an optional initialization param. If absent will initialize the matrix with zeros.
         */
        fun ofSize(rows: Int, columns: Int, init: (Int, Int) -> Int = { _, _ -> 0 }): Matrix =
            ArrayBasedMatrix(
                Array(rows) { i ->
                    Array(columns) { j ->
                        init(i, j)
                    }
                }
            )
    }

    val rows: Int
    val columns: Int
    operator fun get(i: Int, j: Int): Int
    operator fun set(i: Int, j: Int, value: Int)
}

class ArrayBasedMatrix(private val m: Array<Array<Int>>) : Matrix {

    init {
        if (m.isEmpty() || m[0].isEmpty()) throw IllegalArgumentException("Given Matrix cannot have a dimension of zero")
        val columnSize = m[0].size
        for ((i, row) in m.withIndex()) {
            if (row.size != columnSize) {
                throw IllegalArgumentException("Number of columns doesn't match. Expected all columns to have length $columnSize, but the ${i}th row has size ${row.size}.")
            }
        }
    }

    override val rows = m.size
    override val columns = m[0].size

    override operator fun get(i: Int, j: Int): Int =
        m[i][j]

    override operator fun set(i: Int, j: Int, value: Int) {
        m[i][j] = value
    }
}

fun matrixMultiply(A: Matrix, B: Matrix): Matrix {
    if (A.columns != B.rows) throw IllegalArgumentException("incompatible dimensions")

    val C = Matrix.ofSize(A.rows, B.columns)
    for (i in 0..<A.rows) {
        for (j in 0..<B.columns) {
            C[i, j] = 0
            for (k in 0..<A.columns) {
                C[i, j] += A[i, k] * B[k, j]
            }
        }
    }
    return C
}
