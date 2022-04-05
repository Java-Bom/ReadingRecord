package chapter1.item3

fun staredType() {
    val value = JavaClass().value
    println(value.length)
}

interface UserRepo {
    fun getUserName() = JavaClass().value
}


class UserRepoImp: UserRepo {
    override fun getUserName():String? {
        return null
    }
}

fun main() {
    val repo: UserRepo = UserRepoImp()
    val text: String = repo.getUserName()
    println("User name length is ${text.length}")
}