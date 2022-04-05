package chapter1.item5

class Stack<T>(
    var collection: List<T>
) {
    private val size = 3
    private val isOpen = false
    fun pop(num: Int = 1): List<T> {
        require(num <= size) {
            "Cannot remove more elements than current size"
        }

        check(isOpen) { "Cannot pop from closed stack" }

        val ret = collection.take(num)
        collection = collection.drop(num)
        assert(ret.size == num)
        return ret
    }
}
