// Scala for the Impatient / Chapter 5 / Exercise 3:
// Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean that checks
// whether this time comes before the other. A Time object should be constructed as new Time(hrs, min), where hrs is in
// military time format (between 0 and 23).

class Time(val hours: Int, val minutes: Int) {
  def before(other: Time) = hours < other.hours || hours == other.hours && minutes < other.minutes
}
