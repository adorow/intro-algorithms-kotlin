package chapter10

class Stack(capacity: Int) {

    private var top: Int = 0
    private val S: Array<Int> = Array(capacity) { 0 }

    val empty
        get() : Boolean = top == 0

    val full
        get() : Boolean = top == S.size

    val size: Int
        get() = top

    fun push(x: Int) {
        if (full) {
            throw IllegalStateException("overflow")
        }
        S[top++] = x
    }

    fun pop(): Int {
        if (empty) {
            throw IllegalStateException("underflow")
        }
        return S[--top]
    }

}