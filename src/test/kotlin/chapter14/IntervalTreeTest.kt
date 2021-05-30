package chapter14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class IntervalTreeTest {

    @Nested
    @DisplayName("Given an empty Interval Tree")
    inner class EmptyTree {

        private val T = IntervalTree()

        @Test
        fun `searching does not find anything`() {
            assertThat(T.intervalSearch(Interval(5, 10))).isNull()
        }

    }

    @Nested
    @DisplayName("Few elements in random order in a Interval Tree")
    inner class FewRandomElementsTree {

        private val T = IntervalTree()

        init {
            T.intervalInsert(Interval(25, 30))
            T.intervalInsert(Interval(8, 9))
            T.intervalInsert(Interval(0, 3))
            T.intervalInsert(Interval(6, 10))
            T.intervalInsert(Interval(5, 8))
            T.intervalInsert(Interval(19, 20))
            T.intervalInsert(Interval(17, 19))
            T.intervalInsert(Interval(16, 21))
            T.intervalInsert(Interval(15, 23))
            T.intervalInsert(Interval(26, 26))
        }

        @Test
        fun `searching for intervals before the start of the tree interval does not find anything`() {
            assertThat(T.intervalSearch(Interval(-2, -1))).isNull()
        }

        @Test
        fun `searching for intervals after the end of the tree interval does not find anything`() {
            assertThat(T.intervalSearch(Interval(31, 40))).isNull()
        }

        @Test
        fun `searching for intervals returns a match when overlapped perfectly`() {
            assertThat(T.intervalSearch(Interval(26, 26))).isNotNull // matches Interval(26, 26)
        }

        @Test
        fun `searching for intervals returns a match when the low endpoint of the given interval is inside of an interval in the tree`() {
            assertThat(T.intervalSearch(Interval(2, 4))).isNotNull // matches Interval(0, 3)
        }

        @Test
        fun `searching for intervals returns a match when the high endpoint of the given interval is inside of an interval in the tree`() {
            assertThat(T.intervalSearch(Interval(11, 15))).isNotNull // matches Interval(15, 23)
        }

        @Test
        fun `searching for intervals returns a match when the entire interval is contained in an interval in the tree`() {
            assertThat(T.intervalSearch(Interval(27, 29))).isNotNull // matches Interval(25, 30)
        }

        @Test
        fun `searching for intervals returns a match when the given interval contains an entire interval from the tree`() {
            assertThat(T.intervalSearch(Interval(-1, 4))).isNotNull // matches Interval(0, 3)
        }

    }

}