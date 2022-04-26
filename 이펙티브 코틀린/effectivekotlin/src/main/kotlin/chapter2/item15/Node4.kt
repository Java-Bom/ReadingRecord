package chapter2.item15

class Node4(val name: String) {
    fun makeChild(childName: String) =
        create("$name.$childName")
            .apply { print("Create ${this?.name} in ${this@Node4.name}") }

    fun create(name: String): Node3? = Node3(name)
}

fun main() {
    val node = Node4("parent")
    node.makeChild("child") // Create parent.child in parent
}