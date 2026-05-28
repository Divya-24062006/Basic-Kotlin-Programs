fun main() {
    var num = 121
    var temp = num
    var reverse = 0

    while (temp != 0) {
        val digit = temp % 10
        reverse = reverse * 10 + digit
        temp /= 10
    }

    if (num == reverse)
        println("Palindrome")
    else
        println("Not Palindrome")
}
