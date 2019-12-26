package chapter10

import chapter11.DirectAddressTable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DirectAddressTableTest {

    @Nested
    @DisplayName("Given an empty DirectAddressTable")
    inner class LinkedListSizeZero {

        private val T = DirectAddressTable(10)

        @Test
        fun `searching any index yields NIL`() {
            for (i in 0 until T.size) {
                assertThat(T.search(i)).isEqualTo(null)
            }
        }

        @Nested
        @DisplayName("After inserting a value (5 at key=3)")
        inner class LinkedListAfterInsert {

            init {
                T.insert(3, 5)
            }

            @Test
            fun `searching at key=3 yields 5`() {
                assertThat(T.search(3)).isEqualTo(5)
            }

            @Test
            fun `searching other keys yields NIL`() {
                assertThat(T.search(1)).isEqualTo(null)
                assertThat(T.search(4)).isEqualTo(null)
                assertThat(T.search(7)).isEqualTo(null)
            }

            @Nested
            @DisplayName("After deleting key=3")
            inner class LinkedListSizeThree {

                init {
                    T.delete(3)
                }

                @Test
                fun `searching at key=3 yields NIL again`() {
                    assertThat(T.search(3)).isEqualTo(null)
                }

            }

        }

    }

}