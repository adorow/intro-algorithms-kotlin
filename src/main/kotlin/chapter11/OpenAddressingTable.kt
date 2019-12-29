package chapter11

class OpenAddressingTable(val size: Int, private val hash: (Int, Int) -> Int = { key: Int, probe: Int -> (key + probe) % size }/*silly function for the sake of example*/) {

    private val T: Array<Int?> = Array(size) { null }

    fun search(k: Int): Int? {
        for (i in 0 until size) {
            val j = hash(k, i)
            // found an empty slot - so nothing was ever added with this key
            if (T[j] == null) {
                return null
            }
            if (T[j] == k) {
                return j
            }
        }
        return null
    }

    fun insert(k: Int): Int {
        for (i in 0 until size) {
            val j = hash(k, i)
            if (T[j] == null) {
                T[j] = k
                return j
            }
        }

        throw RuntimeException("hash table overflow")
    }

}