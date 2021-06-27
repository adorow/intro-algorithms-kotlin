package chapter15

fun memoizedCutRod(p: Array<Int>, n: Int): Int {
    val r = Array(n + 1) { Int.MIN_VALUE }

    return memoizedCutRodAux(p, n, r)
}

private fun memoizedCutRodAux(p: Array<Int>, n: Int, r: Array<Int>): Int {
    if (r[n] >= 0) {
        return r[n]
    }
    val q: Int
    if (n == 0) {
        q = 0
    } else {
        q = (0 until n)
            .map { i -> p[i] + memoizedCutRodAux(p, n - (i + 1), r) }
            .max() ?: Int.MIN_VALUE
    }
    r[n] = q

    return q
}
