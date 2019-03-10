package chapter10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class StackTest {

    @Nested
    @DisplayName("Given an empty Stack")
    inner class EmptyStack {

        private val S = Stack(0)

        @Test
        fun `size is ZERO`() {
            assertThat(S.size).isEqualTo(0)
        }

        @Test
        fun `empty yields TRUE`() {
            assertThat(S.empty).isEqualTo(true)
        }

        @Test
        fun `full yields TRUE`() {
            assertThat(S.full).isEqualTo(true)
        }

        @Test
        fun `pop() causes an error`() {
            assertFails { S.pop() }
        }

        @Test
        fun `push() causes an error`() {
            assertFails { S.push(1) }
        }

    }

    @Nested
    @DisplayName("Given a Stack of Size 3")
    inner class NormalStack {

        private val S = Stack(3)

        @Test
        fun `size starts at ZERO`() {
            assertThat(S.size).isEqualTo(0)
        }

        @Test
        fun `empty yields TRUE`() {
            assertThat(S.empty).isEqualTo(true)
        }

        @Test
        fun `full yields FALSE`() {
            assertThat(S.full).isEqualTo(false)
        }

        @Test
        fun `pop() causes an error`() {
            assertFails { S.pop() }
        }

        @Nested
        @DisplayName("After pushing [1, 2, 3]")
        inner class Add123 {

            init {
                S.push(1)
                S.push(2)
                S.push(3)
            }

            @Test
            fun `empty now yields FALSE`() {
                assertThat(S.empty).isEqualTo(false)
            }

            @Test
            fun `full now yields TRUE`() {
                assertThat(S.full).isEqualTo(true)
            }

            @Test
            fun `size is now THREE`() {
                assertThat(S.size).isEqualTo(3)
            }

            @Test
            fun `push() causes an error`() {
                assertFails { S.push(10) }
            }

        }

        @Nested
        @DisplayName("Popping one value")
        inner class PoppingOneValue {

            private var x: Int = S.pop()

            @Test
            fun `empty still yields FALSE`() {
                assertThat(S.empty).isEqualTo(false)
            }

            @Test
            fun `full now yields FALSE again`() {
                assertThat(S.full).isEqualTo(false)
            }

            @Test
            fun `size is now TWO`() {
                assertThat(S.size).isEqualTo(2)
            }

            @Test
            fun `popped value was THREE`() {
                assertThat(x).isEqualTo(3)
            }

        }

    }

}