package chapter14

import chapter14.OrderStatisticTree.Color.BLACK
import chapter14.OrderStatisticTree.Color.RED


class OrderStatisticTree {

    // "nil" node, a leaf
    val nil: OSNode = OSNode(0)
    private var root: OSNode = nil

    init {
        // updating pointers here, since the constructor definition of circular references to nil doesn't work (they are actually null on nil's instantiation)
        nil.left = nil
        nil.right = nil
        nil.p = nil
        nil.size = 0
    }

    private fun leftRotate(x: OSNode) {
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

        // -- Order-Statistic change --
        y.size = x.size
        x.size = x.left.size + x.right.size + 1
        // -- Order-Statistic change --
    }

    private fun rightRotate(y: OSNode) {
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

        // -- Order-Statistic change --
        x.size = y.size
        y.size = y.left.size + y.right.size + 1
        // -- Order-Statistic change --
    }

    fun rbInsert(v: Int) {
        val z = OSNode(key = v)
        var y = nil
        var x = root

        while (x != nil) {
            // -- Order-Statistic change --
            x.size++ // increment size on each node in the path of insertion
            // -- Order-Statistic change --
            y = x
            if (z.key < x.key) {
                x = x.left
            } else {
                x = x.right
            }
        }
        z.p = y
        if (y == nil) {
            // tree is empty
            root = z
        } else if (z.key < y.key) {
            y.left = z
        } else {
            y.right = z
        }

        z.left = nil
        z.right = nil
        z.color = RED
        rbInsertFixup(z)
    }

    @Suppress("NAME_SHADOWING")
    private fun rbInsertFixup(z: OSNode) {
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

    fun rbDelete(z: OSNode) {
        var y = z
        var yOriginalColor = y.color
        val x: OSNode

        if (z.left == nil) {
            x = z.right
            rbTransplant(z, z.right)
        } else if (z.right == nil) {
            x = z.left
            rbTransplant(z, z.left)
        } else {
            y = z.right.minimum()
            yOriginalColor = y.color
            x = y.right
            if (y.p == z) {
                x.p = y
            } else {
                rbTransplant(y, y.right)
                y.right = z.right
                y.right.p = y
            }
            rbTransplant(z, y)
            y.left = z.left
            y.left.p = y
            y.color = z.color
        }

        if (yOriginalColor == BLACK) {
            rbDeleteFixup(x)
        }

    }

    private fun rbTransplant(u: OSNode, v: OSNode?) {
        if (u.p == nil) {
            root = v!!
        } else if (u == u.p.left) {
            u.p.left = v!!
        } else {
            u.p.right = v!!
        }

        v.p = u.p
    }

    @Suppress("NAME_SHADOWING")
    private fun rbDeleteFixup(x: OSNode) {
        var x = x
        while (x != root && x.color == BLACK) {
            if (x == x.p.left) {
                var w = x.p.right
                if (w.color == RED) {
                    w.color = BLACK // case 1
                    x.p.color = RED // case 1
                    leftRotate(x.p) // case 1
                    w = x.p.right  // case 1
                }
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED // case 2
                    x = x.p // case 2
                } else {
                    if (w.right.color == BLACK) {
                        w.left.color = BLACK // case 3
                        w.color = RED // case 3
                        rightRotate(w) // case 3
                        w = x.p.right // case 3
                    }
                    w.color = x.p.color // case 4
                    x.p.color = BLACK // case 4
                    x.right.color = BLACK // case 4
                    leftRotate(x.p) // case 4
                    x = root // case 4
                }
            } else {
                var w = x.p.left
                if (w.color == RED) {
                    w.color = BLACK // case 1
                    x.p.color = RED // case 1
                    rightRotate(x.p) // case 1
                    w = x.p.left  // case 1
                }
                if (w.right.color == BLACK && w.left.color == BLACK) {
                    w.color = RED // case 2
                    x = x.p // case 2
                } else {
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK // case 3
                        w.color = RED // case 3
                        leftRotate(w) // case 3
                        w = x.p.left // case 3
                    }
                    w.color = x.p.color // case 4
                    x.p.color = BLACK // case 4
                    x.left.color = BLACK // case 4
                    rightRotate(x.p) // case 4
                    x = root // case 4
                }
            }
        }

        x.color = BLACK
    }

    // From 'BinarySearchTree'
    fun iterativeSearch(k: Int): OSNode? {
        var x = root
        while (x != nil && x.key != k) {
            x = if (k < x.key) {
                x.left
            } else {
                x.right
            }
        }
        return x.orNull
    }

    fun search(k: Int): OSNode? {
        return search_(root, k)
    }

    private fun search_(x: OSNode?, k: Int): OSNode? {
        if (x == nil || k == x!!.key) {
            return x.orNull
        }
        return when {
            k < x.key -> search_(x.left, k)
            else -> search_(x.right, k)
        }
    }

    fun minimum(): OSNode? {
        return when {
            (root == nil) -> null
            else -> root.minimum()
        }
    }

    fun maximum(): OSNode? {
        return when {
            (root == nil) -> null
            else -> root.maximum()
        }
    }

    fun osSelect(i: Int) = osSelect_(root, i)

    private fun osSelect_(x: OSNode, i: Int): OSNode? {
        if (x == nil) {
            return null
        }
        val r = x.left.size + 1
        return when {
            i == r -> x
            i < r -> osSelect_(x.left, i)
            else -> osSelect_(x.right, i - r)
        }
    }

    private fun osRank(x: OSNode): Int {
        var r = x.left.size + 1
        var y = x
        while (y != root) {
            if (y == y.p.right) {
                r += y.p.left.size + 1
            }
            y = y.p
        }
        return r
    }

    enum class Color {
        RED, BLACK
    }

    inner class OSNode(val key: Int) {

        var left: OSNode = nil
            internal set
        var right: OSNode = nil
            internal set
        var p: OSNode = nil
            internal set
        var color: Color = BLACK
            internal set
        var size: Int = 1
            internal set

        internal fun minimum(): OSNode =
            if (left == nil) this else left.minimum()

        internal fun maximum(): OSNode =
            if (right == nil) this else right.maximum()

        fun successor(): OSNode? {
            if (right != nil) {
                return right.minimum()
            }
            var x = this
            var y = x.p
            while (y != nil && x == y.right) {
                x = y
                y = y.p
            }
            return y.orNull
        }

        fun predecessor(): OSNode? {
            if (left != nil) {
                return left.maximum()
            }
            var x = this
            var y = x.p
            while (y != nil && x == y.left) {
                x = y
                y = y.p
            }
            return y.orNull
        }

        fun osRank(): Int = osRank(this)

        val orNull: OSNode?
            get() {
                return when (this) {
                    nil -> null
                    else -> this
                }
            }

    }

}
