class IDGenerator {
    companion object {
        var currentId = 0

        fun getNextId(): Int {
            return ++currentId
        }
    }
}

fun main() {
    println(IDGenerator.getNextId())
    println(IDGenerator.getNextId())
}
