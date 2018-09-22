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
    fun partition(i: Int, iLength: Int, j: Int, jLength: Int): SquareMatrix =
            SubSquareMatrix(this, i, iLength, j, jLength)

    operator fun plus(other: SquareMatrix) =
            SquareMatrix.ofSize(other.rows) { i, j ->
                this[i, j] + other[i, j]
            }

    /**
     * Assigns the values of another SquareMatrix to this one
     */
    fun assign(other: SquareMatrix) {
        for (i in 0 until other.rows) {
            for (j in 0 until other.rows) {
                this[i, j] = other[i, j]
            }
        }
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

/**
 * A Submatrix is a (squared) section of another squared matrix.
 */
private class SubSquareMatrix(
        private val delegate: SquareMatrix,
        private val iOffset: Int, private val iLength: Int,
        private val jOffset: Int, private val jLength: Int
) : SquareMatrix {

    init {
        if (iLength != jLength) {
            throw IllegalArgumentException("Partitioned Matrix is not square. It has $iLength rows and $jLength columns")
        }
    }

    override val rows = iLength

    override fun get(i: Int, j: Int): Int {
        if (i >= iLength) {
            throw IndexOutOfBoundsException("$i > matrix size of $rows")
        }
        if (j >= jLength) {
            throw IndexOutOfBoundsException("$j > matrix size of $rows")
        }
        return delegate[iOffset + i, jOffset + j]
    }

    override fun set(i: Int, j: Int, value: Int) {
        delegate[iOffset + i, jOffset + j] = value
    }

    /**
     * When partitioning a submatrix, we call the partition on the parent, so that we don't end up with infinite chains of calls after multiple partitions.
     */
    override fun partition(i: Int, iLength: Int, j: Int, jLength: Int): SquareMatrix {
        return delegate.partition(iOffset + i, iLength, jOffset + j, jLength)
    }

}

/**
 * A SquareMatrix made up of joined partitions. The partitions are grouped as: top-left, top-right, bottom-left, bottom-right.
 * It is expected that all the partitions have the same dimensions.
 */
private class JoinedSubSquareMatrix(
        private val topLeft: SquareMatrix,
        private val topRight: SquareMatrix,
        private val bottomLeft: SquareMatrix,
        private val bottomRight: SquareMatrix
) : SquareMatrix {

    override val rows = topLeft.rows + topRight.rows

    override operator fun get(i: Int, j: Int): Int {
        val mid = rows / 2
        return getSection(i, j)[i % mid, j % mid]
    }

    override operator fun set(i: Int, j: Int, value: Int) {
        val mid = rows / 2
        getSection(i, j)[i % mid, j % mid] = value
    }

    private fun getSection(i: Int, j: Int): SquareMatrix {
        checkIndex(i, j)

        val mid = rows / 2

        return when {
            i in 0 until mid && j in 0 until mid -> topLeft
            i in 0 until mid && j in mid until rows -> topRight
            i in mid until rows && j in 0 until mid -> bottomLeft
            i in mid until rows && j in mid until rows -> bottomRight
            else -> throw IndexOutOfBoundsException("Indexes ($i, $j) not within 0..$rows")
        }
    }

    private fun checkIndex(i: Int, j: Int) {
        if (i !in 0 until rows) {
            throw IndexOutOfBoundsException("Index $i not within 0..$rows")
        }
        if (j !in 0 until rows) {
            throw IndexOutOfBoundsException("Index $j not within 0..$rows")
        }
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

fun squareMatrixMultiplyRecursive(A: SquareMatrix, B: SquareMatrix): SquareMatrix {
    val n = A.rows

    if (n == 1) {
        return SquareMatrix.ofSize(n) { _, _ -> A[0, 0] * B[0, 0] }
    }

    val mid = n / 2

    // NOTE: to truly take advantage of this recursive approach, we should ONCE create the output Matrix, and all other times just modify the output Matrix partitions
    val c11 = squareMatrixMultiplyRecursive(A.partition(0, mid, 0, mid), B.partition(0, mid, 0, mid)) + squareMatrixMultiplyRecursive(A.partition(0, mid, mid, mid), B.partition(mid, mid, 0, mid))
    val c12 = squareMatrixMultiplyRecursive(A.partition(0, mid, 0, mid), B.partition(0, mid, mid, mid)) + squareMatrixMultiplyRecursive(A.partition(0, mid, mid, mid), B.partition(mid, mid, mid, mid))
    val c21 = squareMatrixMultiplyRecursive(A.partition(mid, mid, 0, mid), B.partition(0, mid, 0, mid)) + squareMatrixMultiplyRecursive(A.partition(mid, mid, mid, mid), B.partition(mid, mid, 0, mid))
    val c22 = squareMatrixMultiplyRecursive(A.partition(mid, mid, 0, mid), B.partition(0, mid, mid, mid)) + squareMatrixMultiplyRecursive(A.partition(mid, mid, mid, mid), B.partition(mid, mid, mid, mid))

    return JoinedSubSquareMatrix(c11, c12, c21, c22)
}

/**
 * This version of the recursive algorithm does not recreate the resulting Matrix C at every recursion, applying the calculations diredctly to the results.
 * This means that this version can actually take advantage of the improvements the Divide-and-Conquer approach gives.
 */
fun squareMatrixMultiplyRecursive2(A: SquareMatrix, B: SquareMatrix): SquareMatrix {
    val n = A.rows

    val C = SquareMatrix.ofSize(n)
    squareMatrixMultiplyRecursive2_(A, B, C)
    return C
}

// A and B are the input for the multiplication, and C is the outcome of the multiplication
private fun squareMatrixMultiplyRecursive2_(A: SquareMatrix, B: SquareMatrix, C: SquareMatrix) {
    val n = A.rows

    if (n == 1) {
        // we always add up to C, since we already initialized with 0 (zero)
        C[0, 0] += A[0, 0] * B[0, 0]
        return
    }

    val mid = n / 2

    val A11 = A.partition(0, mid, 0, mid)
    val A12 = A.partition(0, mid, mid, mid)
    val A21 = A.partition(mid, mid, 0, mid)
    val A22 = A.partition(mid, mid, mid, mid)

    val B11 = B.partition(0, mid, 0, mid)
    val B12 = B.partition(0, mid, mid, mid)
    val B21 = B.partition(mid, mid, 0, mid)
    val B22 = B.partition(mid, mid, mid, mid)

    val C11 = C.partition(0, mid, 0, mid)
    val C12 = C.partition(0, mid, mid, mid)
    val C21 = C.partition(mid, mid, 0, mid)
    val C22 = C.partition(mid, mid, mid, mid)

    // below the recursive calls will already add up the multiplications, because of how we calculate at n==1

    // sum for C11
    squareMatrixMultiplyRecursive2_(A11, B11, C11)
    squareMatrixMultiplyRecursive2_(A12, B21, C11)

    // sum for C12
    squareMatrixMultiplyRecursive2_(A11, B12, C12)
    squareMatrixMultiplyRecursive2_(A12, B22, C12)

    // sum for C21
    squareMatrixMultiplyRecursive2_(A21, B11, C21)
    squareMatrixMultiplyRecursive2_(A22, B21, C21)

    // sum for C22
    squareMatrixMultiplyRecursive2_(A21, B12, C22)
    squareMatrixMultiplyRecursive2_(A22, B22, C22)
}

