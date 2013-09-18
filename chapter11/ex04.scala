// Scala for the Impatient Chapter 11 Exercise 4

// Implement a class Money with fields for dollars and cents. Supply +, - operators as well as
// comparison operators == and <. For example, Money(1, 75) + Money(0, 50) == Money(2, 25) should be
// true. Should you also supply * and / operators? Why or why not?

class Money private (val totalCents: Int) {
  def this(dollars: Int, cents: Int) { this(totalCents = dollars * 100 + cents) }

  def dollars = totalCents / 100
  def cents = totalCents % 100

  def +(other: Money) = new Money(totalCents + other.totalCents)
  def -(other: Money) = this + -other
  def unary_-() = new Money(-totalCents)

  // A: multiplication and division are meaningful as long as the units are accounted for properly

  def *(factor: Double) = new Money((totalCents * factor).round.toInt)
  def /(factor: Double) = new Money((totalCents / factor).round.toInt)
  def /(other: Money) = totalCents.toDouble / other.totalCents

  def ==(other: Money) = totalCents == other.totalCents
  override def hashCode() = totalCents.hashCode

  override def toString() = "$" + dollars + "." + cents
}

object Money extends Ordering[Money] {
  def apply(dollars: Int, cents: Int = 0) = new Money(dollars, cents)
  def compare(self: Money, other: Money) = self.totalCents - other.totalCents
}

object MoneyTest extends App {
  assert(Money(3, 0) == Money(3))
  assert(Money(3, 0) != Money(4, 0))

  assert(Money(3, 30) + Money(4, 40) == Money(7, 70))
  assert(Money(3, 30) - Money(4, 40) == Money(-1, -10))
  assert(Money(3, 30) * 3 == Money(9, 90))
  assert(Money(3, 30) / 3 == Money(1, 10))
  assert(Money(3, 30) / Money(3, 30) == 1)
  println(Money(3, 30) / Money(6, 60))
  assert(Money(3, 30) / Money(6, 60) == 0.5)

  assert(Money(1, 23) < Money(2, 23))
  assert(Money(1, 23) <= Money(2, 23))
  assert(Money(2, 23) > Money(1, 23))
  assert(Money(2, 23) >= Money(1, 23))
}
