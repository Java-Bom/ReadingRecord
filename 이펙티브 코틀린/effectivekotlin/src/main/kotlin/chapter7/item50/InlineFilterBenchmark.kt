package chapter7.item50

open class InlineFilterBenchmark {

    lateinit var list: List<Int>
    lateinit var array: IntArray

//    @SetUp
    fun init() {
        list = List(1_000_000) { it }
        array = IntArray(1_000_000) { it }
    }

//    @Benchmark
    fun averageOnIntList(): Double {
        return list.average()
    }

//    @Benchmark
    fun averageOnIntArray(): Double {
        return array.average()
    }
}