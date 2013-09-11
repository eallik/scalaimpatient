// Scala for the Impatient / Chapter 8 / Exercise 6

// Define an abstract class Shape with an abstract method centerPoint and subclasses Rectangle and Circle. Provide
// appropriate constructors for the subclasses and override the centerPoint method in each subclass.

abstract class Shape { def centerPoint: (Int, Int) }

class Rectangle(val width: Int, val height: Int, val centerPoint: (Int, Int)) extends Shape

class Circle(val radius: Int, val centerPoint: (Int, Int)) extends Shape
