fun check(ch: Char) {
    if (ch in "aeiouAEIOU")
        println("Vowel")
    else
        println("Consonant")
}

fun main() {
    check('A')
}
