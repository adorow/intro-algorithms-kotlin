package chapter11

// m -> size
// h -> hash function
// k -> key
// i -> probe number (starting with 0)

// returns a hash function of the form (k: Int, i: Int) that returns the probed hash value
fun linearProbing(m: Int, h: (Int) -> Int): (Int, Int) -> Int {
    return fun(k: Int, i: Int): Int {
        return (h(k) + i) % m
    }
}
