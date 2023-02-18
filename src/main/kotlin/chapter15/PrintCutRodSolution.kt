package chapter15

fun printCutRodSolution(p: Array<Int>, n: Int) {
    with (extendedBottomUpCutRod(p, n)) {
        var n = n
        while (n > 0) {
            print("${s[n]} ")
            n -= s[n]
        }
    }
}
