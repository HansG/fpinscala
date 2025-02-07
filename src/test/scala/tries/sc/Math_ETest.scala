package tries.sc


import org.scalatest.*
import flatspec.*
import matchers.*

import scala.math.BigDecimal.RoundingMode
import scala.math.*


def e(iterLimit: Int): BigDecimal = {
  val from = BigDecimal(1, java.math.MathContext.UNLIMITED)
  LazyList
    .iterate((1, from, BigDecimal(1))) { case (n, result, fact) =>
      val newFact = fact * n
      val newResult = result + 1 / newFact
      (n + 1, newResult, newFact)
    }.collect { case (n, result, _) if iterLimit == n => result }
    .head
}


class Math_ETest extends AnyFlatSpec with should.Matchers {
  override def suiteName: String = "FactTest"

  "e" should "return the right result" in {
    e(20).toDouble shouldBe E
  }
  it should "give high precision value" in {
    val computed = e(2000).setScale(5000, RoundingMode.FLOOR)
    info(computed.toString)
  }
}
