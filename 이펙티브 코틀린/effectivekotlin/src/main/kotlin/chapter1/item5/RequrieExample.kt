package chapter1.item5

data class Point(
    val x: Int,
    val y: Int,
)

class Cluster

data class User(
    val email: String
)

fun factorial(n: Int): Long {
    require(n >= 0)
    return if (n <= 1) 1 else factorial(n - 1) * n
}

fun findClusters(points: List<Point>): List<Cluster> {
    require(points.isNotEmpty())
    //...
    return points.map { Cluster() }
}

fun sendEmail(user: User, message: String) {
    requireNotNull(user.email)
    require(isValildEmail(user.email))
}

fun isValildEmail(email: String): Boolean {
    // ...
    return false
}
