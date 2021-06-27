package chapter15

fun bottomUpCutRod(p: Array<Int>, n: Int): Int {
    val r = Array(n + 1) { Int.MIN_VALUE }
    r[0] = 0
    for (j in 1..n) {
        var q = Int.MIN_VALUE
        for (i in 1..j) {
            q = maxOf(q, p[i - 1] + r[j - i])
        }
        r[j] = q
    }

    return r[n]
}
