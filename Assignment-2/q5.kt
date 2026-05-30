abstract class Animal {
    abstract fun makeSound()
}

class Cat : Animal() {
    override fun makeSound() {
        println("Meow")
    }
}

fun main() {
    Cat().makeSound()
}
