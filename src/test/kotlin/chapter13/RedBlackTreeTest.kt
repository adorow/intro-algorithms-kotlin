package chapter13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RedBlackTreeTest {

    @Nested
    @DisplayName("Given an empty Red-Black Tree")
    inner class EmptyTree {

        private val T = RedBlackTree()

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
    @DisplayName("One element Red-Black Tree")
    inner class OneElementTree {

        private val T = RedBlackTree()

        init {
            T.rbInsert(3)
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
                T.rbDelete(T.search(3)!!)
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
    @DisplayName("Few elements in random order in a Red-Black Tree")
    inner class FewRandomElementsTree {

        private val T = RedBlackTree()

        init {
            T.rbInsert(3)
            T.rbInsert(1)
            T.rbInsert(5)
            T.rbInsert(4)
            T.rbInsert(6)
            T.rbInsert(2)
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
                T.rbDelete(T.search(3)!!)
                T.rbDelete(T.search(6)!!)
                T.rbDelete(T.search(5)!!)
                T.rbDelete(T.search(1)!!)
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

    @Nested
    @DisplayName("Few elements in sequential order in a Red-Black Tree")
    inner class FewSequentialElementsTree {

        private val T = RedBlackTree()

        init {
            T.rbInsert(1)
            T.rbInsert(2)
            T.rbInsert(3)
            T.rbInsert(4)
            T.rbInsert(5)
            T.rbInsert(6)
            T.rbInsert(7)
        }

        @Test
        fun `searching not present element does not find anything`() {
            assertThat(T.iterativeSearch(9)).isNull()
            assertThat(T.search(9)).isNull()
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
        fun `searching the successor of 7 finds nothing`() {
            assertThat(T.search(7)!!.successor()).isNull()
        }

        @Test
        fun `searching the predecessor of 7 finds 6`() {
            assertThat(T.search(7)!!.predecessor()!!.key).isEqualTo(6)
        }

        @Test
        fun `minimum returns the smallest element`() {
            assertThat(T.minimum()?.key).isEqualTo(1)
        }

        @Test
        fun `maximum returns the largest element`() {
            assertThat(T.maximum()?.key).isEqualTo(7)
        }

        @Nested
        @DisplayName("After deleting some elements sequentially")
        inner class FewSequentialElementsAfterDeletingSomeElementsSequentially {

            init {
                T.rbDelete(T.search(1)!!)
                T.rbDelete(T.search(2)!!)
                T.rbDelete(T.search(3)!!)
                T.rbDelete(T.search(4)!!)
                T.rbDelete(T.search(5)!!)
                // only 6 and 7 remain
            }

            @Test
            fun `searching the element no longer finds it`() {
                assertThat(T.iterativeSearch(5)).isNull()
                assertThat(T.search(5)).isNull()
            }

            @Test
            fun `searching the successor of 6 finds 7`() {
                assertThat(T.search(6)!!.successor()!!.key).isEqualTo(7)
            }

            @Test
            fun `searching the predecessor of 6 finds nothing`() {
                assertThat(T.search(6)!!.predecessor()).isNull()
            }

            @Test
            fun `searching the successor of 7 finds nothing`() {
                assertThat(T.search(7)!!.successor()).isNull()
            }

            @Test
            fun `searching the predecessor of 7 finds 6`() {
                assertThat(T.search(7)!!.predecessor()!!.key).isEqualTo(6)
            }

            @Test
            fun `minimum returns 6`() {
                assertThat(T.minimum()?.key).isEqualTo(6)
            }

            @Test
            fun `maximum returns 7`() {
                assertThat(T.maximum()?.key).isEqualTo(7)
            }

        }

    }

}