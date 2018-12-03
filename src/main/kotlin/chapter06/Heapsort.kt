package chapter06

fun heapsort(A: Array<Int>) {
    val heap = MaxHeap.buildMaxHeap(A)
    for (i in heap.length - 1 downTo 1) {
        heap.swap(i, 0)
        heap.decrementHeapSize()
        heap.maxHeapify(0)
    }
}