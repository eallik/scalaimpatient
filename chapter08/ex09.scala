class Creature {
  def range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature {
  override def range = 2
}

println((new Ant).env.size)
