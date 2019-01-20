package chapter05

import ext.pow
import java.util.*
import kotlin.math.roundToInt

fun permuteBySorting(A: List<Int>, rng: Random = Random()): List<Int> {
    val n = A.size
    val bound = n.pow(3)

    // Here 'P' is a list of pairs rather than just a list, to keep the value and the sort key together
    val P = A.map { it to rng.nextInt(bound) }

    return P.asSequence().sortedBy { it.second }.map { it.first }.toList()
}