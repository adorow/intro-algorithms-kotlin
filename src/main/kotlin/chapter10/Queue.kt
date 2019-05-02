package chapter10

class Queue(capacity: Int) {

    private var head: Int = 0
    private var tail: Int = 0
    private val length: Int = capacity + 1
    private val Q: Array<Int> = Array(length) { 0 }

    val empty
        get() : Boolean = head == tail

    val full
        get() : Boolean = head  == (tail + 1) % length

    val size: Int
        get() = if (head > tail) {
            (length - head) + tail
        } else {
            tail - head
        }

    fun enqueue(x: Int) {
        if (full) {
            throw IllegalStateException("overflow")
        }
        Q[tail] = x
        tail = (tail + 1) % length
    }

    fun dequeue(): Int {
        if (empty) {
            throw IllegalStateException("underflow")
        }
        val x = Q[head]
        head = (head + 1) % length
        return x
    }

}