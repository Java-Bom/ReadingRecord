package chapter3.item23

interface Tree
class Birch : Tree
class Spruce : Tree

class Forest<T : Tree> {
    fun <T : Tree> addTree(tree: T) {
        println("adding tree ...")
    }
}

fun main() {
    val forest = Forest<Birch>()
    forest.addTree(Birch())
    forest.addTree(Spruce())
}