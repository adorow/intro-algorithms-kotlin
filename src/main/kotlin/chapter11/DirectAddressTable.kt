package chapter11

class DirectAddressTable(val size: Int) {

    private val T: Array<Int?> = Array(size) { null }

    fun search(k: Int) = T[k]

    fun insert(key: Int, x: Int) {
        T[key] = x
    }

    fun delete(key: Int) {
        T[key] = null
    }

}