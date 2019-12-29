package chapter11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ChainedHashTableTest {

    @Nested
    @DisplayName("Given an empty ChainedHashTable")
    inner class ChainedHashTableSizeZero {

        private val T = ChainedHashTable(10)

        @Test
        fun `searching for several keys does not find them`() {
            assertThat(T.search(1)).isFalse()
            assertThat(T.search(15)).isFalse()
            assertThat(T.search(5)).isFalse()
            assertThat(T.search(27)).isFalse()
            assertThat(T.search(33)).isFalse()
        }

        @Nested
        @DisplayName("After inserting values (1, 5, 15, 27)")
        inner class AfterInsert {

            init {
                T.insert(1)
                T.insert(5)
                T.insert(15)
                T.insert(27)
            }

            @Test
            fun `searching for the new values finds them`() {
                assertThat(T.search(1)).isTrue()
                assertThat(T.search(5)).isTrue()
                assertThat(T.search(15)).isTrue()
                assertThat(T.search(27)).isTrue()
            }

            @Test
            fun `searching for a value that was not added still does not find it`() {
                assertThat(T.search(33)).isFalse()
            }

            @Nested
            @DisplayName("After deleting some values")
            inner class AfterDeleting {

                init {
                    T.delete(3) // was never present
                    T.delete(5)
                    T.delete(27)
                }

                @Test
                fun `searching for the remaining values finds them`() {
                    assertThat(T.search(1)).isTrue()
                    assertThat(T.search(15)).isTrue()
                }

                @Test
                fun `searching for others does not find them`() {
                    assertThat(T.search(3)).isFalse()
                    assertThat(T.search(5)).isFalse()
                    assertThat(T.search(27)).isFalse()
                    assertThat(T.search(33)).isFalse()
                }

            }

        }

    }

}