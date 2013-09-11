// Scala for the Impatient / Chapter 8 / Exercise 4:

// Define an abstract class Item with methods price and description. A SimpleItem is an item whose price and description are specified in the constructor. Take advantage of the fact that a val can override a def. A Bundle is an item that contains other items. Its price is the sum of the prices in the bundle. Also provide a mechanism for adding items to the bundle and a suitable description method.

abstract class Item { def price: Double; def description: String }

class SimpleItem(val price: Double, val description: String) extends Item

class Bundle(var items: List[Item] = Nil) extends Item {
  def price = items.map(_.price).sum
  def description = items.map(_.description).mkString(", ")
}

object Test extends App {
  val bundle = new Bundle(items = List(
    new SimpleItem(price = 13, description = "Unlucky"),
    new SimpleItem(price = 12, description = "Lucky")))
  assert(bundle.price == 25)
  bundle.items :+= new SimpleItem(price = 10, description = "Gadget")
  assert(bundle.price == 35)
}
