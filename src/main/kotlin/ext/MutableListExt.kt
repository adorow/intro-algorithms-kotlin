package ext

fun <T> MutableList<T>.swap(i1: Int, i2: Int) {
    val v1 = this[i1]
    this[i1] = this[i2]
    this[i2] = v1
}