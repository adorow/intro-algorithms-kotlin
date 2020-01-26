package chapter12

class BinarySearchTree {

    private var root: Node? = null

    fun insert(v: Int) {
        val z = Node(key = v)
        var y: Node? = null
        var x = root

        while (x != null) {
            y = x
            if (z.key < x.key) {
                x = x.left
            } else {
                x = x.right
            }
        }
        z.p = y
        if (y == null) {
            // tree is empty
            root = z
        } else if (z.key < y.key) {
            y.left = z
        } else {
            y.right = z
        }
    }

    fun delete(z: Node) {
        if (z.left == null) {
            transplant(z, z.right)
        } else if (z.right == null) {
            transplant(z, z.left)
        } else {
            val y = z.right!!.minimum()
            if (y.p != null) {
                transplant(y, y.right)
                y.right = z.right
                y.right!!.p = y
            }
            transplant(z, y)
            y.left = z.left
            y.left!!.p = y
        }
    }

    private fun transplant(u: Node, v: Node?) {
        if (u.p == null) {
            root = v
        } else if (u == u.p!!.left) {
            u.p!!.left = v
        } else {
            u.p!!.right = v
        }

        if (v != null) {
            v.p = u.p
        }
    }

    fun iterativeSearch(k: Int): Node? {
        var x = root
        while (x != null && x.key != k) {
            x = if (k < x.key) {
                x.left
            } else {
                x.right
            }
        }
        return x
    }

    fun search(k: Int): Node? {
        return search_(root, k)
    }

    private fun search_(x: Node?, k: Int): Node? {
        if (x == null || k == x.key) {
            return x
        }
        return when {
            k < x.key -> search_(x.left, k)
            else -> search_(x.right, k)
        }
    }

    fun minimum(): Node? {
        return root?.minimum()
    }

    fun maximum(): Node? {
        return root?.maximum()
    }

    class Node(val key: Int) {

        var left: Node? = null
            internal set
        var right: Node? = null
            internal set
        var p: Node? = null
            internal set

        internal fun minimum(): Node =
                left?.minimum() ?: this

        internal fun maximum(): Node =
                right?.maximum() ?: this

        fun successor(): Node? {
            if (right != null) {
                return right!!.minimum()
            }
            var x = this
            var y = x.p
            while (y != null && x == y.right) {
                x = y
                y = y.p
            }
            return y
        }

        fun predecessor(): Node? {
            if (left != null) {
                return left!!.maximum()
            }
            var x = this
            var y = x.p
            while (y != null && x == y.left) {
                x = y
                y = y.p
            }
            return y
        }

    }

}

