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


def e(x : BigDecimal, iter:Int):BigDecimal = {
  val from = BigDecimal(1, java.math.MathContext.UNLIMITED)
  LazyList
    .iterate( (1, from, BigDecimal(1), BigDecimal(1)) ) { case (n, result, fact, y) =>
      val newFact = fact * n
      val newy = y * x
      val newResult = result + newy/newFact
      (n+1, newResult, newFact, newy)
    }.collect {case (n, result, _, _) if iter == n => result}
    .head
}

e(1,20)
//org.scalatest.tools.Runner.main(Array("-oDF", "-s", classOf[ETest].getName))

e(3,20)
e(5,20)
e(3,100) * e(5,100)
e(8,100)
e(9, 100)
e(8,100) * e(1,100)
