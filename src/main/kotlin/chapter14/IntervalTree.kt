package chapter14

import chapter14.IntervalTree.Color.BLACK
import chapter14.IntervalTree.Color.RED

data class Interval(val low: Int, val high: Int)

class IntervalTree {

    // "nil" node, a leaf
    val nil: IntervalNode = IntervalNode(Interval(0, 0))
    private var root: IntervalNode = nil

    init {
        // updating pointers here, since the constructor definition of circular references to nil doesn't work (they are actually null on nil's instantiation)
        nil.left = nil
        nil.right = nil
        nil.p = nil
    }

    private fun leftRotate(x: IntervalNode) {
        val y = x.right // set y
        x.right = y.left // turn y's left subtree into x's right subtree

        if (y.left != nil) {
            y.left.p = x
        }

        y.p = x.p // link x's parent to y

        if (x.p == nil) {
            root = y
        } else if (x == x.p.left) {
            x.p.left = y
        } else {
            x.p.right = y
        }

        y.left = x // put x on y's left
        x.p = y

        // -- Interval Tree change --
        x.max = maxOf(x.int.high, x.left.max, x.right.max)
        y.max = maxOf(y.int.high, y.left.max, y.right.max)
        // -- Interval Tree change --
    }

    private fun rightRotate(y: IntervalNode) {
        val x = y.left // set x
        y.left = x.right // turn x's right subtree into y's left subtree

        if (x.right != nil) {
            x.right.p = y
        }

        x.p = y.p // link y's parent to x

        if (y.p == nil) {
            root = x
        } else if (y == y.p.right) {
            y.p.right = x
        } else {
            y.p.left = x
        }

        x.right = y // put y on x's left
        y.p = x

        // -- Interval Tree change --
        y.max = maxOf(y.int.high, y.left.max, y.right.max)
        x.max = maxOf(x.int.high, x.left.max, x.right.max)
        // -- Interval Tree change --
    }

    fun intervalInsert(i: Interval) {
        val z = IntervalNode(i)
        var y = nil
        var x = root

        while (x != nil) {
            y = x
            if (z.int.low < x.int.low) {
                x = x.left
            } else {
                x = x.right
            }
        }
        z.p = y
        if (y == nil) {
            // tree is empty
            root = z
        } else if (z.int.low < y.int.low) {
            y.left = z
        } else {
            y.right = z
        }

        z.left = nil
        z.right = nil
        z.color = RED
        // -- Interval Tree change --
        z.max = z.int.high
        if (z.p != nil) {
            z.p.max = maxOf(z.p.max, z.max)
        }
        // -- Interval Tree change --

        intervalInsertFixup(z)
    }

    @Suppress("NAME_SHADOWING")
    private fun intervalInsertFixup(z: IntervalNode) {
        var z = z // shadow to preserve nomenclature

        while (z.p.color == RED) {
            if (z.p == z.p.p.left) {
                val y = z.p.p.right
                if (y.color == RED) {
                    z.p.color = BLACK // case 1
                    y.color = BLACK // case 1
                    z.p.p.color = RED // case 1
                    z = z.p.p // case 1
                } else {
                    if (z == z.p.right) {
                        z = z.p // case 2
                        leftRotate(z) // case 2
                    }
                    z.p.color = BLACK // case 3
                    z.p.p.color = RED // case 3
                    rightRotate(z.p.p) // case 3
                }
            } else {
                val y = z.p.p.left
                if (y.color == RED) {
                    z.p.color = BLACK // case 1
                    y.color = BLACK // case 1
                    z.p.p.color = RED // case 1
                    z = z.p.p // case 1
                } else {
                    if (z == z.p.left) {
                        z = z.p // case 2
                        rightRotate(z) // case 2
                    }
                    z.p.color = BLACK // case 3
                    z.p.p.color = RED // case 3
                    leftRotate(z.p.p) // case 3
                }
            }
        }
        root.color = BLACK

    }

    fun intervalSearch(i: Interval): IntervalNode? {
        var x = root
        while (x != nil && !overlaps(x.int, i)) {
            x = if (x.left != nil && x.left.max >= i.low) {
                x.left
            } else {
                x.right
            }
        }
        return x.orNull
    }

    // We say that intervals _i_ and _i'_ overlap **_overlap_** if _i_ ∩ _i'_ ≠ 0, that is, if _i_._low_ ≤ _i'_._high_ and _i'_._low_ ≤ _i_._high_
    private fun overlaps(i: Interval, i_: Interval): Boolean =
        i.low <= i_.high && i_.low <= i.high

    enum class Color {
        RED, BLACK
    }

    inner class IntervalNode(val int: Interval) {

        var left: IntervalNode = nil
            internal set
        var right: IntervalNode = nil
            internal set
        var p: IntervalNode = nil
            internal set
        var color: Color = BLACK
            internal set
        var max: Int = 0
            internal set

        val orNull: IntervalNode?
            get() {
                return when (this) {
                    nil -> null
                    else -> this
                }
            }

    }

}
