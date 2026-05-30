class Window {
    var width = 0
    var height = 0
}

fun main() {
    val window = Window().apply {
        width = 1920
        height = 1080
    }

    println("${window.width} x ${window.height}")
}
