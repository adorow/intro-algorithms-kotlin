package chapter03

fun Int.factorial(): Int {
    if (this < 0) throw IllegalArgumentException()
    return factorial_(this)
}

// factorial was modified to take advantage of tail recursion optimization
private tailrec fun factorial_(n: Int, res: Int = 1): Int =
       if (n == 0) res else factorial_(n - 1, res * n)
