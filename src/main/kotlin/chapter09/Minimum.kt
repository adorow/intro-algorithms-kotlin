package chapter09

fun List<Int>.minimum(): Int? {
    if (this.isEmpty()) {
        return null
    }
    var min = this[0]

    for (i in 1 until this.size) {
        if (min > this[i]) {
            min = this[i]
        }
    }
    return min
}
