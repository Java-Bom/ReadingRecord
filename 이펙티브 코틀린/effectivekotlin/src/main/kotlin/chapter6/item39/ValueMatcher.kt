package chapter6.item39

sealed class ValueMatcher<T>{
    abstract fun match(value: T) :Boolean

    class Equal<T>(val value: T) : ValueMatcher<T>(){
        override fun match(value: T): Boolean = value == this.value
    }

    class NotEqual<T>(val value: T) : ValueMatcher<T>(){
        override fun match(value: T): Boolean = value != this.value
    }

    class EmptyList<T>(val value: T) : ValueMatcher<T>(){
        override fun match(value: T): Boolean = value is List<*> && value.isEmpty()
    }

    class NotEmptyList<T>(val value: T) : ValueMatcher<T>(){
        override fun match(value: T): Boolean = value is List<*> && value.isNotEmpty()
    }
}