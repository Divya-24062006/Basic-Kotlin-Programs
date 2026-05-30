data class Product(val name: String, val price: Double)

fun main() {
    val inventory = listOf(
        Product("Laptop", 1000.0),
        Product("Mouse", 25.0),
        Product("Keyboard", 60.0)
    )

    val result = inventory
        .map { it.copy(price = it.price * 0.9) }
        .filter { it.price > 50 }

    println(result)
}
