// https://gist.github.com/dacr/c071a7b7d3de633281cbe84a34be47f1
// run-with : scala-cli $file

// ---------------------
//> using scala "3.4.2"
//> using dep "org.scalatest::scalatest:3.2.16"
//> using objectWrapper
// ---------------------

import org.scalatest.*
import flatspec.*
import matchers.*

import scala.math.BigDecimal.RoundingMode
import scala.math.*


def e(iter:Int):BigDecimal = {
  val from = BigDecimal(1, java.math.MathContext.UNLIMITED)
  LazyList
    .iterate( (1, from, BigDecimal(1)) ) { case (n, result, fact) =>
      val newFact = fact * n
      val newResult = result + 1/newFact
      (n+1, newResult, newFact)
    }.collect {case (n, result, _) if iter == n => result}
    .head
}


class ETest extends AnyFlatSpec with should.Matchers {
  override def suiteName: String = "FactTest"

  "e" should "return the right result" in {
    e(20).toDouble shouldBe E
  }
  it should "give high precision value" in {
    val computed = e(2000).setScale(5000, RoundingMode.FLOOR)
    info(computed.toString)
  }
}

//org.scalatest.tools.Runner.main(Array("-oDF", "-s", classOf[ETest].getName))