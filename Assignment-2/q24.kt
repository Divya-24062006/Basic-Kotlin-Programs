class File(val filename: String) {
    var permissions = ""
}

fun buildFile(filename: String): File {

    return File(filename)
        .apply {
            permissions = "Read/Write"
        }
        .also {
            println("File $filename created")
        }
}

fun main() {
    val file = buildFile("notes.txt")

    println(file.filename)
    println(file.permissions)
}
