package chapter2.item15

class Node1(val name: String){
    fun makeChild(childName: String) = create("$name.$childName")
        .apply { print("Create ${name}") }

    fun create(name: String): Node1? = Node1(name)
}

fun main(){
    val node= Node1("parent")
    node.makeChild("child") // Create parent
}