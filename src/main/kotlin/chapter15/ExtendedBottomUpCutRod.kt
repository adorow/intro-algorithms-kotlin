package chapter15

fun extendedBottomUpCutRod(p: Array<Int>, n: Int): CutRodSolution {
    val r = Array(n + 1) { Int.MIN_VALUE }
    val s = Array(n + 1) { Int.MIN_VALUE }
    r[0] = 0
    s[0] = 0
    for (j in 1..n) {
        var q = Int.MIN_VALUE
        for (i in 1..j) {
            if (q < p[i - 1] + r[j - i]) {
                q = p[i - 1] + r[j - i]
                s[j] = i
            }
        }
        r[j] = q
    }

    return CutRodSolution(r, s)
}

data class CutRodSolution(
    val r: Array<Int>,
    val s: Array<Int>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CutRodSolution

        return r.contentEquals(other.r) && s.contentEquals(other.s)
    }

    override fun hashCode(): Int {
        var result = r.contentHashCode()
        result = 31 * result + s.contentHashCode()
        return result
    }
}
