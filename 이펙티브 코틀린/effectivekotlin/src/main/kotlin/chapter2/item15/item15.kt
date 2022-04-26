package chapter2.item15

fun <T: Comparable<T>> List<T>.quickSort() : List<T>{
    if(size < 2) return this
    val pivot = first()
    val (smaller, bigger) = drop(1).partition { it < pivot }
    return smaller.quickSort() + pivot + bigger.quickSort()
}

