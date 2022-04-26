package chapter2.item15

class Node2(val name: String){
    fun makeChild(childName: String) = create("$name.$childName")
        .apply { print("Create ${this?.name}") }

    fun create(name: String): Node2? = Node2(name)
}

fun main(){
    val node= Node2("parent")
    node.makeChild("child") // Create parent.child
}