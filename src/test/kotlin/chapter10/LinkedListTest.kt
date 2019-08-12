package chapter10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LinkedListTest {

    @Nested
    @DisplayName("Given an empty LinkedList")
    inner class LinkedListSizeZero {

        private val L = LinkedList()

        @Test
        fun `size is ZERO`() {
            assertThat(L.size).isEqualTo(0)
        }

        @Test
        fun `search does not find element`() {
            assertThat(L.search(5)).isFalse()
        }

        @Nested
        @DisplayName("After inserting a value")
        inner class LinkedListAfterInsert {

            init {
                L.insert(5)
            }

            @Test
            fun `size is now ONE`() {
                assertThat(L.size).isEqualTo(1)
            }

            @Test
            fun `search for 5 finds an element`() {
                assertThat(L.search(5)).isTrue()
            }

            @Test
            fun `search for other number does not find an element`() {
                assertThat(L.search(1)).isFalse()
            }

        }

    }

    @Nested
    @DisplayName("Given a LinkedList with 3 values")
    inner class LinkedListSizeThree {

        private val L = LinkedList()

        init {
            L.insert(1)
            L.insert(2)
            L.insert(3)
        }

        @Test
        fun `size is THREE`() {
            assertThat(L.size).isEqualTo(3)
        }

        @Test
        fun `search finds all elements`() {
            assertThat(L.search(1)).isTrue()
            assertThat(L.search(2)).isTrue()
            assertThat(L.search(3)).isTrue()
        }

        @Nested
        @DisplayName("After deleting 2")
        inner class DeleteTwo {

            init {
                L.delete(2)
            }

            @Test
            fun `size is now TWO`() {
                assertThat(L.size).isEqualTo(2)
            }

            @Test
            fun `search for 2 now fails`() {
                assertThat(L.search(2)).isFalse()
            }

            @Test
            fun `search for 1 and 3 still finds results`() {
                assertThat(L.search(1)).isTrue()
                assertThat(L.search(3)).isTrue()
            }

        }

    }

}