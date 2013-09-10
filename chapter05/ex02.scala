// Scala for the Impatient / Chapter 5 / Exercise 2:
// Write a class BankAccount with methods deposit and withdraw, and a read-only property balance

class Account private() {
  // don't define 'balance' in the primary constructor; otherwise it would have to be named '_balance', which would make
  // calls using named parameters ugly
  private var _balance: Int = 0
  def this(balance: Int) { this(); _balance = balance }

  def balance = _balance
  def deposit(amount: Int) { _balance += amount }
  def withdraw(amount: Int) { _balance -= amount }
}
