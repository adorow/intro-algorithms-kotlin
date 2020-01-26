package chapter12

import chapter12.BinarySearchTree.Node


fun inorderTreeWalk(x: Node?, visit: (Node) -> Unit = { print(it.key) }) {
    x?.apply {
        inorderTreeWalk(left)
        visit(this)
        inorderTreeWalk(right)
    }
}

fun preorderTreeWalk(x: Node?, visit: (Node) -> Unit = { print(it.key) }) {
    x?.apply {
        visit(this)
        preorderTreeWalk(left)
        preorderTreeWalk(right)
    }
}

fun postorderTreeWalk(x: Node?, visit: (Node) -> Unit = { print(it.key) }) {
    x?.apply {
        postorderTreeWalk(left)
        postorderTreeWalk(right)
        visit(this)
    }
}


