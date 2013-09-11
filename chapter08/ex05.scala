// Scala for the Impatient / Chapter 8 / Exercise 5:

// Design a class Point whose x and y coordinate values can be provided in a constructor. Provide a subclass
// LabeledPoint whose constructor takes a label value and x and y coordinates, such as
//   new LabeledPoint("Black Thursday", 1929, 230.07)

class Point(val x: Int, val y: Int)

class LabeledPoint(val label: String, val x: Int, val y: Int) extends Point(x, y)
