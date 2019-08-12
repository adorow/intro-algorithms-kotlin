package chapter10

class LinkedList {

    private var head: Node? = null

    val size: Int
        get() = countNodes(head)

    private fun countNodes(node: Node?): Int {
        return if (node == null) {
            0
        } else {
            countNodes(node.next) + 1
        }
    }

    fun insert(k: Int) {
        val x = Node(k)
        x.next = head
        head?.prev = x
        head = x
        x.prev = null
    }

    fun delete(k: Int) {
        val x = nodeSearch(k) ?: return

        if (x.prev != null) {
            x.prev!!.next= x.next
        } else {
            head = x.next
        }

        x.next?.prev = x.prev
    }

    /** This implementation's public function returns True/False instead of the internal node as defined in the book */
    fun search(k: Int): Boolean {
        var x = head
        while (x != null && x.key != k) {
            x = x.next
        }
        return x != null
    }

    private fun nodeSearch(k: Int): Node? {
        var x = head
        while (x != null && x.key != k) {
            x = x.next
        }
        return x
    }

    private class Node(val key: Int, var prev: Node? = null, var next: Node? = null)

}