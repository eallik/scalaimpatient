// Scala for the Impatient / Chapter 8 / Exercise 1:

// Extend the following BankAccount class to a CheckingAccount class that charges $1 for every deposit and withdrawal.

class BankAccount(initialBalance: Double) {
  private var balance = initialBalance
  def deposit(amount: Double) = { balance += amount; balance }
  def withdraw(amount: Double) = { balance -= amount; balance }
}

class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  override def deposit(amount: Double) = { super.withdraw(1); super.deposit(amount) }
  override def withdraw(amount: Double) = { super.withdraw(1); super.withdraw(amount) }
}

object CheckintACcountTest extends App {
  var cAcc = new CheckingAccount(100.0)
  assert(cAcc.deposit(100.0) == 199.0)
  assert(cAcc.withdraw(100.0) == 98.0)
}
