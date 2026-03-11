package chapter15

import chapter12.BinarySearchTree
import chapter12.Node
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test
import kotlin.math.abs

internal class OptimalBinarySearchTreeTest {

    @Test
    fun `verify that optimal-bst's outcome for figure 15_09 matches the book's figure 15_10`() {
        val p = arrayOf(0.15, 0.10, 0.05, 0.10, 0.20)
        val q = arrayOf(0.05, 0.10, 0.05, 0.05, 0.05, 0.10)
        val n = 5
        val (e, root) = optimalBst(p, q, n)

        val expectedE =
            arrayOf(
                arrayOf(0.05, 0.45, 0.90, 1.25, 1.75, 2.75),
                arrayOf(0.00, 0.10, 0.40, 0.70, 1.20, 2.00),
                arrayOf(0.00, 0.00, 0.05, 0.25, 0.60, 1.30),
                arrayOf(0.00, 0.00, 0.00, 0.05, 0.30, 0.90),
                arrayOf(0.00, 0.00, 0.00, 0.00, 0.05, 0.50),
                arrayOf(0.00, 0.00, 0.00, 0.00, 0.00, 0.10),
            )

        val expectedRoot =
            arrayOf(
                arrayOf(1, 1, 2, 2, 2),
                arrayOf(0, 2, 2, 2, 4),
                arrayOf(0, 0, 3, 4, 5),
                arrayOf(0, 0, 0, 4, 5),
                arrayOf(0, 0, 0, 0, 5),
            )

        SoftAssertions().apply {
            assertTablesMatch(expectedE, e) { a, b -> if (abs(a - b) < 0.000001) 0 else a.compareTo(b) }
            assertTablesMatch(expectedRoot, root, Int::compareTo)
        }.assertAll()

        val keys = arrayOf(1, 2, 3, 4, 5)

        val optimalBst = constructOptimalBst(root, keys)
        val expectedOptimalBst =
            BinarySearchTree(
                Node(2)
                    .withLeft(
                        Node(1)
                    )
                    .withRight(
                        Node(5)
                            .withLeft(
                                Node(4)
                                    .withLeft(
                                        Node(3)
                                    )
                            )
                    )
            )

        assertThat(optimalBst).isEqualTo(expectedOptimalBst)
    }

    private fun <E> SoftAssertions.assertTablesMatch(expected: Array<Array<E>>, actual: Array<Array<E>>, elementComparator: Comparator<E>) {
        assertThat(expected.size)
            .describedAs("has different number of rows")
            .isEqualTo(actual.size)

        for (i in 0..<expected.size) {
            assertThat(expected[i])
                // make the Double values matching within an error margin
                .usingElementComparator(elementComparator)
                .describedAs("has differences on row $i")
                .isEqualTo(actual[i])
        }
    }

}
