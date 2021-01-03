package chapter13

import chapter13.RedBlackTree.Color.BLACK
import chapter13.RedBlackTree.Color.RED


class RedBlackTree {

    // "nil" node, a leaf
    val nil: RBNode = RBNode(0)
    private var root: RBNode = nil

    init {
        // updating pointers here, since the constructor definition of circular references to nil doesn't work (they are actually null on nil's instantiation)
        nil.left = nil
        nil.right = nil
        nil.p = nil
    }

    private fun leftRotate(x: RBNode) {
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
    }

    private fun rightRotate(y: RBNode) {
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
    }

    fun rbInsert(v: Int) {
        val z = RBNode(key = v)
        var y = nil
        var x = root

        while (x != nil) {
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
    private fun rbInsertFixup(z: RBNode) {
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

    fun rbDelete(z: RBNode) {
        var y = z
        var yOriginalColor = y.color
        val x: RBNode

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

    private fun rbTransplant(u: RBNode, v: RBNode?) {
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
    private fun rbDeleteFixup(x: RBNode) {
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
    fun iterativeSearch(k: Int): RBNode? {
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

    fun search(k: Int): RBNode? {
        return search_(root, k)
    }

    private fun search_(x: RBNode?, k: Int): RBNode? {
        if (x == nil || k == x!!.key) {
            return x.orNull
        }
        return when {
            k < x.key -> search_(x.left, k)
            else -> search_(x.right, k)
        }
    }

    fun minimum(): RBNode? {
        return when {
            (root == nil) -> null
            else -> root.minimum()
        }
    }

    fun maximum(): RBNode? {
        return when {
            (root == nil) -> null
            else -> root.maximum()
        }
    }

    enum class Color {
        RED, BLACK
    }

    inner class RBNode(val key: Int) {

        var left: RBNode = nil
            internal set
        var right: RBNode = nil
            internal set
        var p: RBNode = nil
            internal set
        var color: Color = BLACK
            internal set

        internal fun minimum(): RBNode =
                if (left == nil) this else left.minimum()

        internal fun maximum(): RBNode =
                if (right == nil) this else right.maximum()

        fun successor(): RBNode? {
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

        fun predecessor(): RBNode? {
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

        val orNull: RBNode?
            get() {
                return when (this) {
                    nil -> null
                    else -> this
                }
            }

    }

}
