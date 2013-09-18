// Scala for the Impatient Chapter 11 Exercise 7

// Implement a class BitSequence that stores a sequence of 64 bits packed in a Long value. Supply apply and update operators to get and set an individual bit.

class BitSeq(var contents: Long) extends IndexedSeq[Boolean]{
  def apply(ix: Int): Boolean = { require(ix >= 0 && ix <= 63); (contents & (1L << ix)) != 0 }
  def update(ix: Int, value: Boolean) { if (value) contents |= 1 << ix else contents &= ~(1 << ix) }
  def length = 64
}

object BitSeq { def apply(contents: Long = 0L) = new BitSeq(contents) }

object BitSeqTest extends App {
  val seq = BitSeq()
  assert(seq.forall(!_))

  for (i <- 0 until seq.size) seq(i) = true
  assert(seq.forall(identity))

  assert(seq.map(if (_) 1 else 0).mkString == "1" * 64)

  for (i <- 0 until seq.size) seq(i) = false
  assert(seq.map(if (_) 1 else 0).mkString == "0" * 64)

  seq(10) = true
  assert(seq(10))

  seq(10) = false
  assert(!seq(10))
}
