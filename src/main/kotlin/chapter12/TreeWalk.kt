package chapter12

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


