// Scala for the Impatient / Chapter 9 / Exercise 2:

// Write a Scala program that reads a file with tabs, replaces each tab with spaces so that tab
// stops are at n-column boundaries, and writes the result to the same file.

import java.io.File
import java.nio.file.Paths
import scala.collection.JavaConverters._
import java.util.{ Scanner, Locale }
import java.util.regex.Pattern
import scala.io.Source

object Main extends App {
  val cwd = Paths.get("").toAbsolutePath
  val file = new File(cwd + "/" + args(0))

  val dbls = Source.fromFile(file).mkString.split("""\s+""").map(_.toDouble)
  val correctAvg = dbls.sum / dbls.size

  val scn = new Scanner(file).useLocale(Locale.US)
  val it = new Iterator[Double] { def hasNext = scn.hasNextDouble; def next = scn.nextDouble }

  def shift[T](acc: Option[T], curr: T)(f: T => T) = acc match {
    case None => Some(curr)
    case Some(somePrev) => Some(f.apply(somePrev))
  }

  val initial = (0, 0.0, None: Option[Double], None: Option[Double], None: Option[Double])
  val (count, sum, avg, min, max) = it.foldLeft(initial) { (acc, x: Double) =>
    val (count, sum, avg, min, max) = acc

    val newCount = count + 1
    val newSum = sum + x
    val newAvg = shift(acc = avg, curr = x) { acc => acc / newCount * count + x / newCount }
    val newMin = shift(acc = min, curr = x) { acc => if (x < acc) x else acc }
    val newMax = shift(acc = max, curr = x) { y => if (x > y) x else y }
    (newCount, newSum, newAvg, newMin, newMax)
  }

  assert(avg.getOrElse(0.0) == correctAvg, (avg.getOrElse(0.0), correctAvg))

  println("sum: %f\navg: %f\nmin: %f\nmax: %f".format(sum, avg.get, min.get, max.get))
}
