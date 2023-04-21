package scautable

import munit.CatsEffectSuite

class TableTest extends CatsEffectSuite {

  import scautable.{*, given}
  test {
    case class Easy(s: String, d: Double)
    println(scautable(Seq(Easy("thing", 1.2)))).toString()
  }

}
