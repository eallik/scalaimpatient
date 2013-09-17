// Scala for the Impatient / Chapter 10 / Exercise 4:

//Provide a CryptoLogger trait that encrypts the log messages with the Caesar cipher. The key should
//be 3 by default, but it should be overridable by the user. Provide usage examples with the default
//key and a key of –3.

trait Logged { def log(msg: String) }

trait PrintlnLogger extends Logged { def log(msg: String) { println(msg) } }

trait CaesarCryptoLogger extends Logged {
  val ALPHABET = {
    // based on http://stackoverflow.com/questions/220547/printable-char-in-java
    def isPrintableChar(c: Char) = {
      !Character.isISOControl(c) && c != java.awt.event.KeyEvent.CHAR_UNDEFINED && {
        val block = Character.UnicodeBlock.of(c)
        block != null && block != Character.UnicodeBlock.SPECIALS
      }
    }
    (0 until 256).map(_.toChar).filter(isPrintableChar(_))
  }

  def KEY = 3
  abstract override def log(msg: String) { super.log(encrypt(msg)) }

  def encrypt(msg: String) = msg.map(rot(_, KEY))
  def rot(c: Char, amount: Int) = ALPHABET(ALPHABET.indexOf(c) + KEY % ALPHABET.size)
}

object Main extends App {
  abstract class Hello extends Logged { def greet() { log("hello") } }
  (new Hello with PrintlnLogger with CaesarCryptoLogger).greet()
}
