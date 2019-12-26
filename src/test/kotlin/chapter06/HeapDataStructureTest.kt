package chapter06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class HeapDataStructureTest {

    @Nested
    @DisplayName("Build-Max-Heap")
    inner class BuildMaxHeap {
        @Test
        fun `build a Max-Heap already in order`() {
            val input = arrayOf(1, 2, 3, 4, 5, 6)

            val heap = MaxHeap.buildMaxHeap(input)

            // just make sure that all numbers are there
            assertThat(heap.length).isEqualTo(6)
            assertThat(heap.heapSize).isEqualTo(6)
            ensureHeapInvariant(heap)
            assertThat(input).containsExactly(6, 5, 3, 4, 2, 1)
        }

        @Test
        fun `build a Max-Heap that is out of order`() {
            val input = arrayOf(3, 1, 5, 4, 6, 2)

            val heap = MaxHeap.buildMaxHeap(input)

            // just make sure that all numbers are there
            assertThat(heap.length).isEqualTo(6)
            assertThat(heap.heapSize).isEqualTo(6)
            ensureHeapInvariant(heap)
            assertThat(input).containsExactly(6, 4, 5, 3, 1, 2)
        }

        @Test
        fun `build a Max-Heap that is in reverse order`() {
            val input = arrayOf(6, 5, 4, 3, 2, 1)

            val heap = MaxHeap.buildMaxHeap(input)

            // just make sure that all numbers are there
            assertThat(heap.length).isEqualTo(6)
            assertThat(heap.heapSize).isEqualTo(6)
            ensureHeapInvariant(heap)
            assertThat(input).containsExactly(6, 5, 4, 3, 2, 1)
        }

        private fun ensureHeapInvariant(heap: MaxHeap) {
            for (i in 0..((heap.heapSize / 2) - 1)) {
                val l = heap.left(i)
                val r = heap.right(i)

                if (l < heap.heapSize) {
                    // the parent is >= to its left child
                    assertThat(heap[i]).isGreaterThanOrEqualTo(heap[l])
                }
                if (r < heap.heapSize) {
                    // the parent is >= to its right child
                    assertThat(heap[i]).isGreaterThanOrEqualTo(heap[r])
                }
            }
        }
    }

    @Nested
    @DisplayName("Heap-Maximum")
    inner class HeapMaximum {

        @Test
        fun `with only one value`() {
            val input = arrayOf(7)

            val heap = MaxHeap.buildMaxHeap(input)

            assertThat(heap.heapMaximum()).isEqualTo(7)
        }

        @Test
        fun `with a longer array`() {
            val input = arrayOf(7, 5, 8, 19, 3, 1, 18)

            val heap = MaxHeap.buildMaxHeap(input)

            assertThat(heap.heapMaximum()).isEqualTo(19)
        }

    }

    @Nested
    @DisplayName("Heap-Extract-Max")
    inner class HeapExtractMax {

        @Test
        fun `with only one value`() {
            val input = arrayOf(7)

            val heap = MaxHeap.buildMaxHeap(input)

            // heapSize is decreased after the operation
            assertThat(heap.heapSize).isEqualTo(1)
            assertThat(heap.heapExtractMax()).isEqualTo(7)
            assertThat(heap.heapSize).isEqualTo(0)
        }

        @Test
        fun `with a longer array extracting one element`() {
            val input = arrayOf(7, 5, 8, 19, 3, 1, 18)

            val heap = MaxHeap.buildMaxHeap(input)


            // heapSize is decreased after the operation
            assertThat(heap.heapSize).isEqualTo(7)
            assertThat(heap.heapExtractMax()).isEqualTo(19)
            assertThat(heap.heapSize).isEqualTo(6)
        }

        @Test
        fun `with a longer array extracting all elements`() {
            val input = arrayOf(7, 5, 8, 19, 3, 1, 18)

            val heap = MaxHeap.buildMaxHeap(input)


            // heapSize is decreased after the operation
            assertThat(heap.heapSize).isEqualTo(7)
            assertThat(heap.heapExtractMax()).isEqualTo(19)
            assertThat(heap.heapExtractMax()).isEqualTo(18)
            assertThat(heap.heapExtractMax()).isEqualTo(8)
            assertThat(heap.heapExtractMax()).isEqualTo(7)
            assertThat(heap.heapExtractMax()).isEqualTo(5)
            assertThat(heap.heapExtractMax()).isEqualTo(3)
            assertThat(heap.heapExtractMax()).isEqualTo(1)
            assertThat(heap.heapSize).isEqualTo(0)
        }

    }

    @Nested
    @DisplayName("Max-Heap-Insert")
    inner class MaxHeapInsert {

        @Test
        fun `into an empty array`() {
            val input = emptyArray<Int>()

            val heap = MaxHeap.buildMaxHeap(input)

            assertThat(heap.heapSize).isEqualTo(0)

            heap.maxHeapInsert(3)

            // heapSize is increased after the operation
            assertThat(heap.heapSize).isEqualTo(1)
            assertThat(heap.heapMaximum()).isEqualTo(3)
        }

        @Test
        fun `into a longer array`() {
            val input = arrayOf(7, 5, 8, 19, 3, 1, 18)

            val heap = MaxHeap.buildMaxHeap(input)

            assertThat(heap.heapSize).isEqualTo(7)
            heap.maxHeapInsert(20)

            // heapSize is inceased after the operation
            assertThat(heap.heapSize).isEqualTo(8)
            // ... and there is a new maximum
            assertThat(heap.heapMaximum()).isEqualTo(20)
        }

    }

}
