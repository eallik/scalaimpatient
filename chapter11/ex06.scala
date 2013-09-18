// Scala for the Impatient Chapter 11 Exercise 6

// Provide a class ASCIIArt whose objects contain figures such as
//  /\_/\
// ( ' ' )
// (  -  )
//  | | |
// (__|__)

// Supply operators for combining two ASCIIArt figures horizontally

//  /\_/\    -----
// ( ' ' )  / Hello \
// (  -  ) <  Scala |
//  | | |   \ Coder /
// (__|__)    -----

class ASCIIArt(val lines: List[String]) {
  require(lines.map(_.size).toSet.size == 1, "all lines must have equal width")

  def height = lines.size
  def width = lines(0).size

  def followedBy(other: ASCIIArt) = ASCIIArt((lines zip other.lines).map { case (x, y) => x + y })
  def above(other: ASCIIArt) = ASCIIArt(lines ++ other.lines)

  def padRight(totalWidth: Int, c: Char = ' ') = ASCIIArt(lines.map(_.padTo(totalWidth, c)))
  def padLeft(totalWidth: Int, c: Char = ' ') = ASCIIArt(lines.map(_.reverse.padTo(totalWidth, c).reverse))

  override def toString() = lines.mkString("\n")
}

object ASCIIArt {
  def apply(lines: List[String]) = new ASCIIArt(lines)
  def emptyCol(height: Int) = ASCIIArt(List((" " * height).map(_.toString): _*))
  def emptyRow(width: Int) = ASCIIArt(List(" " * width))
}

object ASCIIArtTest extends App {
  val art1 = new ASCIIArt(
    """ /\_/\ """ ::
    """( ' ' )""" ::
    """(  -  )""" ::
    """ | | | """ ::
    """(__|__)""" :: Nil)

  val art2 = new ASCIIArt(
    """  -----   """ ::
    """ / Hello \""" ::
    """<  Scala |""" ::
    """ \ Coder /""" ::
    """   -----  """ :: Nil)

  def hr = println("\n---------------------------\n")

  println(art1)
  hr

  println(art2)
  hr

  println(art1 followedBy ASCIIArt.emptyCol(art1.height) followedBy art2)
  hr

  println(art1.padRight(art2.width) above ASCIIArt.emptyRow(art2.width) above art2)
  hr
}
