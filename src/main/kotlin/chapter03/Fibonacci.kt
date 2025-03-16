package chapter03

fun fibonacci() : Sequence<Int> =
        sequence {
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
