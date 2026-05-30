data class User(
    val id: Int,
    val username: String,
    val isActive: Boolean
)

fun main() {
    val user1 = User(1, "Divya", true)
    val user2 = user1.copy(isActive = false)

    println(user1)
    println(user2)
}
