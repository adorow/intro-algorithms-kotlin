package chapter14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class OrderStatisticTreeTest {

    @Nested
    @DisplayName("Given an empty Order-Statistic Tree")
    inner class EmptyTree {

        private val T = OrderStatisticTree()

        @Test
        fun `searching does not find anything`() {
            assertThat(T.iterativeSearch(5)).isNull()
            assertThat(T.search(5)).isNull()
        }

        @Test
        fun `select returns nothing`() {
            assertThat(T.osSelect(1)).isNull()
        }

    }

    @Nested
    @DisplayName("One element Order-Statistic Tree")
    inner class OneElementTree {

        private val T = OrderStatisticTree()

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
        fun `select 1st smallest element returns the only present element`() {
            assertThat(T.osSelect(1)?.key).isEqualTo(3)
        }

        @Test
        fun `rank of element 3 returns 1`() {
            assertThat(T.search(3)!!.osRank()).isEqualTo(1)
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
            fun `select returns nothing`() {
                assertThat(T.osSelect(1)).isNull()
            }

        }

    }

    @Nested
    @DisplayName("Few elements in random order in a Order-Statistic Tree")
    inner class FewRandomElementsTree {

        private val T = OrderStatisticTree()

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
        fun `select 1st smallest element returns 1`() {
            assertThat(T.osSelect(1)?.key).isEqualTo(1)
        }

        @Test
        fun `select 3rd smallest element returns 3`() {
            assertThat(T.osSelect(3)?.key).isEqualTo(3)
        }

        @Test
        fun `select 6th smallest element returns 6`() {
            assertThat(T.osSelect(6)?.key).isEqualTo(6)
        }

        @Test
        fun `select 7th smallest element returns nothing`() {
            assertThat(T.osSelect(7)).isNull()
        }

        @Test
        fun `rank of element 1 returns 1`() {
            assertThat(T.search(1)!!.osRank()).isEqualTo(1)
        }

        @Test
        fun `rank of element 3 returns 3`() {
            assertThat(T.search(3)!!.osRank()).isEqualTo(3)
        }

        @Test
        fun `rank of element 6 returns 6`() {
            assertThat(T.search(6)!!.osRank()).isEqualTo(6)
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
            fun `select 1st smallest element returns 1`() {
                assertThat(T.osSelect(1)?.key).isEqualTo(2)
            }

            @Test
            fun `select 2nd smallest element returns 4`() {
                assertThat(T.osSelect(2)?.key).isEqualTo(4)
            }

            @Test
            fun `select 3rd smallest element returns nothing`() {
                assertThat(T.osSelect(3)).isNull()
            }

            @Test
            fun `rank of element 2 returns 1`() {
                assertThat(T.search(2)!!.osRank()).isEqualTo(1)
            }

            @Test
            fun `rank of element 4 returns 2`() {
                assertThat(T.search(4)!!.osRank()).isEqualTo(2)
            }

        }

    }

    @Nested
    @DisplayName("Few elements in sequential order in a Order-Statistic Tree")
    inner class FewSequentialElementsTree {

        private val T = OrderStatisticTree()

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
        fun `select 1st smallest element returns 1`() {
            assertThat(T.osSelect(1)?.key).isEqualTo(1)
        }

        @Test
        fun `select 6th smallest element returns 6`() {
            assertThat(T.osSelect(6)?.key).isEqualTo(6)
        }

        @Test
        fun `rank of element 4 returns 4`() {
            assertThat(T.search(4)!!.osRank()).isEqualTo(4)
        }

        @Test
        fun `rank of element 7 returns 7`() {
            assertThat(T.search(7)!!.osRank()).isEqualTo(7)
        }

        @Nested
        @DisplayName("After deleting an element")
        inner class FewSequentialElementsAfterDeletingSomeElementsSequentially {

            init {
                T.rbDelete(T.search(3)!!)
                // 1-2 and 4-7 remain
            }

            @Test
            fun `searching the element no longer finds it`() {
                assertThat(T.iterativeSearch(3)).isNull()
                assertThat(T.search(3)).isNull()
            }

            @Test
            fun `select 1st smallest element returns 1`() {
                assertThat(T.osSelect(1)?.key).isEqualTo(1)
            }

            @Test
            fun `select 2nd smallest element returns 2`() {
                assertThat(T.osSelect(2)?.key).isEqualTo(2)
            }

            @Test
            fun `select 6th smallest element returns 6`() {
                assertThat(T.osSelect(6)?.key).isEqualTo(7)
            }

            @Test
            fun `select 7th smallest element returns nothing`() {
                assertThat(T.osSelect(7)).isNull()
            }

            @Test
            fun `rank of element 1 returns 1`() {
                assertThat(T.search(1)!!.osRank()).isEqualTo(1)
            }

            @Test
            fun `rank of element 4 returns 3`() {
                assertThat(T.search(4)!!.osRank()).isEqualTo(3)
            }

            @Test
            fun `rank of element 6 returns 5`() {
                assertThat(T.search(6)!!.osRank()).isEqualTo(5)
            }

        }

    }

}