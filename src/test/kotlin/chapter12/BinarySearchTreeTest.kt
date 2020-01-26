package chapter12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    @Nested
    @DisplayName("Given an empty Binary Search Tree")
    inner class EmptyTree {

        private val T = BinarySearchTree()

        @Test
        fun `searching does not find anything`() {
            assertThat(T.iterativeSearch(5)).isNull()
            assertThat(T.search(5)).isNull()
        }

        @Test
        fun `minimum returns nothing`() {
            assertThat(T.minimum()).isNull()
        }

        @Test
        fun `maximum returns nothing`() {
            assertThat(T.maximum()).isNull()
        }

    }

    @Nested
    @DisplayName("One element Binary Search Tree")
    inner class OneElementTree {

        private val T = BinarySearchTree()

        init {
            T.insert(3)
        }

        @Test
        fun `searching not present element does not find anything`() {
            assertThat(T.iterativeSearch(5)).isNull()
            assertThat(T.search(5)).isNull()
        }

        @Test
        fun `searching present element finds it`() {
            assertThat(T.iterativeSearch(3)).isNotNull
            assertThat(T.search(3)).isNotNull
        }

        @Test
        fun `searching the successor of 3 finds nothing`() {
            assertThat(T.search(3)!!.successor()).isNull()
        }

        @Test
        fun `searching the predecessor of 3 finds nothing`() {
            assertThat(T.search(3)!!.predecessor()).isNull()
        }

        @Test
        fun `minimum returns the only present element`() {
            assertThat(T.minimum()?.key).isEqualTo(3)
        }

        @Test
        fun `maximum returns the only present element`() {
            assertThat(T.maximum()?.key).isEqualTo(3)
        }

        @Nested
        @DisplayName("After deleting that element")
        inner class OneElementTreeAfterDeleting {

            init {
                T.delete(T.search(3)!!)
            }

            @Test
            fun `searching the element no longer finds it`() {
                assertThat(T.iterativeSearch(3)).isNull()
                assertThat(T.search(3)).isNull()
            }

            @Test
            fun `minimum returns nothing`() {
                assertThat(T.minimum()).isNull()
            }

            @Test
            fun `maximum returns nothing`() {
                assertThat(T.maximum()).isNull()
            }

        }

    }

    @Nested
    @DisplayName("Few elements in a Binary Search Tree")
    inner class FewElementsTree {

        private val T = BinarySearchTree()

        init {
            T.insert(3)
            T.insert(1)
            T.insert(5)
            T.insert(4)
            T.insert(6)
            T.insert(2)
        }

        @Test
        fun `searching not present element does not find anything`() {
            assertThat(T.iterativeSearch(7)).isNull()
            assertThat(T.search(7)).isNull()
        }

        @Test
        fun `searching present element finds it`() {
            assertThat(T.iterativeSearch(4)).isNotNull
            assertThat(T.search(4)).isNotNull
        }

        @Test
        fun `searching the successor of 3 finds 4`() {
            assertThat(T.search(3)!!.successor()!!.key).isEqualTo(4)
        }

        @Test
        fun `searching the predecessor of 3 finds 2`() {
            assertThat(T.search(3)!!.predecessor()!!.key).isEqualTo(2)
        }

        @Test
        fun `searching the successor of 1 finds 2`() {
            assertThat(T.search(1)!!.successor()!!.key).isEqualTo(2)
        }

        @Test
        fun `searching the predecessor of 1 finds nothing`() {
            assertThat(T.search(1)!!.predecessor()).isNull()
        }

        @Test
        fun `searching the successor of 6 finds nothing`() {
            assertThat(T.search(6)!!.successor()).isNull()
        }

        @Test
        fun `searching the predecessor of 6 finds 5`() {
            assertThat(T.search(6)!!.predecessor()!!.key).isEqualTo(5)
        }

        @Test
        fun `minimum returns the smallest element`() {
            assertThat(T.minimum()?.key).isEqualTo(1)
        }

        @Test
        fun `maximum returns the largest element`() {
            assertThat(T.maximum()?.key).isEqualTo(6)
        }

        @Nested
        @DisplayName("After deleting some elements")
        inner class FewElementsAfterDeletingSomeElements {

            init {
                T.delete(T.search(3)!!)
                T.delete(T.search(6)!!)
                T.delete(T.search(5)!!)
                T.delete(T.search(1)!!)
            }

            @Test
            fun `searching the element no longer finds it`() {
                assertThat(T.iterativeSearch(5)).isNull()
                assertThat(T.search(5)).isNull()
            }

            @Test
            fun `searching the successor of 2 finds 4`() {
                assertThat(T.search(2)!!.successor()!!.key).isEqualTo(4)
            }

            @Test
            fun `searching the predecessor of 2 finds nothing`() {
                assertThat(T.search(2)!!.predecessor()).isNull()
            }

            @Test
            fun `searching the successor of 4 finds nothing`() {
                assertThat(T.search(4)!!.successor()).isNull()
            }

            @Test
            fun `searching the predecessor of 4 finds 2`() {
                assertThat(T.search(4)!!.predecessor()!!.key).isEqualTo(2)
            }

            @Test
            fun `minimum returns 2`() {
                assertThat(T.minimum()?.key).isEqualTo(2)
            }

            @Test
            fun `maximum returns 4`() {
                assertThat(T.maximum()?.key).isEqualTo(4)
            }

        }

    }

}