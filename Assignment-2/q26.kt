import kotlinx.coroutines.*

data class CartItem(
    val name: String,
    val price: Double,
    val quantity: Int
)

fun List<CartItem>.calculateTotal(): Double =
    sumOf { it.price * it.quantity }

sealed class CheckoutResult {
    object Processing : CheckoutResult()
    data class Success(val receiptId: String) : CheckoutResult()
    data class Failed(val reason: String) : CheckoutResult()
}

object PaymentProcessor {
    suspend fun processPayment(cart: List<CartItem>): CheckoutResult {
        delay(2000)

        val total = cart.calculateTotal()

        return if (total > 1000) {
            CheckoutResult.Failed("Insufficient funds for large transaction")
        } else {
            CheckoutResult.Success("TXN-${(1000..9999).random()}")
        }
    }
}

fun main(args: Array<String>) {
    runBlocking {

        val cheapCart = listOf(
            CartItem("Mouse", 25.0, 2),
            CartItem("Keyboard", 50.0, 1)
        )

        val expensiveCart = listOf(
            CartItem("Laptop", 1500.0, 1)
        )

        val job1 = launch {
            when (val result = PaymentProcessor.processPayment(cheapCart)) {
                is CheckoutResult.Processing ->
                    println("Processing")

                is CheckoutResult.Success ->
                    println("Cheap Cart Success: ${result.receiptId}")

                is CheckoutResult.Failed ->
                    println("Cheap Cart Failed: ${result.reason}")
            }
        }

        val job2 = launch {
            when (val result = PaymentProcessor.processPayment(expensiveCart)) {
                is CheckoutResult.Processing ->
                    println("Processing")

                is CheckoutResult.Success ->
                    println("Expensive Cart Success: ${result.receiptId}")

                is CheckoutResult.Failed ->
                    println("Expensive Cart Failed: ${result.reason}")
            }
        }

        job1.join()
        job2.join()
    }
}
