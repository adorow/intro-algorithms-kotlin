package chapter10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class QueueTest {

    @Nested
    @DisplayName("Given a Queue of size 0")
    inner class QueueSizeZero {

        private val Q = Queue(0)

        @Test
        fun `size is ZERO`() {
            assertThat(Q.size).isEqualTo(0)
        }

        @Test
        fun `empty yields TRUE`() {
            assertThat(Q.empty).isEqualTo(true)
        }

        @Test
        fun `full yields TRUE`() {
            assertThat(Q.full).isEqualTo(true)
        }

        @Test
        fun `dequeue() causes an error`() {
            assertFails { Q.dequeue() }
        }

        @Test
        fun `enqueue() causes an error`() {
            assertFails { Q.enqueue(1) }
        }

    }

    @Nested
    @DisplayName("Given a Queue of Size 3")
    inner class QueueSizeThree {

        private val Q = Queue(3)

        @Test
        fun `size starts at ZERO`() {
            assertThat(Q.size).isEqualTo(0)
        }

        @Test
        fun `empty yields TRUE`() {
            assertThat(Q.empty).isEqualTo(true)
        }

        @Test
        fun `full yields FALSE`() {
            assertThat(Q.full).isEqualTo(false)
        }

        @Test
        fun `pop() causes an error`() {
            assertFails { Q.dequeue() }
        }

        @Nested
        @DisplayName("After enqueing [1, 2, 3]")
        inner class Add123 {

            init {
                Q.enqueue(1)
                Q.enqueue(2)
                Q.enqueue(3)
            }

            @Test
            fun `empty now yields FALSE`() {
                assertThat(Q.empty).isEqualTo(false)
            }

            @Test
            fun `full now yields TRUE`() {
                assertThat(Q.full).isEqualTo(true)
            }

            @Test
            fun `size is now THREE`() {
                assertThat(Q.size).isEqualTo(3)
            }

            @Test
            fun `enqueue() causes an error`() {
                assertFails { Q.enqueue(10) }
            }

            @Nested
            @DisplayName("Then dequeing one value")
            inner class ThenDequeingOneValue {

                private var x: Int = Q.dequeue()

                @Test
                fun `empty still yields FALSE`() {
                    assertThat(Q.empty).isEqualTo(false)
                }

                @Test
                fun `full now yields FALSE again`() {
                    assertThat(Q.full).isEqualTo(false)
                }

                @Test
                fun `size is now TWO`() {
                    assertThat(Q.size).isEqualTo(2)
                }

                @Test
                fun `dequeued value was ONE`() {
                    assertThat(x).isEqualTo(1)
                }

                @Nested
                @DisplayName("Then adding one more again")
                inner class ThenAddingOneMoreAgain {

                    init {
                        Q.enqueue(4)
                    }

                    @Test
                    fun `empty still yields FALSE`() {
                        assertThat(Q.empty).isEqualTo(false)
                    }

                    @Test
                    fun `full now yields TRUE again`() {
                        assertThat(Q.full).isEqualTo(true)
                    }

                    @Test
                    fun `size is now THREE`() {
                        assertThat(Q.size).isEqualTo(3)
                    }

                    @Nested
                    @DisplayName("Then dequeing three items to empty the Queue")
                    inner class ThenPoppingThreeItems {

                        init {
                            Q.dequeue()
                            Q.dequeue()
                            Q.dequeue()
                        }

                        @Test
                        fun `empty now yields TRUE`() {
                            assertThat(Q.empty).isEqualTo(true)
                        }

                        @Test
                        fun `full now yields FALSE again`() {
                            assertThat(Q.full).isEqualTo(false)
                        }

                        @Test
                        fun `size is now ZERO`() {
                            assertThat(Q.size).isEqualTo(0)
                        }

                    }

                }

            }

        }

    }

}