import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        println("Background task finished")
    }
}
