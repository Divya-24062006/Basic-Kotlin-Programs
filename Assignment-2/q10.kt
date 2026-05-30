sealed class ScreenState {
    object Loading : ScreenState()
    data class Success(val data: String) : ScreenState()
}

fun main() {
    val state = ScreenState.Success("Data Loaded")
    println(state)
}
