// Scala for the Impatient Chapter 11 Exercise 5

// Provide operators that construct an HTML table. For example,
//   Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"

class Table(val trsRev: List[Tr] = null) {
  def |(content: String) = {
    if (trsRev == null) Table(Tr(Td(content) :: Nil) :: Nil)
    else Table(Tr(Td(content) :: trsRev.head.tdsRev) :: trsRev.slice(1, trsRev.size))
  }
  def ||(content: String) = Table(Tr(Td(content) :: Nil) :: trsRev)

  override def toString() = "<table>" + (if (trsRev != null) trsRev.reverse.mkString else "") + "</table>"
}

class Tr(val tdsRev: List[Td]) {
  override def toString() = "<tr>" + tdsRev.reverse.mkString + "</tr>"
}
class Td(val content: String) {
  override def toString() = "<td>" + content + "</td>"
}

object Table { def apply(trsRev: List[Tr] = null) = new Table(trsRev) }
object Tr { def apply(tdsRev: List[Td]) = new Tr(tdsRev) }
object Td { def apply(content: String) = new Td(content) }

object HtmlTableTest extends App {
  assert((Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET").toString.startsWith("<table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling"))

  assert(Table().toString == "<table></table>")
  assert((Table() | "foo").toString == "<table><tr><td>foo</td></tr></table>")
  assert((Table() | "foo" | "bar").toString == "<table><tr><td>foo</td><td>bar</td></tr></table>")
  assert((Table() | "foo" | "bar" || "baz").toString == "<table><tr><td>foo</td><td>bar</td></tr><tr><td>baz</td></tr></table>")
  assert((Table() | "foo" | "bar" || "baz" | "baah").toString == "<table><tr><td>foo</td><td>bar</td></tr><tr><td>baz</td><td>baah</td></tr></table>")
  assert((Table() | "foo" | "bar" || "baz" | "baah" || "hello" | "world").toString == "<table><tr><td>foo</td><td>bar</td></tr><tr><td>baz</td><td>baah</td></tr><tr><td>hello</td><td>world</td></tr></table>")
}
