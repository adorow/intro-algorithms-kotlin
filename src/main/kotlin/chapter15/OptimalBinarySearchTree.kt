package chapter15

import chapter12.BinarySearchTree
import chapter12.Node
import kotlin.Double.Companion.POSITIVE_INFINITY

typealias SearchCostTable = Array<Array<Double>>
typealias RootTable = Array<Array<Int>>

// _p_ has n entries, and _q_ has n+1 entries
// p[i] is the probability that a search will be for key k[i]
// q[i] is the probability that a search will be for key d[i]
// d[i] is a dummy key representing search terms between k[i-1] and k[i],
//   hence _q_ has one additional entry
fun optimalBst(p: Array<Double>, q: Array<Double>, n: Int): Pair<SearchCostTable, RootTable> {
    if (p.size != n) throw IllegalArgumentException("Expected 'p' to be of size n=$n")
    if (q.size != n + 1) throw IllegalArgumentException("Expected 'q' to be of size (n+1)=${n + 1}")

    val e = Array(n + 1) { Array(n + 1) { 0.0 } }
    val w = Array(n + 1) { Array(n + 1) { 0.0 } }
    val root = Array(n) { Array(n) { 0 } }

    for (i in 1..n + 1) {
        e[i - 1][i - 1] = q[i - 1]
        w[i - 1][i - 1] = q[i - 1]
    }

    for (l in 1..n) {
        for (i in 1..n - l + 1) {
            val j = i + l - 1
            e[i - 1][j] = POSITIVE_INFINITY
            w[i - 1][j] = w[i - 1][j - 1] + p[j - 1] + q[j]
            for (r in i..j) {
                val t = e[i - 1][r - 1] + e[r][j] + w[i - 1][j]
                if (t < e[i - 1][j]) {
                    e[i - 1][j] = t
                    root[i - 1][j - 1] = r
                }
            }
        }
    }

    return e to root
}

fun constructOptimalBst(root: RootTable, elems: Array<Int>): BinarySearchTree {
    if (root.size != elems.size) {
        throw IllegalArgumentException("root's size should match elems' size")
    }

    return BinarySearchTree(
        constructOptimalBst(root, elems, 0, elems.size - 1)
    )
}

private fun constructOptimalBst(root: RootTable, elems: Array<Int>, l: Int, r: Int): Node? {
    if (l > r) return null

    val key = root[l][r] - 1
    return Node(elems[key])
            .withLeft(constructOptimalBst(root, elems, l, key - 1))
            .withRight(constructOptimalBst(root, elems, key+1, r))
}