package chapter11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class OpenAddressingTableTest {

    @Nested
    @DisplayName("Given an empty OpenAddressingTable")
    inner class OpenAddressingTableSizeZero {

        private val T = OpenAddressingTable(5)

        @Test
        fun `searching for several keys yields NIL`() {
            assertThat(T.search(1)).isNull()
            assertThat(T.search(15)).isNull()
            assertThat(T.search(5)).isNull()
            assertThat(T.search(27)).isNull()
            assertThat(T.search(33)).isNull()
        }

        @Nested
        @DisplayName("After inserting values (1, 5, 15, 27, 33) to fill up the table")
        inner class AfterInsert {

            init {
                T.insert(1)
                T.insert(5)
                T.insert(15)
                T.insert(27)
                T.insert(33)
            }

            @Test
            fun `searching for the new values finds them`() {
                assertThat(T.search(1)).isNotNull()
                assertThat(T.search(5)).isNotNull()
                assertThat(T.search(15)).isNotNull()
                assertThat(T.search(27)).isNotNull()
                assertThat(T.search(33)).isNotNull()
            }

            @Test
            fun `searching for a value that was not inserted yields NIL`() {
                assertThat(T.search(2)).isNull()
                assertThat(T.search(6)).isNull()
                assertThat(T.search(14)).isNull()
                assertThat(T.search(28)).isNull()
                assertThat(T.search(39)).isNull()
            }

            @Test
            fun `trying to add yet another element will fail due to the table being full`() {
                assertFails { T.insert(22) }
            }

        }

    }

}