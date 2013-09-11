// Scala for the Impatient / Chapter 8 / Exercise 2:

// Extend the BankAccount class of the preceding exercise into a class SavingsAccount that earns interest every month
// (when a method earnMonthlyInterest is called) and has three free deposits or withdrawals every month. Reset the
// transaction count in the earnMonthlyInterest method.

// `balance` in BankAccount needed to be made available to SavingsAccount
class BankAccount(initialBalance: Double) {
  private var _balance = initialBalance
  def deposit(amount: Double) = { _balance += amount; _balance }
  def withdraw(amount: Double) = { _balance -= amount; _balance }
  def balance = _balance
}

class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  private var numTransactionsLeftThisMonth = 3

  def earnMonthlyInterest() {
    deposit(balance * 0.001)
    numTransactionsLeftThisMonth = 3
  }

  override def deposit(amount: Double) = { checkFreeTransactions(); super.deposit(amount) }
  override def withdraw(amount: Double) = { checkFreeTransactions(); super.withdraw(amount) }

  private def checkFreeTransactions() {
    if (numTransactionsLeftThisMonth == 0) throw new NoTransactionsLeftThisMonth
    numTransactionsLeftThisMonth -= 1
  }
}

class NoTransactionsLeftThisMonth extends Exception

// test it

object SavingsAccountTest extends App {
  val svAcc = new SavingsAccount(100)
  assert(svAcc.balance == 100)
  svAcc.deposit(100)
  assert(svAcc.balance == 200)
  svAcc.deposit(100)
  svAcc.deposit(100)
  assertRaises[NoTransactionsLeftThisMonth] { () => svAcc.deposit(100) }
}

// I just implemented this for use above because I wanted to know how to do it in Scala

import scala.reflect.ClassTag

def assertRaises[X: ClassTag](fn: ()=> Any) {
  assert(try {
    fn.apply
    false
  } catch {
    case exc: X => true
    case exc: Throwable => false
  })
}
