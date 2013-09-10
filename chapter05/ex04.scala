// Scala for the Impatient / Chapter 5 / Exercise 4:
// Reimplement the Time class from the preceding exercise so that the internal representation is the number of minutes
// since midnight (between 0 and 24 × 60 – 1). Do not change the public interface. That is, client code should be
// unaffected by your change.

class Time(val minutesFromMidnight: Int) {
  def this(hours: Int, minutes: Int) { this(minutesFromMidnight = hours * 60 + minutes) }
  def before(other: Time) = minutesFromMidnight < other.minutesFromMidnight
  def hours = (minutesFromMidnight / 60).floor.toInt
  def minutes = minutesFromMidnight % 60
}
