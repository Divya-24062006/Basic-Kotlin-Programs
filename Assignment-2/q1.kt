class BankAccount {
    private var balance = 0.0

    fun deposit(amount: Double) {
        if (amount > 0) balance += amount
    }

    fun showBalance() {
        println(balance)
    }
}

fun main() {
    val account = BankAccount()
    account.deposit(500.0)
    account.showBalance()
}
