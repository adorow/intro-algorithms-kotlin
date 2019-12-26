package chapter11

import chapter10.LinkedList

class ChainedHashTable(val size: Int, private val hash: (Int) -> Int = { key: Int -> key % size }/*silly function for the sake of example*/) {

    private val T: Array<LinkedList> = Array(size) { LinkedList() }

    // --> for the sake of simplicity, this implementation uses key=value

    fun search(k: Int) =
            T[hash(k)].search(k)

    fun insert(k: Int) =
            T[hash(k)].insert(k)

    fun delete(k: Int) =
            T[hash(k)].delete(k)

}