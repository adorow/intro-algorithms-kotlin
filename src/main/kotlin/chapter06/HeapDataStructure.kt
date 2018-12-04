package chapter06

import ext.swap
import kotlin.math.max

/**
 * For the maths to work in this implementation, the top (max) of the Heap needs to be at index 1.
 * Therefore, in every calculation of indexes in the heap, this implementation is increasing the index to make the calculation, and then decreasing one from the result.
 *
 * An example is the Left(i) function. Which is defined as:
 * > (i * 2)
 * Which works when indexes start at 1 as the Head. Which will point to a left of index 2.
 * But if the indexes start at 0, this will result in 0 also, breaking the entire logic.
 *
 * This implementations keeps the original array as a part of itself, modifying it (as defined in the book).
 */
class MaxHeap private constructor(private var A: Array<Int>) {

    private var _heapSize: Int = A.size

    companion object {

        fun buildMaxHeap(A: Array<Int>): MaxHeap {
            val heap = MaxHeap(A)
            for (i in ((heap.length - 1) / 2) downTo 0) {
                heap.maxHeapify(i)
            }
            return heap
        }

    }

    val heapSize: Int
        get() = _heapSize

    val length: Int
        get() = this.A.size

    val innerArray: Array<Int>
        get() = A

    fun parent(i: Int) = ((i + 1) shr 1) - 1 // (i / 2) | considering index starting at 1
    fun left(i: Int) = ((i + 1) shl 1) - 1 // (i * 2) | considering index starting at 1
    fun right(i: Int) = ((i + 1) shl 1) // (i * 2) + 1 | considering index starting at 1

    operator fun get(i: Int) = A[i]
    operator fun set(i: Int, value: Int) {
        A[i] = value
    }

    fun swap(i1: Int, i2: Int) {
        A.swap(i1, i2)
    }

    fun decrementHeapSize() {
        _heapSize--
    }

    fun maxHeapify(i: Int) {
        val l = left(i)
        val r = right(i)

        var largest = when {
            l < heapSize && A[l] > A[i] -> l
            else -> i
        }

        if (r < heapSize && A[r] > A[largest]) {
            largest = r
        }

        if (largest != i) {
            swap(i, largest)
            maxHeapify(largest)
        }
    }

    fun heapMaximum(): Int = A[0]

    fun heapExtractMax(): Int {
        if (heapSize < 1) throw Exception("heap underflow")

        val max = A[0]
        A[0] = A[--_heapSize]
        maxHeapify(1)
        return max
    }

    private fun heapIncreaseKey(i: Int, key: Int) {
        if (key < A[i]) {
            throw Exception("new key is smaller than curent key")
        }
        A[i] = key

        var j = i
        while (j > 0 && A[parent(j)] < A[j]) {
            swap(j, parent(j))
            j = parent(j)
        }
    }

    fun maxHeapInsert(key: Int) {
        ensureCapacity(heapSize + 1)

        _heapSize++
        A[_heapSize - 1] = Int.MIN_VALUE
        heapIncreaseKey(_heapSize - 1, key)
    }

    private fun ensureCapacity(requiredCapacity: Int) {
        if (requiredCapacity > length) {
            val newSize = max(requiredCapacity, length * 2)
            A = Array(newSize) { i -> if (i < length) A[i] else 0 }
        }
    }

}