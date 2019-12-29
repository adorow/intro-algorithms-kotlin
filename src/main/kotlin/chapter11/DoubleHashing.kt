package chapter11

// m -> size
// h1 -> an auxiliary hash function
// h2 -> another auxiliary hash function
// k -> key
// i -> probe number (starting with 0)

// returns a hash function of the form (k: Int, i: Int) that returns the probed hash value
fun doubleHashing(m: Int, h1: (Int) -> Int, h2: (Int) -> Int): (Int, Int) -> Int {
    return fun(k: Int, i: Int): Int {
        return (h1(k) + (i * h2(k))) % m
    }
}
