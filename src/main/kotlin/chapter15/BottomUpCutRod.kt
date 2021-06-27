package chapter15

fun bottomUpCutRod(p: Array<Int>, n: Int): Int {
    val r = Array(n + 1) { Int.MIN_VALUE }
    r[0] = 0
    for (j in 1..n) {
        r[j] = (1..j)
            .map { i -> p[i - 1] + r[j - i] }
            .max() ?: Int.MIN_VALUE
    }

    return r[n]
}
