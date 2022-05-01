package chapter2.item15

class Node1(val name: String){
    fun makeChild(childName: String) = create("$name.$childName")
        .apply { print("Create ${name}") }

    fun create(name: String): Node1? = Node1(name)
}

fun main(){
    val node= Node1("parent")
    node.makeChild("child") // Create parent

    Count().counter = 3
}

class Count {
    var counter = 0 // the initializer assigns the backing field directly
        set(value) {
            if (value >= 0)
             counter = value // ERROR StackOverflow: Using actual name 'counter' would make setter recursive
        }
}