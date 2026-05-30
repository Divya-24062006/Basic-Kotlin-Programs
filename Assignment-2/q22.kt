sealed class ScreenState {
    object Loading : ScreenState()
    data class Success(val data: String) : ScreenState()
    data class Error(val errorMsg: String) : ScreenState()
}

fun renderUI(state: ScreenState) {
    when (state) {
        is ScreenState.Loading ->
            println("Loading...")

        is ScreenState.Success ->
            println("Success: ${state.data}")

        is ScreenState.Error ->
            println("Error: ${state.errorMsg}")
    }
}

fun main() {
    renderUI(ScreenState.Loading)
    renderUI(ScreenState.Success("User Data"))
    renderUI(ScreenState.Error("Network Error"))
}
