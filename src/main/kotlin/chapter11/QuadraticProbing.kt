package chapter11

// m -> size
// h -> hash function
// k -> key
// i -> probe number (starting with 0)
// c1 -> a constant
// c2 -> another constant

// returns a hash function of the form (k: Int, i: Int) that returns the probed hash value
fun quadraticProbing(m: Int, h: (Int) -> Int, c1: Int, c2: Int): (Int, Int) -> Int {
    return fun(k: Int, i: Int): Int {
        return (h(k) + (c1 * i) + (c2 * (i * i))) % m
    }
}
