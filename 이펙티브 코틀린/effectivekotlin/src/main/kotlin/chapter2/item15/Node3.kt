package chapter2.item15

class Node3(val name: String) {
    fun makeChild(childName: String) = create("$name.$childName")
        .also { print("Create ${it?.name}") }

    fun create(name: String): Node3? = Node3(name)
}

fun main(){
    val node= Node2("parent")
    node.makeChild("child") // Create parent.child
}