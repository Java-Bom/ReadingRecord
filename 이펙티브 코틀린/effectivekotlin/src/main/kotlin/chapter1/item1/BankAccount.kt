package chapter1.item1

class BankAccount {
    var balance = 0.0
        private set

    fun deposit(depositAmount: Double) {
        balance += depositAmount
    }

    fun withdraw(withdrawAmount: Double) {
        if (balance < withdrawAmount) {
            throw InsufficientFunds()
        }
        balance -= withdrawAmount
    }
}

class InsufficientFunds: Exception()

fun main() {
    val account = BankAccount()
    println(account.balance)
    account.deposit(100.0)
    println(account.balance)
    account.withdraw(50.0)
    println(account.balance)
}

