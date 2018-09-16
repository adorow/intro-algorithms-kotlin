package chapter03

import kotlin.coroutines.experimental.buildSequence

fun fibonacci() : Sequence<Int> =
        buildSequence {
            yield(0)
            yield(1)

            var a = 0
            var b = 1
            while (true) {
                val tmp = a + b
                yield(tmp)
                a = b
                b = tmp
            }
        }
