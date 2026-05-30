import kotlinx.coroutines.*

fun main() = runBlocking {
    val result = async {
        5 + 5
    }

    println(result.await())
}
